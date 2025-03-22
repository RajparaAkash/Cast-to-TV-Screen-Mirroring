package com.casttotv.screenmirroring.chromecast.smart.tv.Lg.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;

import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.Common;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.OnSwipeTouchListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.FragmentRemoteLGBinding;
import com.casttotv.screenmirroring.chromecast.smart.tv.Lg.LGTV;
import com.casttotv.screenmirroring.chromecast.smart.tv.Lg.LGTVSingleTon;

import kotlin.jvm.internal.Intrinsics;

public class RemoteLGFragment extends Fragment implements View.OnClickListener {
    public LGTV tvControlInstance;
    FragmentRemoteLGBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        binding = FragmentRemoteLGBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        LGTVSingleTon lGTVSingleTon = LGTVSingleTon.INSTANCE;
        tvControlInstance = lGTVSingleTon.getInstance(requireContext());
        if (tvControlInstance == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvControlInstance");
            tvControlInstance = null;
        }
        tvControlInstance.loadMainPreferences();


        /*if (MyApplication.getInstance().isEndOneDay()) {
            binding.ivPremium1.setVisibility(View.VISIBLE);
            binding.ivPremium2.setVisibility(View.VISIBLE);
            binding.ivPremium3.setVisibility(View.VISIBLE);
        } else {
            binding.ivPremium1.setVisibility(View.GONE);
            binding.ivPremium2.setVisibility(View.GONE);
            binding.ivPremium3.setVisibility(View.GONE);
        }*/


        RemoteLGFragment remoteLGFragment = this;
        binding.ibRemote.setOnClickListener(remoteLGFragment);
        binding.ibTouchpad.setOnClickListener(remoteLGFragment);
        binding.ibNumpad.setOnClickListener(remoteLGFragment);
        binding.ibBack.setOnClickListener(remoteLGFragment);
        binding.ibExit.setOnClickListener(remoteLGFragment);
        binding.ibHome.setOnClickListener(remoteLGFragment);
        binding.ibPower.setOnClickListener(remoteLGFragment);
        binding.btnVolUp.setOnClickListener(remoteLGFragment);
        binding.btnVolDown.setOnClickListener(remoteLGFragment);
        binding.ibMute.setOnClickListener(remoteLGFragment);
        binding.btnChannelUp.setOnClickListener(remoteLGFragment);
        binding.btnChannelDown.setOnClickListener(remoteLGFragment);
        binding.dpad.upButton.setOnClickListener(remoteLGFragment);
        binding.dpad.leftButton.setOnClickListener(remoteLGFragment);
        binding.dpad.downButton.setOnClickListener(remoteLGFragment);
        binding.dpad.rightButton.setOnClickListener(remoteLGFragment);
        binding.dpad.okButton.setOnClickListener(remoteLGFragment);
        binding.ibPrev.setOnClickListener(remoteLGFragment);
        binding.ibPause.setOnClickListener(remoteLGFragment);
        binding.ibPlay.setOnClickListener(remoteLGFragment);
        binding.ibNext.setOnClickListener(remoteLGFragment);
        binding.ibStop.setOnClickListener(remoteLGFragment);
        binding.btnOne.setOnClickListener(remoteLGFragment);
        binding.btnTwo.setOnClickListener(remoteLGFragment);
        binding.btnThree.setOnClickListener(remoteLGFragment);
        binding.btnFour.setOnClickListener(remoteLGFragment);
        binding.btnFive.setOnClickListener(remoteLGFragment);
        binding.btnSix.setOnClickListener(remoteLGFragment);
        binding.btnSeven.setOnClickListener(remoteLGFragment);
        binding.btnEight.setOnClickListener(remoteLGFragment);
        binding.btnNine.setOnClickListener(remoteLGFragment);
        binding.btnZero.setOnClickListener(remoteLGFragment);
        binding.ivTouchpad.setOnTouchListener(new OnSwipeTouchListener(requireContext()) {
            @Override
            public void onSingleTap() {
                if (tvControlInstance != null) {
                    tvControlInstance.send_key("OK", LGTV.KEY_INDEX.fromInt(43));
                }
            }

            @Override
            public void onSwipeRight() {
                if (tvControlInstance != null) {
                    tvControlInstance.send_key("Right", LGTV.KEY_INDEX.fromInt(42));
                }
            }

            @Override
            public void onSwipeLeft() {
                if (tvControlInstance != null) {
                    tvControlInstance.send_key("Left", LGTV.KEY_INDEX.fromInt(41));
                }
            }

            @Override
            public void onSwipeTop() {
                if (tvControlInstance != null) {
                    tvControlInstance.send_key("Up", LGTV.KEY_INDEX.fromInt(39));
                }
            }

            @Override
            public void onSwipeBottom() {
                if (tvControlInstance != null) {
                    tvControlInstance.send_key("Down", LGTV.KEY_INDEX.fromInt(40));
                }
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
        if (Intrinsics.areEqual(view, binding.ibBack)) {
            if (tvControlInstance != null) {
                tvControlInstance.send_key("Back", LGTV.KEY_INDEX.fromInt(38));
            }
            return;
        }
        if (!Intrinsics.areEqual(view, binding.ibHome)) {
            if (Intrinsics.areEqual(view, binding.ibExit)) {
                if (tvControlInstance != null) {
                    tvControlInstance.send_key("Exit", LGTV.KEY_INDEX.fromInt(44));
                }
                return;
            }
            if (!Intrinsics.areEqual(view, binding.ibPower)) {
                if (Intrinsics.areEqual(view, binding.btnVolUp)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Volume +", LGTV.KEY_INDEX.fromInt(24));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnVolDown)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Volume -", LGTV.KEY_INDEX.fromInt(25));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnChannelUp)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Channel +", LGTV.KEY_INDEX.CHANNEL_INCREASE);
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnChannelDown)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Channel -", LGTV.KEY_INDEX.CHANNEL_DECREASE);
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibMute)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Mute", LGTV.KEY_INDEX.fromInt(23));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.upButton)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Up", LGTV.KEY_INDEX.fromInt(39));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.leftButton)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Left", LGTV.KEY_INDEX.fromInt(41));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.downButton)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Down", LGTV.KEY_INDEX.fromInt(40));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.rightButton)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Right", LGTV.KEY_INDEX.fromInt(42));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.dpad.okButton)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("OK", LGTV.KEY_INDEX.fromInt(43));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibPrev)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Prev", LGTV.KEY_INDEX.fromInt(34));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibPause)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Pause", LGTV.KEY_INDEX.fromInt(29));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibPlay)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Play", LGTV.KEY_INDEX.fromInt(28));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibNext)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Next", LGTV.KEY_INDEX.fromInt(33));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.ibStop)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("Stop", LGTV.KEY_INDEX.fromInt(30));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnOne)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("1", LGTV.KEY_INDEX.fromInt(1));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnTwo)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key(ExifInterface.GPS_MEASUREMENT_2D, LGTV.KEY_INDEX.fromInt(2));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnThree)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key(ExifInterface.GPS_MEASUREMENT_3D, LGTV.KEY_INDEX.fromInt(3));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnFour)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("4", LGTV.KEY_INDEX.fromInt(4));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnFive)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("5", LGTV.KEY_INDEX.fromInt(5));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnSix)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("6", LGTV.KEY_INDEX.fromInt(6));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnSeven)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("7", LGTV.KEY_INDEX.fromInt(7));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnEight)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("8", LGTV.KEY_INDEX.fromInt(8));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnNine)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("9", LGTV.KEY_INDEX.fromInt(9));
                    }
                    return;
                }
                if (Intrinsics.areEqual(view, binding.btnZero)) {
                    if (tvControlInstance != null) {
                        tvControlInstance.send_key("0", LGTV.KEY_INDEX.fromInt(0));
                    }
                }
            } else {
                if (tvControlInstance != null) {
                    tvControlInstance.send_key("Off", LGTV.KEY_INDEX.fromInt(22));
                }
            }
        } else {
            if (tvControlInstance != null) {
                tvControlInstance.send_key("Home", LGTV.KEY_INDEX.fromInt(46));
            }
        }
    }
}
