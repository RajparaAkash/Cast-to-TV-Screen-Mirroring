package com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivityAudioListBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class AudioListActivity extends ActivityBase {

    public AudioListAdapter audioListAdapter;
    private List<AudioModel> audioModelArrayList;

    private ActivityAudioListBinding binding;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivityAudioListBinding.inflate(getLayoutInflater());
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
        // Get folder data passed from the previous screen
        Intent intent = getIntent();
        if (intent != null) {
            String folderName = intent.getStringExtra("folderName");
            ArrayList<AudioModel> folderData = intent.getParcelableArrayListExtra("folderData");

            if (folderName != null) {
                binding.headerTxt.setText(folderName);
            }

            if (folderData != null) {
                audioModelArrayList = new ArrayList<>();
                audioModelArrayList.addAll(folderData);
            }

            // Setup RecyclerView
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            binding.audioAlbumDataRv.setLayoutManager(linearLayoutManager);

            // Initialize AudioAdapter
            audioListAdapter = new AudioListAdapter(audioModelArrayList, this);
            binding.audioAlbumDataRv.setAdapter(audioListAdapter);

            // Handle item click in AudioAdapter
            audioListAdapter.setClickListener(new AudioListAdapter.OnItemClickPhoto() {
                @Override
                public void itemClick(List<AudioModel> list, int i) {
                    if (TVConnectUtils.getInstance().isConnected()) {
                        gotoPlay(list, i);
                    } else {
                        AdsManager.displayTimeBasedInterstitialAd(AudioListActivity.this, () -> {
                            startActivity(new Intent(AudioListActivity.this, ActivityConnect.class));
                        });
                    }
                }
            });
        }

        if (TVConnectUtils.getInstance().isConnected()) {
            binding.tvConnectImg.setImageResource(R.drawable.tv_connected_img);
        } else {
            binding.tvConnectImg.setImageResource(R.drawable.tv_disconnect_img);
        }

        binding.tvConnectLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                if (TVConnectUtils.getInstance().isConnected()) {
                    new DialogDisconnectTv(AudioListActivity.this).show();
                    return;
                }
                AdsManager.displayTimeBasedInterstitialAd(AudioListActivity.this, () -> {
                    startActivity(new Intent(AudioListActivity.this, ActivityConnect.class));
                });
            }
        });
    }

    public void gotoPlay(List<AudioModel> list, int position) {

        ManagerDataPlay.getInstance().setTypePlay(2);
        ManagerDataPlay.getInstance().setListAudio(new ArrayList<>(list));
        ManagerDataPlay.getInstance().setPosSelected(position);
        ManagerDataPlay.getInstance().duration = Long.valueOf(list.get(position).getDuration());

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
}
