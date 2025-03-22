package com.casttotv.screenmirroring.chromecast.smart.tv.FileVideo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityBase;
import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityConnect;
import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs.DialogDisconnectTv;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MessageEvent;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivityVideoBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class VideoActivity extends ActivityBase {

    private String[] titleAudio;
    private ActivityVideoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AdsManager.displaySmallNativeAd(this, findViewById(R.id.llNativeSmall), findViewById(R.id.llAds));

        configureBackPressedBehavior();
        EventBus.getDefault().register(this);
        initView();
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
        } else {
            binding.tvConnectImg.setImageResource(R.drawable.tv_disconnect_img);
        }
    }

    private void initView() {
        titleAudio = new String[]{
                "All Videos",
                "Albums"};

        binding.tvConnectLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                if (TVConnectUtils.getInstance().isConnected()) {
                    new DialogDisconnectTv(VideoActivity.this).show();
                    return;
                }
                AdsManager.displayTimeBasedInterstitialAd(VideoActivity.this, () -> {
                    startActivity(new Intent(VideoActivity.this, ActivityConnect.class));
                });
            }
        });


        if (TVConnectUtils.getInstance().isConnected()) {
            binding.tvConnectImg.setImageResource(R.drawable.tv_connected_img);
        } else {
            binding.tvConnectImg.setImageResource(R.drawable.tv_disconnect_img);
        }

        binding.audioViewPager.setAdapter(new VideoPagerAdapter(this));

        new TabLayoutMediator(binding.audioTabLayout, binding.audioViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                tab.setText(VideoActivity.this.titleAudio[i]);
            }
        }).attach();

        binding.audioTabLayout.requestLayout();
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

    public static class VideoPagerAdapter extends FragmentStateAdapter {

        public VideoPagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new VideoAllFragment();
                case 1:
                    return new VideoAlbumsFragment();
                default:
                    // Handle unexpected positions, maybe throw an exception or return a default fragment
                    return new VideoAllFragment();
            }
        }
    }
}