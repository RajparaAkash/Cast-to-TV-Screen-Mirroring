package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;

import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivityAppExitBinding;

public class ActivityAppExit extends ActivityBase {

    public ActivityAppExitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppExitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AdsManager.displayLargeNativeAd(this, findViewById(R.id.llNativeLarge), findViewById(R.id.llAds));

        configureBackPressedBehavior();
        buttonClickListener();
    }

    private void buttonClickListener() {
        binding.yesTxt.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                finishAffinity();
                finishAndRemoveTask();
                System.gc();
                System.exit(0);
            }
        });

        binding.noTxt.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                finish();
            }
        });
    }

    private void configureBackPressedBehavior() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}