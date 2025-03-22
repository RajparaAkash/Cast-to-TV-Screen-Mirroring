package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.casttotv.screenmirroring.chromecast.smart.tv.Adapter.TvDeviceAdapter;
import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVType;
import com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs.DialogPairingAlert;
import com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs.DialogPairingCode;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MessageEvent;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.TVObject;
import com.casttotv.screenmirroring.chromecast.smart.tv.Preferences.PreferenceApp;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Tracking;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivityConnectBinding;
import com.connectsdk.core.Util;
import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.device.ConnectableDeviceListener;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.discovery.DiscoveryManagerListener;
import com.connectsdk.service.DIALService;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.FireTVService;
import com.connectsdk.service.command.ServiceCommandError;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ActivityConnect extends ActivityBase implements DiscoveryManagerListener {

    public static String TAG = "ActivityConnect";
    public ActivityConnectBinding binding;

    public static String IpAddressFireTV = "";
    private TvDeviceAdapter tvDeviceAdapter;

    private DialogPairingAlert dialogPairingAlert;
    private DialogPairingCode dialogPairingCode;
    private Dialog connectingDialog;

    private final ArrayList<ConnectableDevice> connectableDevices = new ArrayList<>();
    private boolean isConnected = false;

    public ConnectableDeviceListener connectableDeviceListener = new ConnectableDeviceListener() {

        @Override
        public void onDeviceReady(ConnectableDevice connectableDevice) {
            String deviceName = connectableDevice.getFriendlyName() != null
                    ? connectableDevice.getFriendlyName()
                    : connectableDevice.getModelName();

            Log.d(TAG, "Device ready: " + deviceName);

            if (!isConnected) {
                connectDeviceReady(connectableDevice);
                isConnected = true;
            }
        }

        @Override
        public void onDeviceDisconnected(ConnectableDevice connectableDevice) {
            String deviceName = connectableDevice.getFriendlyName() != null
                    ? connectableDevice.getFriendlyName()
                    : connectableDevice.getModelName();

            Log.d(TAG, "Device disconnected: " + deviceName);

            try {
                String manufacturer = connectableDevice.getServiceDescription() != null
                        ? connectableDevice.getServiceDescription().getManufacturer()
                        : TVType.getTVType(connectableDevice);

                Tracking.connect(ActivityConnect.this, "fail", connectableDevice.getId(), manufacturer);
            } catch (Exception e) {
                Log.e("TAG", "Error tracking disconnection", e);
                Tracking.connect(ActivityConnect.this, "fail", connectableDevice.getId(), "");
            }
        }

        @Override
        public void onPairingRequired(ConnectableDevice connectableDevice, DeviceService deviceService, DeviceService.PairingType pairingType) {
            String deviceName = connectableDevice.getFriendlyName() != null
                    ? connectableDevice.getFriendlyName()
                    : connectableDevice.getModelName();

            Log.d(TAG, "Pairing required for: " + deviceName + " | Type: " + pairingType);

            try {
                switch (pairingType) {
                    case NONE:
                        Log.d(TAG, "No pairing required for: " + deviceName);
                        break;

                    case FIRST_SCREEN:
                        Log.d(TAG, "First-screen pairing required for: " + deviceName);
                        // Handle first-screen pairing logic if applicable
                        break;

                    case PIN_CODE:
                        if (dialogPairingAlert != null) dialogPairingAlert.show();
                        break;

                    case MIXED:
                        if (dialogPairingCode != null) dialogPairingCode.show();
                        break;

                    default:
                        Log.w(TAG, "Unhandled pairing type: " + pairingType);
                        break;
                }
            } catch (Exception e) {
                Log.e(TAG, "Error showing pairing dialog", e);
            }
        }

        @Override
        public void onCapabilityUpdated(ConnectableDevice connectableDevice, List<String> added, List<String> removed) {
            String deviceName = connectableDevice.getFriendlyName() != null
                    ? connectableDevice.getFriendlyName()
                    : connectableDevice.getModelName();

            Log.d(TAG, "Capabilities updated for: " + deviceName +
                    " | Added: " + added + " | Removed: " + removed);
        }

        @Override
        public void onConnectionFailed(ConnectableDevice connectableDevice, ServiceCommandError serviceCommandError) {
            String deviceName = connectableDevice.getFriendlyName() != null
                    ? connectableDevice.getFriendlyName()
                    : connectableDevice.getModelName();

            Log.e(TAG, "Connection failed: " + deviceName +
                    " | Error: " + serviceCommandError.getMessage());

            try {
                String manufacturer = connectableDevice.getServiceDescription() != null
                        ? connectableDevice.getServiceDescription().getManufacturer()
                        : TVType.getTVType(connectableDevice);

                Tracking.connect(ActivityConnect.this, "fail", connectableDevice.getId(), manufacturer);
            } catch (Exception e) {
                Log.e(TAG, "Error tracking connection failure", e);
            }

            connectFailed(connectableDevice);
        }
    };

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
                if (wifiState == WifiManager.WIFI_STATE_DISABLED) {
                    Toast.makeText(ActivityConnect.this, "Wi-Fi is disabled.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivityConnectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AdsManager.displaySmallNativeAd(this, findViewById(R.id.llNativeSmall), findViewById(R.id.llAds));

        configureBackPressedBehavior();
        dialogConnecting();
        initView();
        initData();

        binding.howToConnectLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                BottomSheetDialog bottomDialog = new BottomSheetDialog(ActivityConnect.this, R.style.BottomSheetDialogLight);
                View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_how_to_connect, null);
                bottomDialog.setContentView(bottomSheetView);
                bottomDialog.show();

                bottomDialog.findViewById(R.id.iGotItTxt).setOnClickListener(new ThrottleClickListener() {
                    @Override
                    public void onThrottleClick(View v) {
                        bottomDialog.dismiss();
                    }
                });
            }
        });

        binding.refreshLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                initData();
            }
        });

        startReviewFlow();
    }

    public void dialogConnecting() {
        try {
            connectingDialog = new Dialog(ActivityConnect.this);
            connectingDialog.setContentView(R.layout.dialog_connecting_tv);
            Objects.requireNonNull(connectingDialog.getWindow()).setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            connectingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            connectingDialog.setCanceledOnTouchOutside(false);
            connectingDialog.setCancelable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dismissConnectingDialog() {
        if (connectingDialog != null && connectingDialog.isShowing() && (!isDestroyed() || !isFinishing())) {
            connectingDialog.dismiss();
        }
    }

    private void initView() {
        DiscoveryManager.getInstance().addListener(this);

        binding.tvListRv.setLayoutManager(new LinearLayoutManager(this));
        tvDeviceAdapter = new TvDeviceAdapter(connectableDevice -> {
            try {
                if (connectingDialog != null) {
                    connectingDialog.show();
                }
                connectToDevice(connectableDevice);
            } catch (Exception e) {
                Log.e(TAG, "Error connecting to device", e);
            }
        });
        binding.tvListRv.setAdapter(tvDeviceAdapter);
        registerReceiverWifi();
    }

    private void initData() {
        connectableDevices.clear();
        connectableDevices.addAll(DiscoveryManager.getInstance().getCompatibleDevices().values());
        tvDeviceAdapter.setData(getArrayTV(connectableDevices));

        if (!DiscoveryManager.getInstance().getCompatibleDevices().values().isEmpty()) {
            binding.tvSearchingLay.setVisibility(View.GONE);
            binding.tvListLay.setVisibility(View.VISIBLE);
            binding.refreshLay.setVisibility(View.VISIBLE);
            return;
        }
        binding.tvSearchingLay.setVisibility(View.VISIBLE);
        binding.tvListLay.setVisibility(View.GONE);
        binding.refreshLay.setVisibility(View.GONE);
    }

    @Override
    public void onDeviceAdded(DiscoveryManager discoveryManager, ConnectableDevice connectableDevice) {
        String deviceId = connectableDevice.getId();
        Log.d(TAG, "onDeviceAdded :: " + deviceId);

        Util.runOnUI(() -> {
            // Refresh the device list
            List<ConnectableDevice> compatibleDevices = new ArrayList<>(DiscoveryManager.getInstance().getCompatibleDevices().values());
            connectableDevices.clear();
            connectableDevices.addAll(compatibleDevices);

            // Update adapter with new data
            tvDeviceAdapter.setData(getArrayTV(connectableDevices));

            // Update UI visibility based on device availability
            boolean hasDevices = !compatibleDevices.isEmpty();
            /*boolean hasDevices = !getArrayTV(connectableDevices).isEmpty();*/
            binding.tvSearchingLay.setVisibility(hasDevices ? View.GONE : View.VISIBLE);
            binding.tvListLay.setVisibility(hasDevices ? View.VISIBLE : View.GONE);
            binding.refreshLay.setVisibility(hasDevices ? View.VISIBLE : View.GONE);

            // Log the device count
            Log.d(TAG, "Updated device count: " + connectableDevices.size());
        });
    }

    @Override
    public void onDeviceUpdated(DiscoveryManager discoveryManager, ConnectableDevice connectableDevice) {
        String deviceId = connectableDevice.getId();
        Log.d(TAG, "onDeviceUpdated :: " + deviceId);

        Util.runOnUI(() -> {
            // Refresh the device list
            List<ConnectableDevice> compatibleDevices = new ArrayList<>(DiscoveryManager.getInstance().getCompatibleDevices().values());
            connectableDevices.clear();
            connectableDevices.addAll(compatibleDevices);

            // Update UI visibility based on device availability
            boolean hasDevices = !compatibleDevices.isEmpty();
            binding.tvSearchingLay.setVisibility(hasDevices ? View.GONE : View.VISIBLE);
            binding.tvListLay.setVisibility(hasDevices ? View.VISIBLE : View.GONE);
            binding.refreshLay.setVisibility(hasDevices ? View.VISIBLE : View.GONE);

            // Update adapter with new data
            tvDeviceAdapter.setData(getArrayTV(connectableDevices));
        });
    }

    @Override
    public void onDeviceRemoved(DiscoveryManager discoveryManager, ConnectableDevice connectableDevice) {
        String deviceId = connectableDevice.getId();
        Log.d(TAG, "onDeviceRemoved :: " + deviceId);

        Util.runOnUI(() -> {
            // Refresh the device list
            List<ConnectableDevice> compatibleDevices = new ArrayList<>(DiscoveryManager.getInstance().getCompatibleDevices().values());
            connectableDevices.clear();
            connectableDevices.addAll(compatibleDevices);

            // Update UI visibility based on device availability
            boolean hasDevices = !compatibleDevices.isEmpty();
            binding.tvSearchingLay.setVisibility(hasDevices ? View.GONE : View.VISIBLE);
            binding.tvListLay.setVisibility(hasDevices ? View.VISIBLE : View.GONE);
            binding.refreshLay.setVisibility(hasDevices ? View.VISIBLE : View.GONE);

            // Update adapter with new data
            tvDeviceAdapter.setData(getArrayTV(connectableDevices));
        });
    }

    @Override
    public void onDiscoveryFailed(DiscoveryManager discoveryManager, ServiceCommandError serviceCommandError) {
        String errorMessage = serviceCommandError.getMessage();
        Log.e(TAG, "onDiscoveryFailed :: " + errorMessage);

        Util.runOnUI(() -> {
            // Clear the current device list
            connectableDevices.clear();

            // Refresh device visibility based on compatibility
            List<ConnectableDevice> compatibleDevices = new ArrayList<>(DiscoveryManager.getInstance().getCompatibleDevices().values());
            boolean hasDevices = !compatibleDevices.isEmpty();

            binding.tvSearchingLay.setVisibility(hasDevices ? View.GONE : View.VISIBLE);
            binding.tvListLay.setVisibility(hasDevices ? View.VISIBLE : View.GONE);
            binding.refreshLay.setVisibility(hasDevices ? View.VISIBLE : View.GONE);

            // Update the adapter with an empty or refreshed list
            tvDeviceAdapter.setData(getArrayTV(connectableDevices));
        });
    }

    public void connectToDevice(final ConnectableDevice connectableDevice) {
        runOnUiThread(() -> {
            Log.d(TAG, "Connecting to: " + connectableDevice.getModelName());
            connectableDevice.addListener(connectableDeviceListener);
            connectableDevice.setPairingType(DeviceService.PairingType.PIN_CODE);
            connectableDevice.connect();
        });

        // Tracking connection attempt
        try {
            Tracking.connect(this, "start", connectableDevice.getId(), TVType.getTVType(connectableDevice));
        } catch (Exception e) {
            Log.e(TAG, "Error while tracking device connection", e);
        }

        // Initialize Pairing Alert Dialog
        dialogPairingAlert = new DialogPairingAlert(this, this::hConnectToggle);

        // Initialize Pairing Code Dialog
        dialogPairingCode = new DialogPairingCode(this, new DialogPairingCode.DialogListener() {
            @Override
            public void onCancel() {
                Log.d(TAG, "Pairing canceled by the user.");
            }

            @Override
            public void onOk(String pairingKey) {
                try {
                    connectableDevice.sendPairingKey(pairingKey);
                    Log.d(TAG, "Pairing key sent: " + pairingKey);
                } catch (Exception e) {
                    Log.e(TAG, "Error sending pairing key", e);
                }
            }
        });
    }

    public void hConnectToggle() {
        if (TVConnectUtils.getInstance().getConnectableDevice() != null) {
            if (TVConnectUtils.getInstance().getConnectableDevice().isConnected()) {
                TVConnectUtils.getInstance().getConnectableDevice().disconnect();
            }
            TVConnectUtils.getInstance().getConnectableDevice().removeListener(this.connectableDeviceListener);
            TVConnectUtils.getInstance().getConnectableDevice().disconnect();
        }
    }

    public void connectDeviceReady(final ConnectableDevice connectableDevice) {
        runOnUiThread(() -> {
            Log.d(TAG, "Device ready with connected services: " + connectableDevice.getConnectedServiceNames());

            Toast.makeText(ActivityConnect.this, "Connection successful", Toast.LENGTH_SHORT).show();

            if (PreferenceApp.isVibrate()) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (vibrator != null && vibrator.hasVibrator()) {
                    vibrator.vibrate(200);
                }
            }

            TVConnectUtils.getInstance().setConnectableDevice(connectableDevice);
            EventBus.getDefault().post(new MessageEvent("KEY_CONNECT"));
            isConnected = true;
            dismissConnectingDialog();
            finish();
        });
    }

    public void connectFailed(ConnectableDevice connectableDevice) {
        if (connectableDevice != null) {
            Log.e(TAG, "Failed to connect to device: " + connectableDevice.getIpAddress());
        }

        ConnectableDevice currentDevice = TVConnectUtils.getInstance().getConnectableDevice();
        if (currentDevice != null) {
            currentDevice.removeListener(connectableDeviceListener);
            currentDevice.disconnect();
        }
        dismissConnectingDialog();
    }

    public ArrayList<TVObject> getArrayTV(ArrayList<ConnectableDevice> arrayList) {
        ArrayList<TVObject> arrayList3 = new ArrayList<>();
        try {
            for (ConnectableDevice connectableDevice : arrayList) {
                String tvName = "";
                String tvModelName = "";

                // Check for the DIAL service and special handling for Fire TV
                if (connectableDevice.getServiceId().equalsIgnoreCase(DIALService.ID)) {
                    if (connectableDevice.getFriendlyName() != null && connectableDevice.getFriendlyName().contains(FireTVService.ID)) {
                        IpAddressFireTV = connectableDevice.getIpAddress();
                    }
                } else {
                    // Set the TV name and model name
                    if (connectableDevice.getFriendlyName() != null) {
                        tvName = connectableDevice.getFriendlyName();
                    }
                    if (connectableDevice.getModelName() != null) {
                        tvModelName = connectableDevice.getModelName();
                    }

                    // Create a new list to hold the ConnectableDevice
                    ArrayList<ConnectableDevice> devicesList = new ArrayList<>();
                    devicesList.add(connectableDevice);

                    // Check if the TVObject for this TV name already exists
                    boolean tvExists = false;
                    for (TVObject tvObject : arrayList3) {
                        if (tvName.equalsIgnoreCase(tvObject.getTvName())) {
                            tvObject.getArrType().add(connectableDevice);
                            tvExists = true;
                            break;
                        }
                    }

                    // If it doesn't exist, create a new TVObject
                    if (!tvExists) {
                        arrayList3.add(new TVObject(tvName, tvModelName, devicesList));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList3;
    }

    private void registerReceiverWifi() {
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.receiver);
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
