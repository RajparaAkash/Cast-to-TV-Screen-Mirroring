package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.core.content.ContextCompat;

import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivityAppRateBinding;

public class ActivityAppRate extends ActivityBase {

    public ActivityAppRateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppRateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AdsManager.displayLargeNativeAd(this, findViewById(R.id.llNativeLarge), findViewById(R.id.llAds));

        configureBackPressedBehavior();
        buttonClickListener();
    }

    private void buttonClickListener() {
        binding.rateStarImg01.setOnClickListener(v -> {
            unselectAllStar();
            binding.rateStarImg01.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            rateApp();
        });

        binding.rateStarImg02.setOnClickListener(v -> {
            unselectAllStar();
            binding.rateStarImg01.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            binding.rateStarImg02.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            rateApp();
        });

        binding.rateStarImg03.setOnClickListener(v -> {
            unselectAllStar();
            binding.rateStarImg01.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            binding.rateStarImg02.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            binding.rateStarImg03.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            rateApp();
        });

        binding.rateStarImg04.setOnClickListener(v -> {
            unselectAllStar();
            binding.rateStarImg01.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            binding.rateStarImg02.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            binding.rateStarImg03.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            binding.rateStarImg04.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            rateApp();
        });

        binding.rateStarImg05.setOnClickListener(v -> {
            unselectAllStar();
            binding.rateStarImg01.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            binding.rateStarImg02.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            binding.rateStarImg03.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            binding.rateStarImg04.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            binding.rateStarImg05.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_selected_img));
            rateApp();
        });

        binding.yesTxt.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                rateApp();
            }
        });

        binding.noTxt.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                AdsManager.displayTimeBasedInterstitialAd(ActivityAppRate.this, () -> {
                    startActivity(new Intent(ActivityAppRate.this, ActivityAppExit.class));
                    finish();
                });
            }
        });
    }

    public void unselectAllStar() {
        binding.rateStarImg01.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_unselected_img));
        binding.rateStarImg02.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_unselected_img));
        binding.rateStarImg03.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_unselected_img));
        binding.rateStarImg04.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_unselected_img));
        binding.rateStarImg05.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.rate_star_unselected_img));
    }

    public void rateApp() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
            startActivity(intent);
        } catch (Exception e) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + getPackageName()));
            startActivity(intent);
        }
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