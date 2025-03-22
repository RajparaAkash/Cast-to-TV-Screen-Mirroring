package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityConnect;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.Common;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.OnSwipeTouchListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.FragmentRemoteRokuBinding;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.CastClient;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.RokuKey;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.tasks.RxRequestTask;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils.RokuRequestTypes;
import com.github.kunal52.remote.Remotemessage;
import com.jaku.core.JakuRequest;
import com.jaku.core.KeypressKeyValues;
import com.jaku.request.KeypressRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class FragmentRemoteRoku extends Fragment implements View.OnClickListener {

    public final int REQUEST_CODE_SPEECH_INPUT = Remotemessage.KEYCODE_NUMPAD_3_VALUE;
    FragmentRemoteRokuBinding binding;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        binding = FragmentRemoteRokuBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View v, Bundle bundle) {
        super.onViewCreated(v, bundle);

        binding.ibRemote.setOnClickListener(view -> {
            binding.ibRemote.setBackgroundResource(R.drawable.bg_tab_selected);
            binding.ibTouchpad.setBackgroundColor(0);
            binding.dpadSection.setVisibility(View.VISIBLE);
            binding.flTouchpad.setVisibility(View.GONE);

            binding.ibRemote.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white));
            binding.ibTouchpad.setColorFilter(ContextCompat.getColor(requireContext(), R.color.remote_icon_tint));
        });

        binding.ibTouchpad.setOnClickListener(view -> {
            if (!TVConnectUtils.getInstance().isConnected()) {
                startActivity(new Intent(requireActivity(), ActivityConnect.class));
                return;
            }
            binding.ibRemote.setBackgroundColor(0);
            binding.ibTouchpad.setBackgroundResource(R.drawable.bg_tab_selected);
            binding.dpadSection.setVisibility(View.GONE);
            binding.flTouchpad.setVisibility(View.VISIBLE);

            binding.ibRemote.setColorFilter(ContextCompat.getColor(requireContext(), R.color.remote_icon_tint));
            binding.ibTouchpad.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white));
        });

        FragmentRemoteRoku remoteRokuFragment = this;
        binding.ibBack.setOnClickListener(remoteRokuFragment);
        binding.ibHome.setOnClickListener(remoteRokuFragment);
        binding.ibPower.setOnClickListener(remoteRokuFragment);
        binding.dpad.upButton.setOnClickListener(remoteRokuFragment);
        binding.dpad.downButton.setOnClickListener(remoteRokuFragment);
        binding.dpad.leftButton.setOnClickListener(remoteRokuFragment);
        binding.dpad.rightButton.setOnClickListener(remoteRokuFragment);
        binding.dpad.okButton.setOnClickListener(remoteRokuFragment);
        binding.ibPlayPause.setOnClickListener(remoteRokuFragment);
        binding.ibPrev.setOnClickListener(remoteRokuFragment);
        binding.ibNext.setOnClickListener(remoteRokuFragment);
        binding.ibBack.setOnClickListener(remoteRokuFragment);
        binding.ibReplay.setOnClickListener(remoteRokuFragment);
        binding.ibMic.setOnClickListener(remoteRokuFragment);
        binding.ibKeyboard.setOnClickListener(remoteRokuFragment);
        binding.ibInfo.setOnClickListener(remoteRokuFragment);
        binding.ibMute.setOnClickListener(remoteRokuFragment);
        binding.btnVolumeUp.setOnClickListener(remoteRokuFragment);
        binding.btnVolumeDown.setOnClickListener(remoteRokuFragment);
        binding.ivTouchpad.setOnTouchListener(new OnSwipeTouchListener(requireContext()) {
            @Override
            public void onSwipeTop() {
                performKeypress(KeypressKeyValues.UP);
                super.onSwipeTop();
            }

            @Override
            public void onSwipeLeft() {
                performKeypress(KeypressKeyValues.LEFT);
                super.onSwipeLeft();
            }

            @Override
            public void onSwipeBottom() {
                performKeypress(KeypressKeyValues.DOWN);
                super.onSwipeBottom();
            }

            @Override
            public void onSwipeRight() {
                performKeypress(KeypressKeyValues.RIGHT);
                super.onSwipeRight();
            }

            @Override
            public void onDoubleTapF(MotionEvent motionEvent) {
                performKeypress(KeypressKeyValues.SELECT);
                super.onDoubleTapF(motionEvent);
            }
        });

    }

    public void performKeypress(KeypressKeyValues keypressKeyValues) {
        if (TVConnectUtils.getInstance().getConnectableDevice() != null) {
            try {
                performNewRequest(new JakuRequest(new KeypressRequest("http://" + TVConnectUtils.getInstance().getConnectableDevice().getIpAddress() + ":8060",
                        keypressKeyValues.getValue()), null), RokuRequestTypes.keypress);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void performNewRequest(JakuRequest jakuRequest, RokuRequestTypes rokuRequestTypes) {
        try {
            Observable.fromCallable(new RxRequestTask(requireActivity().getApplicationContext(), jakuRequest, rokuRequestTypes))
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() {
                        @Override
                        public void call(Object o) {
                        }
                    });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void onClick(View view) {
        if (Intrinsics.areEqual(view, binding.ibHome)) {
            if (!TVConnectUtils.getInstance().isConnected()) {
                startActivity(new Intent(requireActivity(), ActivityConnect.class));
                return;
            }
            Common common = Common.INSTANCE;
            common.shakeItBaby(getActivity());
            performKeypress(KeypressKeyValues.HOME);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibPower)) {
            if (!TVConnectUtils.getInstance().isConnected()) {
                startActivity(new Intent(requireActivity(), ActivityConnect.class));
                return;
            }
            Common common = Common.INSTANCE;
            common.shakeItBaby(getActivity());
            performKeypress(KeypressKeyValues.POWER_OFF);
            return;
        }

        if (!TVConnectUtils.getInstance().isConnected()) {
            /*ActivityDevicePicker.startDevicePicker(requireActivity());*/
            startActivity(new Intent(requireActivity(), ActivityConnect.class));
            return;
        }
        Common common = Common.INSTANCE;
        Context requireContext = requireContext();
        common.shakeItBaby(requireContext);
        if (Intrinsics.areEqual(view, binding.ibBack)) {
            performKeypress(KeypressKeyValues.BACK);
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.upButton)) {
            performKeypress(KeypressKeyValues.UP);
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.leftButton)) {
            performKeypress(KeypressKeyValues.LEFT);
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.downButton)) {
            performKeypress(KeypressKeyValues.DOWN);
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.rightButton)) {
            performKeypress(KeypressKeyValues.RIGHT);
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.okButton)) {
            performKeypress(KeypressKeyValues.SELECT);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibPrev)) {
            performKeypress(KeypressKeyValues.REV);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibPlayPause)) {
            performKeypress(KeypressKeyValues.PLAY);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibNext)) {
            performKeypress(KeypressKeyValues.FWD);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibReplay)) {
            performKeypress(KeypressKeyValues.INTANT_REPLAY);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibKeyboard)) {
            DialogTextInput dialogTextInput = new DialogTextInput();
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null) {
                dialogTextInput.show(fragmentManager, DialogTextInput.class.getName());
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibInfo)) {
            performKeypress(KeypressKeyValues.INFO);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibMute)) {
            performKeypress(KeypressKeyValues.VOLUME_MUTE);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnVolumeDown)) {
            performKeypress(KeypressKeyValues.VOLUME_DOWN);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnVolumeUp)) {
            performKeypress(KeypressKeyValues.VOLUME_UP);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibMic)) {
            Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
            intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
            intent.putExtra("android.speech.extra.LANGUAGE", Locale.getDefault());
            intent.putExtra("android.speech.extra.PROMPT", getString(R.string.speak_to_text));
            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
            } catch (Exception unused) {
            }
        }
    }

    /* CODE  */
    @Override
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        if (n == REQUEST_CODE_SPEECH_INPUT && n2 == -1 && intent != null) {
            final ArrayList stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
            final int n3 = 0;
            String s;
            if (stringArrayListExtra != null) {
                s = (String) stringArrayListExtra.get(0);
            } else {
                s = null;
            }
            if (s != null) {
                final Object value = stringArrayListExtra.get(0);
                final List split = new Regex("").split((CharSequence) value, 0);
                List list = null;
                Label_0195:
                {
                    if (!split.isEmpty()) {
                        final ListIterator listIterator = split.listIterator(split.size());
                        while (listIterator.hasPrevious()) {
                            if (((CharSequence) listIterator.previous()).length() != 0) {
                                list = CollectionsKt.take((Iterable) split, listIterator.nextIndex() + 1);
                                break Label_0195;
                            }
                        }
                    }
                    list = CollectionsKt.emptyList();
                }
                final String[] array = (String[]) list.toArray(new String[0]);
                final String[] array2 = array;
                for (int length = array2.length, i = n3; i < length; ++i) {
                    String str;
                    if (Intrinsics.areEqual((Object) (str = array2[i]), (Object) " ")) {
                        str = "%20";
                    }
                    CastClient.INSTANCE.sendRemoteKey(RokuKey.LIT_ + str);
                }
            }
        }
        super.onActivityResult(n, n2, intent);
    }

}
