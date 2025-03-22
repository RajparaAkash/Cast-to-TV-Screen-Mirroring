package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;

import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.BuildConfig;
import com.casttotv.screenmirroring.chromecast.smart.tv.Preferences.PreferenceApp;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Utils;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivitySettingBinding;

import java.util.Locale;

public class ActivitySetting extends ActivityBase {

    public ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AdsManager.displaySmallNativeAd(this, findViewById(R.id.llNativeSmall), findViewById(R.id.llAds));

        binding.versionTxt.setText(String.format("%s %s", getString(R.string.version), BuildConfig.VERSION_NAME));

        Locale locale = new Locale(PreferenceApp.get_LanguageCode());
        binding.languageTxt.setText(String.format("%s", locale.getDisplayLanguage(locale)));

        binding.vibrateCheckBox.setChecked(PreferenceApp.isVibrate());

        binding.vibrateCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            PreferenceApp.set_Vibrate(isChecked);
        });

        configureBackPressedBehavior();
        buttonClickListener();
    }

    private void buttonClickListener() {
        binding.goPremiumCard.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                startActivity(new Intent(ActivitySetting.this, ActivityPremium.class));
            }
        });

        binding.languageLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                AdsManager.displayTimeBasedInterstitialAd(ActivitySetting.this, () -> {
                    startActivity(new Intent(ActivitySetting.this, ActivityLanguage.class));
                });
            }
        });

        binding.rateUsLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                rateApp();
            }
        });

        binding.shareLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                shareApp();
            }
        });

        binding.privacyLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                Utils.LinkOpen(ActivitySetting.this, "https://sites.google.com/view/unimotefortv/home");
            }
        });
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

    private void shareApp() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            e.printStackTrace();
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