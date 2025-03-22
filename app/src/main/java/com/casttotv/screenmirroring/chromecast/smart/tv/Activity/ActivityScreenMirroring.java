package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;

import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivityScreenMirroringBinding;

public class ActivityScreenMirroring extends ActivityBase {

    public ActivityScreenMirroringBinding binding;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivityScreenMirroringBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AdsManager.displaySmallNativeAd(this, findViewById(R.id.llNativeSmall), findViewById(R.id.llAds));

        configureBackPressedBehavior();
        buttonClickListener();
        startReviewFlow();
    }

    private void buttonClickListener() {
        binding.smSmartTvLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                AdsManager.displayTimeBasedInterstitialAd(ActivityScreenMirroring.this, () -> {
                    startActivity(new Intent(ActivityScreenMirroring.this, ActivitySmSmartTV.class));
                });
            }
        });

        binding.smDirectMirrorLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                AdsManager.displayTimeBasedInterstitialAd(ActivityScreenMirroring.this, () -> {
                    startActivity(new Intent(ActivityScreenMirroring.this, ActivitySmDirectMirror.class));
                });
            }
        });

        binding.smWebBrowserLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                AdsManager.displayTimeBasedInterstitialAd(ActivityScreenMirroring.this, () -> {
                    startActivity(new Intent(ActivityScreenMirroring.this, ActivitySmWebBrowser.class));
                });
            }
        });
    }

    private void configureBackPressedBehavior() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        binding.backLay.setOnClickListener(view -> {
            callback.handleOnBackPressed();
        });
    }
}
