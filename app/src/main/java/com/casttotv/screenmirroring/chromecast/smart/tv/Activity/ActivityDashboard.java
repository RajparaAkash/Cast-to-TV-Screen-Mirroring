package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;

import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs.DialogDisconnectTv;
import com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio.AudioActivity;
import com.casttotv.screenmirroring.chromecast.smart.tv.FileImage.ImageActivity;
import com.casttotv.screenmirroring.chromecast.smart.tv.FileVideo.VideoActivity;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MessageEvent;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.InAppUpdate;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Utils;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivityDashboardBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ActivityDashboard extends ActivityBase {

    private ActivityDashboardBinding binding;
    private InAppUpdate inAppUpdate;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Utils.fullScreenLightStatusBar(this);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AdsManager.displaySmallNativeAd(this, findViewById(R.id.llNativeSmall), findViewById(R.id.llAds));

        configureBackPressedBehavior();
        EventBus.getDefault().register(this);
        buttonClickListener();

        inAppUpdate = new InAppUpdate(this);
        inAppUpdate.checkForAppUpdate();
    }

    private void buttonClickListener() {
        binding.premiumLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                startActivity(new Intent(ActivityDashboard.this, ActivityPremium.class));
            }
        });

        binding.settingLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                AdsManager.displayTimeBasedInterstitialAd(ActivityDashboard.this, () -> {
                    startActivity(new Intent(ActivityDashboard.this, ActivitySetting.class));
                });
            }
        });

        binding.connectLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                if (TVConnectUtils.getInstance().isConnected()) {
                    new DialogDisconnectTv(ActivityDashboard.this).show();
                } else {
                    navigateWithAd(ActivityConnect.class);
                }
            }
        });

        binding.screenMirroringLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                checkNotificationPermission(() ->
                        navigateWithAd(ActivityScreenMirroring.class)
                );
            }
        });

        binding.remoteControlLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                checkNotificationPermission(() ->
                        navigateWithAd(ActivityRemoteControl.class)
                );
            }
        });

        binding.imageLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                checkNotificationPermission(() ->
                        navigateWithAd(ImageActivity.class)
                );
            }
        });

        binding.audioLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                checkNotificationPermission(() ->
                        navigateWithAd(AudioActivity.class)
                );
            }
        });

        binding.videoLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                checkNotificationPermission(() ->
                        navigateWithAd(VideoActivity.class)
                );
            }
        });
    }

    private void checkNotificationPermission(Runnable onPermissionGranted) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Dexter.withContext(this)
                    .withPermission(Manifest.permission.POST_NOTIFICATIONS)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            onPermissionGranted.run();
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            onPermissionGranted.run();
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    })
                    .check();
        } else {
            onPermissionGranted.run();
        }
    }

    private void navigateWithAd(Class<?> activityClass) {
        AdsManager.displayTimeBasedInterstitialAd(ActivityDashboard.this, () ->
                startActivity(new Intent(ActivityDashboard.this, activityClass))
        );
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        if (messageEvent.getMessage().contains("KEY_CONNECT")) {
            if (TVConnectUtils.getInstance().isConnected()) {
                binding.connectImg.setImageResource(R.drawable.db_connected_img);
                if (TVConnectUtils.getInstance().getConnectableDevice() != null && TVConnectUtils.getInstance().getConnectableDevice().getModelName() != null) {
                    binding.connect1Txt.setText(TVConnectUtils.getInstance().getConnectableDevice().getModelName());
                } else {
                    binding.connect1Txt.setText(getString(R.string.chromecast));
                }
                binding.connect2Txt.setText(getString(R.string.tap_to_disconnect));
            } else {
                binding.connectImg.setImageResource(R.drawable.db_disconnect_img);
                binding.connect1Txt.setText(getString(R.string.no_device_connection));
                binding.connect2Txt.setText(getString(R.string.tap_to_connect));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        inAppUpdate.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inAppUpdate.onActivityResult(requestCode, resultCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        inAppUpdate.onDestroy();
    }

    private void configureBackPressedBehavior() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(ActivityDashboard.this, ActivityAppRate.class));
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}