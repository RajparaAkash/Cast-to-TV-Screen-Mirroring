package com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.fragment;

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
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.FragmentRemoteSamSungBinding;
import com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.WebSocketUtils;

import kotlin.jvm.internal.Intrinsics;

public class FragmentRemoteSamSung extends Fragment implements View.OnClickListener {
    FragmentRemoteSamSungBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        binding = FragmentRemoteSamSungBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        super.onViewCreated(view, bundle);


        /*if (MyApplication.getInstance().isEndOneDay()) {
            binding.ivPremium1.setVisibility(View.VISIBLE);
            binding.ivPremium2.setVisibility(View.VISIBLE);
            binding.ivPremium3.setVisibility(View.VISIBLE);
        } else {
            binding.ivPremium1.setVisibility(View.GONE);
            binding.ivPremium2.setVisibility(View.GONE);
            binding.ivPremium3.setVisibility(View.GONE);
        }*/


        FragmentRemoteSamSung fragmentRemoteSamSung = this;
        binding.ibNumpad.setOnClickListener(fragmentRemoteSamSung);
        binding.ibRemote.setOnClickListener(fragmentRemoteSamSung);
        binding.ibTouchpad.setOnClickListener(fragmentRemoteSamSung);
        binding.ibBack.setOnClickListener(fragmentRemoteSamSung);
        binding.ibExit.setOnClickListener(fragmentRemoteSamSung);
        binding.ibHome.setOnClickListener(fragmentRemoteSamSung);
        binding.ibPower.setOnClickListener(fragmentRemoteSamSung);
        binding.btnVolUp.setOnClickListener(fragmentRemoteSamSung);
        binding.btnVolDown.setOnClickListener(fragmentRemoteSamSung);
        binding.ibMute.setOnClickListener(fragmentRemoteSamSung);
        binding.btnChannelUp.setOnClickListener(fragmentRemoteSamSung);
        binding.btnChannelDown.setOnClickListener(fragmentRemoteSamSung);
        binding.ivRed.setOnClickListener(fragmentRemoteSamSung);
        binding.ivGreen.setOnClickListener(fragmentRemoteSamSung);
        binding.ivYellow.setOnClickListener(fragmentRemoteSamSung);
        binding.ivBlue.setOnClickListener(fragmentRemoteSamSung);
        binding.dpad.upButton.setOnClickListener(fragmentRemoteSamSung);
        binding.dpad.leftButton.setOnClickListener(fragmentRemoteSamSung);
        binding.dpad.downButton.setOnClickListener(fragmentRemoteSamSung);
        binding.dpad.rightButton.setOnClickListener(fragmentRemoteSamSung);
        binding.dpad.okButton.setOnClickListener(fragmentRemoteSamSung);
        binding.ibPrev.setOnClickListener(fragmentRemoteSamSung);
        binding.ibPlay.setOnClickListener(fragmentRemoteSamSung);
        binding.ibPause.setOnClickListener(fragmentRemoteSamSung);
        binding.ibNext.setOnClickListener(fragmentRemoteSamSung);
        binding.ibStop.setOnClickListener(fragmentRemoteSamSung);
        binding.btnOne.setOnClickListener(fragmentRemoteSamSung);
        binding.btnTwo.setOnClickListener(fragmentRemoteSamSung);
        binding.btnThree.setOnClickListener(fragmentRemoteSamSung);
        binding.btnFour.setOnClickListener(fragmentRemoteSamSung);
        binding.btnFive.setOnClickListener(fragmentRemoteSamSung);
        binding.btnSix.setOnClickListener(fragmentRemoteSamSung);
        binding.btnSeven.setOnClickListener(fragmentRemoteSamSung);
        binding.btnEight.setOnClickListener(fragmentRemoteSamSung);
        binding.btnNine.setOnClickListener(fragmentRemoteSamSung);
        binding.btnZero.setOnClickListener(fragmentRemoteSamSung);
        binding.ivTouchpad.setOnTouchListener(new OnSwipeTouchListener(requireContext()) {
            @Override
            public void onSingleTap() {
                sendKey("KEY_ENTER");
            }

            @Override
            public void onSwipeRight() {
                sendKey("KEY_RIGHT");
            }

            @Override
            public void onSwipeLeft() {
                sendKey("KEY_LEFT");
            }

            @Override
            public void onSwipeTop() {
                sendKey("KEY_UP");
            }

            @Override
            public void onSwipeBottom() {
                sendKey("KEY_DOWN");
            }
        });
    }

    public void sendKey(String str) {
        WebSocketUtils.INSTANCE.sendKey(str);
    }

    public void onClick(View view) {
        Common common = Common.INSTANCE;
        common.shakeItBaby(requireActivity());
        if (!WebSocketUtils.INSTANCE.isConnected()) {
            Common common2 = Common.INSTANCE;
            common2.gotoDevicePicker(requireActivity());
            return;
        }
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
            sendKey("KEY_RETURN");
            return;
        }
        if (!Intrinsics.areEqual(view, binding.ibHome)) {
            if (Intrinsics.areEqual(view, binding.ibExit)) {
                sendKey("KEY_EXIT");
                return;
            }
            if (!Intrinsics.areEqual(view, binding.ibPower)) {
                if (Intrinsics.areEqual(view, binding.btnVolUp)) {
                    sendKey("KEY_VOLUP");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnVolDown)) {
                    sendKey("KEY_VOLDOWN");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnChannelUp)) {
                    sendKey("KEY_CHUP");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnChannelDown)) {
                    sendKey("KEY_CHDOWN");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibMute)) {
                    sendKey("KEY_MUTE");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ivRed)) {
                    sendKey("KEY_RED");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ivGreen)) {
                    sendKey("KEY_GREEN");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ivYellow)) {
                    sendKey("KEY_YELLOW");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ivBlue)) {
                    sendKey("KEY_CYAN");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.upButton)) {
                    sendKey("KEY_UP");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.leftButton)) {
                    sendKey("KEY_LEFT");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.downButton)) {
                    sendKey("KEY_DOWN");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.rightButton)) {
                    sendKey("KEY_RIGHT");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.okButton)) {
                    sendKey("KEY_ENTER");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibPrev)) {
                    sendKey("KEY_REWIND");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibPlay)) {
                    sendKey("KEY_PLAY");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibPause)) {
                    sendKey("KEY_PAUSE");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibNext)) {
                    sendKey("KEY_FF");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibStop)) {
                    sendKey("KEY_STOP");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnOne)) {
                    sendKey("KEY_1");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnTwo)) {
                    sendKey("KEY_2");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnThree)) {
                    sendKey("KEY_3");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnFour)) {
                    sendKey("KEY_4");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnFive)) {
                    sendKey("KEY_5");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnSix)) {
                    sendKey("KEY_6");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnSeven)) {
                    sendKey("KEY_7");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnEight)) {
                    sendKey("KEY_8");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnNine)) {
                    sendKey("KEY_9");
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnZero)) {
                    sendKey("KEY_0");
                }
            } else {
                sendKey("KEY_POWER");
            }
        } else {
            sendKey("KEY_SOURCE");
        }
    }
}
