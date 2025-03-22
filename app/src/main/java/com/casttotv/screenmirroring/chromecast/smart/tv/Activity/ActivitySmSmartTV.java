package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;

import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivitySmSmartTvBinding;

public class ActivitySmSmartTV extends ActivityBase {

    public ActivitySmSmartTvBinding binding;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivitySmSmartTvBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AdsManager.displaySmallNativeAd(this, findViewById(R.id.llNativeSmall), findViewById(R.id.llAds));

        configureBackPressedBehavior();
        buttonClickListener();
    }

    private void buttonClickListener() {
        binding.startMirroringTxt.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                openScreenMirroringSettings();
            }
        });
    }

    public void openScreenMirroringSettings() {
        try {
            startActivity(new Intent("android.settings.WIFI_DISPLAY_SETTINGS"));
        } catch (ActivityNotFoundException e2) {
            e2.printStackTrace();
            try {
                startActivity(new Intent("android.settings.CAST_SETTINGS"));
            } catch (Exception exception) {
                Toast.makeText(this, "Device not supported", Toast.LENGTH_LONG).show();
            }
        }
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
