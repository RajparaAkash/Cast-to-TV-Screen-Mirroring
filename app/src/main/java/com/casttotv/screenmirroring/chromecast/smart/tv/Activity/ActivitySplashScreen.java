package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.GoogleMobileAdsConsentManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.MyApplication;
import com.casttotv.screenmirroring.chromecast.smart.tv.Preferences.PrefHelper;
import com.casttotv.screenmirroring.chromecast.smart.tv.Preferences.PreferenceApp;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.LocaleHelper;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.NetworkUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.PermissionUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Utils;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivitySplashScreenBinding;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Objects;

public class ActivitySplashScreen extends AppCompatActivity {

    private GoogleMobileAdsConsentManager mobileAdsConsentManager;
    public ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Utils.fullScreenDarkStatusBar(this);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configureBackPressedBehavior();

        if (NetworkUtils.isInternetAvailable(this)) {
            initializeData();
        } else {
            displayNoConnectionDialog();
        }
    }

    private void displayNoConnectionDialog() {
        Dialog noConnectionDialog = new Dialog(this);

        ViewGroup root = (ViewGroup) Objects.requireNonNull(noConnectionDialog.getWindow()).getDecorView();
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_no_connection, root, false);

        noConnectionDialog.setContentView(dialogView);
        noConnectionDialog.setCancelable(false);

        Window window = noConnectionDialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setStatusBarColor(Color.WHITE);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        MaterialCardView connectWifiButton = dialogView.findViewById(R.id.connectWifiCard);
        connectWifiButton.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                if (NetworkUtils.isInternetAvailable(ActivitySplashScreen.this)) {
                    noConnectionDialog.dismiss();
                    initializeData();
                } else {
                    Toast.makeText(ActivitySplashScreen.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        noConnectionDialog.show();
    }

    private void initializeData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataReference = database.getReference("data");
        dataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    Object data = dataSnapshot.getValue(Object.class);
                    String jsonData = new Gson().toJson(data);
                    Log.d("TAG", "Data fetched: " + jsonData);

//                    if (BuildConfig.DEBUG) {
//                        PrefHelper.getInstance().setAdsData("{\"app_v\":0,\"inter_count\":2,\"g_r_tag\":\"ca-app-pub-3940256099942544/5224354917\",\"g_b_tag\":\"ca-app-pub-3940256099942544/9214589741\",\"app_a\":1,\"g_n_tag\":\"ca-app-pub-3940256099942544/2247696110\",\"g_ao_tag\":\"ca-app-pub-3940256099942544/9257395921\",\"t_sec\":30,\"g_n1_tag\":\"ca-app-pub-3940256099942544/2247696110\",\"g_i_tag\":\"ca-app-pub-3940256099942544/1033173712\"}");
//                    } else {
                    PrefHelper.getInstance().setAdsData(jsonData);
//                    }

                    proceedToNextActivity();
                } catch (JsonSyntaxException e) {
                    Log.e("TAG", "JSON parsing error", e);
                    proceedToNextActivity();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("TAG", "Data fetch cancelled", error.toException());
                proceedToNextActivity();
            }
        });
    }

    private void proceedToNextActivity() {
        try {
            new Thread(
                    () -> {
                        // Initialize the Google Mobile Ads SDK on a background thread.
                        MobileAds.initialize(this, initializationStatus -> {
                            mobileAdsConsentManager = GoogleMobileAdsConsentManager.getInstance(getApplicationContext());
                            mobileAdsConsentManager.gatherConsent(this, consentError -> {
                                if (consentError != null) {
                                    Log.e("TAG", "Consent error: " + consentError.getMessage());
                                }

                                if (mobileAdsConsentManager.canRequestAds()) {
                                    MyApplication.canRequestAds = true;
                                    loadAndShowInterstitial();
                                } else {
                                    navigateToDashboard();
                                }
                            });
                        });
                    }).start();
        } catch (Exception e) {
            Log.e("TAG", "Error initializing ads", e);
            navigateToDashboard();
        }
    }

    private void loadAndShowInterstitial() {
        new AdsManager().showInterstitialSplash(this, this::navigateToDashboard, findViewById(R.id.llAdaptiveBanner));
    }

    private void navigateToDashboard() {
        Intent intent;
        if (PreferenceApp.isFirstTime()) {
            intent = new Intent(this, ActivityLanguage.class);
        } else if (!PermissionUtils.isStoragePermissionsGranted(this)) {
            intent = new Intent(this, ActivityPermission.class);
        } else {
            intent = new Intent(this, ActivityDashboard.class);
        }
        startActivity(intent);
        finish();
    }

    private void configureBackPressedBehavior() {
        OnBackPressedCallback pressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

            }
        };
        getOnBackPressedDispatcher().addCallback(this, pressedCallback);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LocaleHelper.onAttach(context));
    }
}