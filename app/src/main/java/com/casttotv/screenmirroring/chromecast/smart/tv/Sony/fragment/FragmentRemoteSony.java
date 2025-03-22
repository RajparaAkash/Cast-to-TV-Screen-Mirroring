package com.casttotv.screenmirroring.chromecast.smart.tv.Sony.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.MyApplication;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.Common;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.OnSwipeTouchListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.FragmentRemoteSonyBinding;
import com.casttotv.screenmirroring.chromecast.smart.tv.Sony.RetrofitClient;
import com.connectsdk.etc.helper.HttpMessage;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentRemoteSony extends Fragment implements View.OnClickListener {
    public static final Companion Companion = new Companion(null);
    public String cookie = "";
    public String deviceIp = "";
    FragmentRemoteSonyBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        binding = FragmentRemoteSonyBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        String string;
        super.onViewCreated(view, bundle);
        deviceIp = TVConnectUtils.getInstance().getConnectableDevice().getIpAddress();
        Bundle arguments = getArguments();
        if (!(arguments == null || (string = arguments.getString("cookie")) == null)) {
            cookie = string;
        }
        Context requireContext = requireContext();

        FragmentRemoteSony fragmentRemoteSony = this;
        binding.ibBack.setOnClickListener(fragmentRemoteSony);
        binding.ibHome.setOnClickListener(fragmentRemoteSony);
        binding.ibSetting.setOnClickListener(fragmentRemoteSony);
        binding.ibPower.setOnClickListener(fragmentRemoteSony);
        binding.ibMute.setOnClickListener(fragmentRemoteSony);
        binding.ibNumpad.setOnClickListener(fragmentRemoteSony);
        binding.ibRemote.setOnClickListener(fragmentRemoteSony);
        binding.ibTouchpad.setOnClickListener(fragmentRemoteSony);
        binding.btnChannelUp.setOnClickListener(fragmentRemoteSony);
        binding.btnChannelDown.setOnClickListener(fragmentRemoteSony);
        binding.btnVolUp.setOnClickListener(fragmentRemoteSony);
        binding.btnVolDown.setOnClickListener(fragmentRemoteSony);
        binding.dpad.upButton.setOnClickListener(fragmentRemoteSony);
        binding.dpad.rightButton.setOnClickListener(fragmentRemoteSony);
        binding.dpad.downButton.setOnClickListener(fragmentRemoteSony);
        binding.dpad.leftButton.setOnClickListener(fragmentRemoteSony);
        binding.dpad.okButton.setOnClickListener(fragmentRemoteSony);
        binding.ibForward.setOnClickListener(fragmentRemoteSony);
        binding.ibBackward.setOnClickListener(fragmentRemoteSony);
        binding.ibPrev.setOnClickListener(fragmentRemoteSony);
        binding.ibStop.setOnClickListener(fragmentRemoteSony);
        binding.ibPause.setOnClickListener(fragmentRemoteSony);
        binding.ibPlay.setOnClickListener(fragmentRemoteSony);
        binding.ibNext.setOnClickListener(fragmentRemoteSony);
        binding.btnOne.setOnClickListener(fragmentRemoteSony);
        binding.btnTwo.setOnClickListener(fragmentRemoteSony);
        binding.btnThree.setOnClickListener(fragmentRemoteSony);
        binding.btnFour.setOnClickListener(fragmentRemoteSony);
        binding.btnFive.setOnClickListener(fragmentRemoteSony);
        binding.btnSix.setOnClickListener(fragmentRemoteSony);
        binding.btnSeven.setOnClickListener(fragmentRemoteSony);
        binding.btnEight.setOnClickListener(fragmentRemoteSony);
        binding.btnNine.setOnClickListener(fragmentRemoteSony);
        binding.btnZero.setOnClickListener(fragmentRemoteSony);
        binding.ivTouchpad.setOnTouchListener(new OnSwipeTouchListener(requireContext) {
            @Override
            public void onSingleTap() {
                sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAABlAw==", cookie);
            }

            @Override
            public void onSwipeRight() {
                sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAAzAw==", cookie);
            }

            @Override
            public void onSwipeLeft() {
                sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAA0Aw==", cookie);
            }

            @Override
            public void onSwipeTop() {
                sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAB0Aw==", cookie);
            }

            @Override
            public void onSwipeBottom() {
                sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAB1Aw==", cookie);
            }
        });
    }

    public void onClick(View view) {
        Common common = Common.INSTANCE;
        Context requireContext = requireContext();
        common.shakeItBaby(requireContext);
        if (Intrinsics.areEqual(view, binding.ibRemote)) {
            binding.ibRemote.setBackgroundResource(R.drawable.bg_tab_selected);
            binding.ibTouchpad.setBackgroundColor(0);
            binding.ibNumpad.setBackgroundColor(0);
            binding.dpadSection.setVisibility(View.VISIBLE);
            binding.flTouchpad.setVisibility(View.GONE);
            binding.lnNumpad.setVisibility(View.GONE);

            binding.ibRemote.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white));
            binding.ibTouchpad.setColorFilter(ContextCompat.getColor(requireContext(), R.color.remote_icon_tint));
            binding.ibNumpad.setColorFilter(ContextCompat.getColor(requireContext(), R.color.remote_icon_tint));
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibTouchpad)) {
            binding.ibRemote.setBackgroundColor(0);
            binding.ibTouchpad.setBackgroundResource(R.drawable.bg_tab_selected);
            binding.ibNumpad.setBackgroundColor(0);
            binding.dpadSection.setVisibility(View.GONE);
            binding.flTouchpad.setVisibility(View.VISIBLE);
            binding.lnNumpad.setVisibility(View.GONE);

            binding.ibRemote.setColorFilter(ContextCompat.getColor(requireContext(), R.color.remote_icon_tint));
            binding.ibTouchpad.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white));
            binding.ibNumpad.setColorFilter(ContextCompat.getColor(requireContext(), R.color.remote_icon_tint));
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibNumpad)) {
            binding.ibRemote.setBackgroundColor(0);
            binding.ibTouchpad.setBackgroundColor(0);
            binding.ibNumpad.setBackgroundResource(R.drawable.bg_tab_selected);
            binding.dpadSection.setVisibility(View.GONE);
            binding.flTouchpad.setVisibility(View.GONE);
            binding.lnNumpad.setVisibility(View.VISIBLE);

            binding.ibRemote.setColorFilter(ContextCompat.getColor(requireContext(), R.color.remote_icon_tint));
            binding.ibTouchpad.setColorFilter(ContextCompat.getColor(requireContext(), R.color.remote_icon_tint));
            binding.ibNumpad.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white));
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibBack)) {
            sendKeyWithRetrofit(deviceIp, "AAAAAgAAAJcAAAAjAw==", cookie);
            return;
        }
        if (!Intrinsics.areEqual(view, binding.ibHome)) {
            if (Intrinsics.areEqual(view, binding.ibSetting)) {
                sendKeyWithRetrofit(deviceIp, "AAAAAgAAAJcAAAA2Aw==", cookie);
                return;
            }
            if (!Intrinsics.areEqual(view, binding.ibPower)) {
                if (Intrinsics.areEqual(view, binding.ibMute)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAAUAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnChannelUp)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAAQAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnChannelDown)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAARAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnVolUp)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAASAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnVolDown)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAATAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.upButton)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAB0Aw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.rightButton)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAAzAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.downButton)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAB1Aw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.leftButton)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAA0Aw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.okButton)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAABlAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibForward)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAgAAAJcAAAB4Aw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibBackward)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAgAAAJcAAAB5Aw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibPrev)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAgAAAJcAAAA8Aw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibStop)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAgAAAJcAAAAYAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibPause)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAgAAAJcAAAAZAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibPlay)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAgAAAJcAAAAaAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibNext)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAgAAAJcAAAA9Aw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnOne)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAAAAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnTwo)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAABAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnThree)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAACAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnFour)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAADAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnFive)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAAEAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnSix)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAAFAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnSeven)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAAGAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnEight)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAAHAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnNine)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAAIAw==", cookie);
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnZero)) {
                    sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAAJAw==", cookie);
                }
            } else {
                sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAAAVAw==", cookie);
            }
        } else {
            sendKeyWithRetrofit(deviceIp, "AAAAAQAAAAEAAABgAw==", cookie);
        }
    }

    public void sendKeyWithRetrofit(String str, String str2, String str3) {
        Call<String> sendKeyWithCookie = RetrofitClient.getInstance(str).getMyApi().sendKeyWithCookie(RequestBody.Companion.create("<s:Envelope    xmlns:s='http://schemas.xmlsoap.org/soap/envelope/'    s:encodingStyle='http://schemas.xmlsoap.org/soap/encoding/'>    <s:Body><u:X_SendIRCC xmlns:u='urn:schemas-sony-com:service:IRCC:1'><IRCCCode>" + str2 + "</IRCCCode></u:X_SendIRCC></s:Body></s:Envelope>", MediaType.Companion.parse("text/xml")), HttpMessage.CONTENT_TYPE_TEXT_XML, "\"urn:schemas-sony-com:service:IRCC:1#X_SendIRCC\"", str3);
        if (sendKeyWithCookie != null) {
            sendKeyWithCookie.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                }

                @Override
                public void onFailure(@NonNull Call<String> call, @NonNull Throwable th) {
                    Toast.makeText(getContext(), th.toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public FragmentRemoteSony instance(Intent intent) {
            Bundle bundle = new Bundle();
            bundle.putString("cookie", MyApplication.getInstance().myCookie);
            bundle.putString("deviceIp", intent.getStringExtra("deviceIp"));
            FragmentRemoteSony fragmentRemoteSony = new FragmentRemoteSony();
            fragmentRemoteSony.setArguments(bundle);
            return fragmentRemoteSony;
        }
    }
}
