package com.casttotv.screenmirroring.chromecast.smart.tv.FireTv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.Common;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.OnSwipeTouchListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.Api.Api;
import com.casttotv.screenmirroring.chromecast.smart.tv.Api.Description;
import com.casttotv.screenmirroring.chromecast.smart.tv.Api.Text;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.FragmentRemoteFireTvBinding;
import com.casttotv.screenmirroring.chromecast.smart.tv.FireTv.utils.FireTVController;
import com.github.kunal52.remote.Remotemessage;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentRemoteFireTv extends Fragment implements View.OnClickListener {
    private final int REQUEST_CODE_SPEECH_INPUT = Remotemessage.KEYCODE_NUMPAD_4_VALUE;
    FragmentRemoteFireTvBinding binding;
    private boolean isTouchPad;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        binding = FragmentRemoteFireTvBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        super.onViewCreated(view, bundle);

        FragmentRemoteFireTv remoteFireTvFragment = this;
        binding.ibPower.setOnClickListener(remoteFireTvFragment);
        binding.dpad.upButton.setOnClickListener(remoteFireTvFragment);
        binding.dpad.leftButton.setOnClickListener(remoteFireTvFragment);
        binding.dpad.downButton.setOnClickListener(remoteFireTvFragment);
        binding.dpad.rightButton.setOnClickListener(remoteFireTvFragment);
        binding.dpad.okButton.setOnClickListener(remoteFireTvFragment);
        binding.ibBack.setOnClickListener(remoteFireTvFragment);
        binding.ibHome.setOnClickListener(remoteFireTvFragment);
        binding.ibPlayPause.setOnClickListener(remoteFireTvFragment);
        binding.ibMenu.setOnClickListener(remoteFireTvFragment);
        binding.ibTouchpad.setOnClickListener(remoteFireTvFragment);
        binding.ibKeyboard.setOnClickListener(remoteFireTvFragment);
        binding.ibVoice.setOnClickListener(remoteFireTvFragment);
        binding.ivTouchpad.setOnTouchListener(new OnSwipeTouchListener(requireContext()) {
            @Override
            public void onSingleTap() {
                sendAction("select");
            }

            @Override
            public void onSwipeRight() {
                sendAction("dpad_right");
            }

            @Override
            public void onSwipeLeft() {
                sendAction("dpad_left");
            }

            @Override
            public void onSwipeTop() {
                sendAction("dpad_up");
            }

            @Override
            public void onSwipeBottom() {
                sendAction("dpad_down");
            }
        });
    }

    public void onClick(View view) {
        Common common = Common.INSTANCE;
        common.shakeItBaby(requireContext());
        if (Intrinsics.areEqual(view, binding.ibPower)) {
            sendAction("sleep");
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.upButton)) {
            sendAction("dpad_up");
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.leftButton)) {
            sendAction("dpad_left");
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.downButton)) {
            sendAction("dpad_down");
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.rightButton)) {
            sendAction("dpad_right");
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.okButton)) {
            sendAction("select");
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibBack)) {
            sendAction("back");
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibHome)) {
            sendAction("home");
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibPlayPause)) {
            sendActionMedia("play");
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibMenu)) {
            sendAction("menu");
            return;
        }
        if (!Intrinsics.areEqual(view, binding.ibTouchpad)) {
            if (isTouchPad) {
                binding.flTouchpad.setVisibility(View.GONE);
                binding.dpadSection.setVisibility(View.VISIBLE);
                isTouchPad = false;
            } else {
                binding.flTouchpad.setVisibility(View.VISIBLE);
                binding.dpadSection.setVisibility(View.GONE);
                isTouchPad = true;
            }
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibKeyboard)) {
            new DialogTextInputFireTv().show(getChildFragmentManager(), DialogTextInputFireTv.class.getName());
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibVoice)) {
            Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
            intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
            intent.putExtra("android.speech.extra.LANGUAGE", Locale.getDefault());
            intent.putExtra("android.speech.extra.PROMPT", getString(R.string.speak_to_text));
            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
            } catch (Exception e) {
                Toast.makeText(requireContext(), ' ' + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void sendAction(String str) {
        FireTVController fireTVController = FireTVController.INSTANCE;
        String token = fireTVController.getToken(requireContext());
        Api api = FireTVController.INSTANCE.getApi();
        Call<Description> remote = api != null ? api.remote(FireTVController.apiKeyFireTV, token, str) : null;
        if (remote != null) {
            remote.enqueue(new Callback<Description>() {
                @Override
                public void onResponse(@NonNull Call<Description> call, @NonNull Response<Description> response) {

                }

                @Override
                public void onFailure(@NonNull Call<Description> call, @NonNull Throwable t) {
                    Log.e("fatal", "RemoteFireTvFragment sendAction onFailure: " + t.getMessage());
                }
            });
        }
    }

    private void sendActionMedia(String str) {
        FireTVController fireTVController = FireTVController.INSTANCE;
        String token = fireTVController.getToken(requireContext());
        Api api = FireTVController.INSTANCE.getApi();
        Call<Description> remoteMedia = api != null ? api.remoteMedia(FireTVController.apiKeyFireTV, token, str) : null;
        if (remoteMedia != null) {
            remoteMedia.enqueue(new Callback<Description>() {
                @Override
                public void onResponse(@NonNull Call<Description> call, @NonNull Response<Description> response) {

                }

                @Override
                public void onFailure(@NonNull Call<Description> call, @NonNull Throwable t) {
                    Log.e("fatal", "RemoteFireTvFragment sendActionMedia onFailure: " + t.getMessage());
                }
            });
        }
    }

    /* CODE  */
    @Override
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        if (n == this.REQUEST_CODE_SPEECH_INPUT && n2 == -1 && intent != null) {
            final ArrayList stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
            final int n3 = 0;
            if (stringArrayListExtra.get(0) != null) {
                final Object value = stringArrayListExtra.get(0);
                final List split = new Regex("").split((CharSequence) value, 0);
                List list = null;
                Label_0179:
                {
                    if (!split.isEmpty()) {
                        final ListIterator listIterator = split.listIterator(split.size());
                        while (listIterator.hasPrevious()) {
                            if (((CharSequence) listIterator.previous()).length() != 0) {
                                list = CollectionsKt.take((Iterable) split, listIterator.nextIndex() + 1);
                                break Label_0179;
                            }
                        }
                    }
                    list = CollectionsKt.emptyList();
                }
                final String[] array = (String[]) list.toArray(new String[0]);
                final String[] array2 = array;
                for (int length = array2.length, i = n3; i < length; ++i) {
                    String s;
                    if (Intrinsics.areEqual((Object) (s = array2[i]), (Object) " ")) {
                        s = "%20";
                    }
                    final Api api = FireTVController.INSTANCE.getApi();
                    Call text;
                    if (api != null) {
                        final FireTVController instance = FireTVController.INSTANCE;
                        text = api.text("0987654321", instance.getToken(requireContext()), new Text(s));
                    } else {
                        text = null;
                    }
                    if (text != null) {
                        text.enqueue(new Callback() {
                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) {

                            }

                            @Override
                            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                                Log.e("fatal", "RemoteFireTvFragment onActivityResult onFailure: " + t.getMessage());
                            }
                        });
                    }
                }
            }
        }
        super.onActivityResult(n, n2, intent);
    }

}
