package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import com.casttotv.screenmirroring.chromecast.smart.tv.Adapter.PagerHomeAdapter;
import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.AndroidTv.FragmentRemoteAndroidTv;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVType;
import com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs.DialogDisconnectTv;
import com.casttotv.screenmirroring.chromecast.smart.tv.FireTv.fragment.FragmentRemoteFireTv;
import com.casttotv.screenmirroring.chromecast.smart.tv.Fragment.FragmentChannel;
import com.casttotv.screenmirroring.chromecast.smart.tv.Lg.LGTV;
import com.casttotv.screenmirroring.chromecast.smart.tv.Lg.LGTVSingleTon;
import com.casttotv.screenmirroring.chromecast.smart.tv.Lg.fragment.RemoteLGFragment;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MessageEvent;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.CastClient;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.fragment.FragmentRemoteRoku;
import com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.WebSocketUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.fragment.FragmentRemoteSamSung;
import com.casttotv.screenmirroring.chromecast.smart.tv.Sony.fragment.FragmentRemoteSony;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.Common;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivityRemoteControlBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class ActivityRemoteControl extends ActivityBase {

    private final ArrayList<Fragment> fragments = new ArrayList<>();
    private final ActivityRemoteWebSocketListener webSocketListener = new ActivityRemoteWebSocketListener();

    public ActivityRemoteControlBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRemoteControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AdsManager.displayAdaptiveBannerAd(this, findViewById(R.id.llAdaptiveBanner));

        configureBackPressedBehavior();
        EventBus.getDefault().register(this);
        initView();
        buttonClickListener();
        startReviewFlow();
    }

    private void initView() {
        if (TVConnectUtils.getInstance().getConnectableDevice() == null) {
            fragments.add(new FragmentRemoteRoku());
            fragments.add(new FragmentChannel());
        } else {
            if (TVType.isRokuTV(TVConnectUtils.getInstance().getConnectableDevice())) {
                fragments.add(new FragmentRemoteRoku());
                CastClient.INSTANCE.setIp(TVConnectUtils.getInstance().getConnectableDevice().getIpAddress());
            } else if (TVType.isSonyTV(TVConnectUtils.getInstance().getConnectableDevice())) {
                FragmentRemoteSony.Companion companion = FragmentRemoteSony.Companion;
                Intent intent = getIntent();
                fragments.add(companion.instance(intent));
            } else if (TVType.isFireTV(TVConnectUtils.getInstance().getConnectableDevice())) {
                fragments.add(new FragmentRemoteFireTv());
            } else if (TVType.isSamsungTV(TVConnectUtils.getInstance().getConnectableDevice())) {
                WebSocketUtils webSocketUtils = WebSocketUtils.INSTANCE;
                String ipAddress = TVConnectUtils.getInstance().getConnectableDevice().getIpAddress();
                webSocketUtils.initWebSocket(ActivityRemoteControl.this, ipAddress, "eW91cG9wYWE=", webSocketListener);
                fragments.add(new FragmentRemoteSamSung());
            } else if (TVType.isLGTV(TVConnectUtils.getInstance().getConnectableDevice())) {
                LGTV tvControlInstance = LGTVSingleTon.INSTANCE.getInstance(ActivityRemoteControl.this);
                if (tvControlInstance != null) {
                    tvControlInstance.loadMainPreferences();
                }
                if (tvControlInstance != null) {
                    tvControlInstance.setMyIP(TVConnectUtils.getInstance().getConnectableDevice().getIpAddress());
                }
                if (tvControlInstance != null) {
                    tvControlInstance.saveIPPreference();
                }
                fragments.add(new RemoteLGFragment());
            } else {
                fragments.add(new FragmentRemoteAndroidTv());
            }
            FragmentChannel.Companion companion2 = FragmentChannel.Companion;
            Intent intent2 = getIntent();
            fragments.add(companion2.instance(intent2));
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Lifecycle lifecycle = getLifecycle();
        PagerHomeAdapter pagerHomeAdapter = new PagerHomeAdapter(fragments, supportFragmentManager, lifecycle);
        binding.rcViewPager.setAdapter(pagerHomeAdapter);
        binding.rcViewPager.setUserInputEnabled(false);
    }

    private void buttonClickListener() {
        binding.tvConnectLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                if (TVConnectUtils.getInstance().isConnected()) {
                    new DialogDisconnectTv(ActivityRemoteControl.this).show();
                    return;
                }
                AdsManager.displayTimeBasedInterstitialAd(ActivityRemoteControl.this, () -> {
                    startActivity(new Intent(ActivityRemoteControl.this, ActivityConnect.class));
                });
            }
        });

        binding.llRemote.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View view) {
                setDefault();
                binding.ivRemote.setColorFilter(getColor(R.color.remote_icon_menu_sel));
                binding.tvRemote.setTextColor(getColor(R.color.remote_icon_menu_sel));
                binding.rcViewPager.setCurrentItem(0);
                Common.INSTANCE.setChannel(false);
            }
        });

        binding.llChannel.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View view) {
                setDefault();
                binding.ivChannel.setColorFilter(getColor(R.color.remote_icon_menu_sel));
                binding.tvChannel.setTextColor(getColor(R.color.remote_icon_menu_sel));
                Common.INSTANCE.setChannel(true);
                binding.rcViewPager.setCurrentItem(1);
            }
        });
    }

    public void setDefault() {
        binding.ivRemote.setColorFilter(getColor(R.color.remote_icon_menu_unsel));
        binding.ivChannel.setColorFilter(getColor(R.color.remote_icon_menu_unsel));
        binding.tvRemote.setTextColor(getColor(R.color.remote_icon_menu_unsel));
        binding.tvChannel.setTextColor(getColor(R.color.remote_icon_menu_unsel));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        if (TVConnectUtils.getInstance().isConnected()) {
            binding.tvConnectImg.setImageResource(R.drawable.tv_connected_img);
            if (TVConnectUtils.getInstance().getConnectableDevice() != null && TVConnectUtils.getInstance().getConnectableDevice().getModelName() != null) {
                binding.headerTxt.setText(TVConnectUtils.getInstance().getConnectableDevice().getModelName());
            } else {
                binding.headerTxt.setText(getString(R.string.remote_control));
            }
        } else {
            binding.tvConnectImg.setImageResource(R.drawable.tv_disconnect_img);
            binding.headerTxt.setText(getString(R.string.remote_control));
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