package com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityBase;
import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityCastFiles;
import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityConnect;
import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.ManagerDataPlay;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs.DialogDisconnectTv;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MessageEvent;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivityAudioBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class AudioActivity extends ActivityBase {

    private String[] titleAudio;
    public ArrayList<AudioModel> audioModelArrayList;

    private ActivityAudioBinding binding;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivityAudioBinding.inflate(getLayoutInflater());
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
                "All Audio",
                "Albums",
                "Artist"};

        binding.tvConnectLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                if (TVConnectUtils.getInstance().isConnected()) {
                    new DialogDisconnectTv(AudioActivity.this).show();
                    return;
                }
                AdsManager.displayTimeBasedInterstitialAd(AudioActivity.this, () -> {
                    startActivity(new Intent(AudioActivity.this, ActivityConnect.class));
                });
            }
        });


        if (TVConnectUtils.getInstance().isConnected()) {
            binding.tvConnectImg.setImageResource(R.drawable.tv_connected_img);
        } else {
            binding.tvConnectImg.setImageResource(R.drawable.tv_disconnect_img);
        }

        audioModelArrayList = new ArrayList<>();
        binding.audioViewPager.setAdapter(new AudioPagerAdapter(this));

        new TabLayoutMediator(binding.audioTabLayout, binding.audioViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                tab.setText(AudioActivity.this.titleAudio[i]);
            }
        }).attach();

        /*for (int i = 0; i < binding.audioTabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) binding.audioTabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(10, 0, 10, 0);
            tab.requestLayout();
        }*/

        binding.audioTabLayout.requestLayout();
    }

    public void gotoPlay(List<AudioModel> list, int i) {
        ArrayList<AudioModel> arrayList = this.audioModelArrayList;
        if (arrayList == null || arrayList.size() <= i) {
            return;
        }
        ManagerDataPlay.getInstance().setTypePlay(2);
        ManagerDataPlay.getInstance().setListAudio((ArrayList) list);
        ManagerDataPlay.getInstance().setPosSelected(i);
        ManagerDataPlay.getInstance().duration = Long.valueOf(list.get(i).getDuration());

        Intent intent = new Intent(this, ActivityCastFiles.class).putExtra("audio", true);
        AdsManager.displayTimeBasedInterstitialAd(this, () -> {
            startActivity(intent);
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

    public class AudioPagerAdapter extends FragmentStateAdapter {

        public AudioPagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new AudioAllFragment();
                case 1:
                    return new AudioAlbumsFragment();
                case 2:
                    return new AudioArtistFragment();
                default:
                    // Handle unexpected positions, maybe throw an exception or return a default fragment
                    return new AudioAllFragment();
            }
        }
    }
}
