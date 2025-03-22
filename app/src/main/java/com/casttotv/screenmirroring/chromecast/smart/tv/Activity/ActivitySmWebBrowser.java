package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.projection.MediaProjectionManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs.DialogDisconnectTv;
import com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser.AppNotifications;
import com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser.NetUtil;
import com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser.ScreenCapturePermission;
import com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser.ScreenRecordingService;
import com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser.ScreenServiceConnector;
import com.casttotv.screenmirroring.chromecast.smart.tv.MirrorWebBrowser.SettingsManagerActivity;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivitySmWebBrowserBinding;

public class ActivitySmWebBrowser extends ActivityBase {

    private ScreenRecordingService screenRecordingService;
    private ScreenCapturePermission screenCapturePermission;
    private ScreenServiceConnector screenServiceConnector;
    private ConnectivityManager.NetworkCallback networkCallback;
    private boolean isScreenSharingActive = false;

    private ActivitySmWebBrowserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySmWebBrowserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AdsManager.displaySmallNativeAd(this, findViewById(R.id.llNativeSmall), findViewById(R.id.llAds));

        configureBackPressedBehavior();
        requestNotificationPermission();
        AppNotifications.createNotificationChannel(this);

        buttonClickListener();
        registerWifiStateReceiver();
        connectToScreenRecordingService();
    }

    private void registerWifiStateReceiver() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                runOnUiThread(() -> updateIpAddressView());
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                runOnUiThread(() -> updateIpAddressView());
            }
        };

        if (connectivityManager != null) {
            NetworkRequest networkRequest = new NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .build();
            connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
        }
    }

    private void buttonClickListener() {
        binding.mirroringTxt.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                if (isScreenSharingActive) {
                    stopScreenSharing();
                } else {
                    if (TVConnectUtils.getInstance().isConnected()) {
                        new DialogDisconnectTv(ActivitySmWebBrowser.this).show();
                    } else {
                        startScreenCapture();
                    }
                }
            }
        });

        binding.ipCopyImg.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                String ipAddress = binding.ipTxt.getText().toString();
                if (!ipAddress.isEmpty()) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("IP Address", ipAddress);
                    if (clipboard != null) {
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(ActivitySmWebBrowser.this, "Copied", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivitySmWebBrowser.this, "Failed to copy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startScreenCapture() {
        MediaProjectionManager mediaProjectionManager =
                (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);

        if (mediaProjectionManager != null) {
            Intent screenCaptureIntent = mediaProjectionManager.createScreenCaptureIntent();
            screenCaptureLauncher.launch(screenCaptureIntent);
        } else {
            Log.e("TAG", "MediaProjectionManager is null");
        }
    }

    private final ActivityResultLauncher<Intent> screenCaptureLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    handleScreenCapturePermission(result.getResultCode(), result.getData());
                } else {
                    Log.d("TAG", "User denied screen sharing permission");
                    isScreenSharingActive = false;
                    binding.mirroringTxt.setText(R.string.start_mirroring);
                }
            });

    private void stopScreenSharing() {
        if (screenRecordingService != null) {
            screenRecordingService.stopScreenRecording();
            screenCapturePermission = null;

            isScreenSharingActive = false;
            binding.mirroringTxt.setText(R.string.start_mirroring);
            finish();
        }
    }

    private void handleScreenCapturePermission(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (TVConnectUtils.getInstance().isConnected()) {
                Toast.makeText(this, "First, disconnect your screen casting in smart TV", Toast.LENGTH_SHORT).show();
            } else if (TVConnectUtils.getInstance().isConnectWeb) {
                Toast.makeText(this, "First, disconnect your screen casting in web browser", Toast.LENGTH_SHORT).show();
            } else {
                screenCapturePermission = new ScreenCapturePermission(resultCode, data);

                if (screenRecordingService != null) {
                    screenRecordingService.startScreenRecording(screenCapturePermission);
                }

                isScreenSharingActive = true;
                binding.mirroringTxt.setText(R.string.stop_mirroring);
                return;
            }
        }
        isScreenSharingActive = false;
        binding.mirroringTxt.setText(R.string.start_mirroring);
    }

    private void updateIpAddressView() {
        String ipAddress = NetUtil.getIpAddress(this);
        int webServerPort = SettingsManagerActivity.getInstance(this).getWebServerPort();

        if (ipAddress != null) {
            binding.ipTxt.setText("http://" + ipAddress + ":" + webServerPort);
            binding.ipTxt.setVisibility(View.VISIBLE);
            binding.ipCopyImg.setVisibility(View.VISIBLE);
        } else {
            binding.ipTxt.setVisibility(View.GONE);
            binding.ipCopyImg.setVisibility(View.GONE);
        }
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
    }

    private void connectToScreenRecordingService() {
        screenServiceConnector = new ScreenServiceConnector(this) {
            @Override
            public void onServiceConnected(ScreenRecordingService service) {
                screenRecordingService = service;

                if (service.hasStreamingStarted()) {
                    isScreenSharingActive = true;
                    binding.mirroringTxt.setText(R.string.stop_mirroring);
                } else {
                    isScreenSharingActive = false;
                    binding.mirroringTxt.setText(R.string.start_mirroring);
                }
            }
        };
        screenServiceConnector.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateIpAddressView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (screenServiceConnector != null) {
            if (screenRecordingService != null && screenRecordingService.hasStreamingStarted()) {
                screenServiceConnector.disconnect();
            } else {
                screenServiceConnector.stopService();
            }
        }
        if (networkCallback != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                connectivityManager.unregisterNetworkCallback(networkCallback);
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