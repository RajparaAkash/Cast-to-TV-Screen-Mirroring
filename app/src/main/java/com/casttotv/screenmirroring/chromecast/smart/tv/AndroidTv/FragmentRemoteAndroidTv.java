package com.casttotv.screenmirroring.chromecast.smart.tv.AndroidTv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.Common;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.OnSwipeTouchListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.FragmentRemoteAndroidTvBinding;
import com.github.kunal52.remote.Remotemessage;

import kotlin.jvm.internal.Intrinsics;

public class FragmentRemoteAndroidTv extends Fragment implements View.OnClickListener {
    public boolean isMute = false;
    FragmentRemoteAndroidTvBinding binding;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        binding = FragmentRemoteAndroidTvBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        super.onViewCreated(view, bundle);

        FragmentRemoteAndroidTv fragmentRemoteAndroidTv = this;
        binding.ibRemote.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ibTouchpad.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ibNumpad.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ibHome.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ibPower.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ivBack.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ivExit.setOnClickListener(fragmentRemoteAndroidTv);
        binding.dpad.upButton.setOnClickListener(fragmentRemoteAndroidTv);
        binding.dpad.rightButton.setOnClickListener(fragmentRemoteAndroidTv);
        binding.dpad.downButton.setOnClickListener(fragmentRemoteAndroidTv);
        binding.dpad.leftButton.setOnClickListener(fragmentRemoteAndroidTv);
        binding.dpad.okButton.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnVolUp.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnVolDown.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnChannelUp.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnChannelDown.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ivGuide.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ivMute.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ivUnmute.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ivKeyboard.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ivPlay.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ivPrev.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ivNext.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ivPause.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnZero.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnOne.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnTwo.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnThree.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnFour.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnFive.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnSix.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnSeven.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnEight.setOnClickListener(fragmentRemoteAndroidTv);
        binding.btnNine.setOnClickListener(fragmentRemoteAndroidTv);
        binding.ivTouchpad.setOnTouchListener(new OnSwipeTouchListener(requireContext()) {
            @Override
            public void onSingleTap() {
                AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_DPAD_CENTER);
            }

            @Override
            public void onSwipeRight() {
                AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_DPAD_RIGHT);
            }

            @Override
            public void onSwipeLeft() {
                AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_DPAD_LEFT);
            }

            @Override
            public void onSwipeTop() {
                AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_DPAD_UP);
            }

            @Override
            public void onSwipeBottom() {
                AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_DPAD_DOWN);
            }
        });
    }

    public void onClick(View view) {
        Common common = Common.INSTANCE;
        common.shakeItBaby(requireContext());
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
            binding.lnNumpad.setVisibility(View.GONE);
            binding.flTouchpad.setVisibility(View.VISIBLE);

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
        if (Intrinsics.areEqual(view, binding.ibHome)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_HOME);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ibPower)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_POWER);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ivBack)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_BACK);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ivExit)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_BACK);
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.upButton)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_DPAD_UP);
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.leftButton)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_DPAD_LEFT);
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.downButton)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_DPAD_DOWN);
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.rightButton)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_DPAD_RIGHT);
            return;
        }
        if (Intrinsics.areEqual(view, binding.dpad.okButton)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_DPAD_CENTER);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnVolUp)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_VOLUME_UP);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnVolDown)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_VOLUME_DOWN);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ivGuide)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_MENU);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ivMute)) {
            if (!isMute) {
                isMute = true;
                AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_MUTE);
            }
            return;
        }
        if (Intrinsics.areEqual(view, binding.ivUnmute)) {
            if (isMute) {
                isMute = false;
                AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_MUTE);
            }
            return;
        }
        if (Intrinsics.areEqual(view, binding.ivKeyboard)) {
            new DialogTextInputAndroidTv().show(getChildFragmentManager(), DialogTextInputAndroidTv.class.getName());
            return;
        }
        if (Intrinsics.areEqual(view, binding.ivPlay)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_MEDIA_PLAY);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ivPrev)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_MEDIA_PREVIOUS);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ivNext)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_MEDIA_NEXT);
            return;
        }
        if (Intrinsics.areEqual(view, binding.ivPause)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_MEDIA_PAUSE);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnChannelUp)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_CHANNEL_UP);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnChannelDown)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_CHANNEL_DOWN);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnZero)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_0);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnOne)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_1);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnTwo)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_2);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnThree)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_3);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnFour)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_4);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnFive)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_5);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnSix)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_6);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnSeven)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_7);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnEight)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_8);
            return;
        }
        if (Intrinsics.areEqual(view, binding.btnNine)) {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_9);
        }
    }
}
