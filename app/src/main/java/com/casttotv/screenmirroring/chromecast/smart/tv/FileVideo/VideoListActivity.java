package com.casttotv.screenmirroring.chromecast.smart.tv.FileVideo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.recyclerview.widget.GridLayoutManager;

import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityBase;
import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityConnect;
import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs.DialogDisconnectTv;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MediaModel;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MessageEvent;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivityVideoListBinding;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;

public class VideoListActivity extends ActivityBase {

    private ArrayList<MediaModel> videoList;
    private VideoListAdapter videoListAdapter;

    private ActivityVideoListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AdsManager.displaySmallNativeAd(this, findViewById(R.id.llNativeSmall), findViewById(R.id.llAds));

        configureBackPressedBehavior();
        EventBus.getDefault().register(this);
        initView();
    }

    public Unit callbackDone() {
        return Unit.INSTANCE;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        updateTVConnectionStatus();
    }

    private void updateTVConnectionStatus() {
        if (TVConnectUtils.getInstance().isConnected()) {
            binding.tvConnectImg.setImageResource(R.drawable.tv_connected_img);
        } else {
            binding.tvConnectImg.setImageResource(R.drawable.tv_disconnect_img);
        }
    }

    private void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("folderName");
            binding.headerTxt.setText(stringExtra);

            binding.videoAlbumDataRv.setLayoutManager(new GridLayoutManager(this, 3));
            videoList = new ArrayList<>();
            videoListAdapter = new VideoListAdapter(this, videoList);
            binding.videoAlbumDataRv.setAdapter(videoListAdapter);

            binding.progressBar.setVisibility(View.VISIBLE);
            if (stringExtra == null) {
                binding.noItemFoundLay.setVisibility(View.VISIBLE);
            } else {
                new FetchVideosTask(stringExtra).execute();
            }
        }

        updateTVConnectionStatus();

        binding.tvConnectLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                if (TVConnectUtils.getInstance().isConnected()) {
                    new DialogDisconnectTv(VideoListActivity.this).show();
                    return;
                }
                AdsManager.displayTimeBasedInterstitialAd(VideoListActivity.this, () -> {
                    startActivity(new Intent(VideoListActivity.this, ActivityConnect.class));
                });

            }
        });
    }

    private class FetchVideosTask extends AsyncTask<Void, Void, List<MediaModel>> {

        private final String folderName;

        public FetchVideosTask(String folderName) {
            this.folderName = folderName;
        }

        @Override
        protected List<MediaModel> doInBackground(Void... voids) {
            List<MediaModel> videos = new ArrayList<>();
            Uri videoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            String[] projection = {
                    MediaStore.Video.Media._ID,
                    MediaStore.Video.Media.DATA,
                    MediaStore.Video.Media.DISPLAY_NAME,
                    MediaStore.Video.Media.SIZE,
                    MediaStore.Video.Media.DURATION,
                    MediaStore.Video.Media.DATE_ADDED
            };

            String selection;
            String[] selectionArgs;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                selection = MediaStore.Video.Media.RELATIVE_PATH + " LIKE ?";
                selectionArgs = new String[]{"%" + folderName + "/"};
            } else {
                selection = MediaStore.Video.Media.DATA + " LIKE ?";
                selectionArgs = new String[]{"%/" + folderName + "/%"};
            }

            try (Cursor cursor = getContentResolver().query(videoUri, projection, selection, selectionArgs, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
                    int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                    int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
                    int durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);

                    do {
                        String videoId = cursor.getString(idColumn);
                        String videoPath = cursor.getString(dataColumn);
                        String videoName = cursor.getString(nameColumn);
                        long videoDuration = cursor.getLong(durationColumn);

                        Uri videoContentUri = Uri.withAppendedPath(videoUri, videoId);
                        /*videos.add(new MediaModel(videoName, videoPath, videoContentUri.toString(), videoDuration));*/
                        videos.add(new MediaModel(videoName, videoPath, videoPath, videoDuration));
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                Log.e("FetchVideosTask", "Error fetching videos", e);
            }

            return videos;
        }

        @Override
        protected void onPostExecute(List<MediaModel> videos) {
            binding.progressBar.setVisibility(View.GONE);

            if (videos.isEmpty()) {
                binding.noItemFoundLay.setVisibility(View.VISIBLE);
            } else {
                binding.noItemFoundLay.setVisibility(View.GONE);
                videoList.clear();
                videoList.addAll(videos);
                videoListAdapter.notifyDataSetChanged();
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