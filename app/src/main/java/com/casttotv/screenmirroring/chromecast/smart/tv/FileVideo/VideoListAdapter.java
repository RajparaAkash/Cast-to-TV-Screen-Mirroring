package com.casttotv.screenmirroring.chromecast.smart.tv.FileVideo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityConnect;
import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityCastFiles;
import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MediaModel;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.ManagerDataPlay;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Utils;
import com.google.android.material.imageview.ShapeableImageView;

import java.io.File;
import java.util.ArrayList;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {

    private final Activity mActivity;
    private final ArrayList<MediaModel> videoList;
    private int currentItem = 0;

    public VideoListAdapter(Activity activity, ArrayList<MediaModel> list) {
        this.mActivity = activity;
        this.videoList = list;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VideoViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.itemview_video_file, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(VideoViewHolder videoViewHolder, @SuppressLint("RecyclerView") final int i) {

        final MediaModel video = this.videoList.get(i);

        long duration = video.getDuration();
        videoViewHolder.videoDurationTxt.setText(Utils.formatDuration(duration));

        Glide.with(mActivity)
                .load(Uri.fromFile(new File(video.getPhotoUri()))).thumbnail(0.1f)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(false)
                        .error(R.drawable.ic_launcher_background))
                .into(videoViewHolder.videoThumbnailImg);

        videoViewHolder.itemView.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                if (TVConnectUtils.getInstance().isConnected()) {
                    currentItem = i;
                    gotoPlay();
                }
                else {
                    AdsManager.displayTimeBasedInterstitialAd(mActivity, () -> {
                        mActivity.startActivity(new Intent(mActivity, ActivityConnect.class));
                    });
                }
            }
        });
    }

    private void gotoPlay() {
        ManagerDataPlay.getInstance().setTypePlay(1);
        ManagerDataPlay.getInstance().setListMedia(videoList);
        ManagerDataPlay.getInstance().setPosSelected(currentItem);
        ManagerDataPlay.getInstance().duration = videoList.get(currentItem).getDuration();

        AdsManager.displayTimeBasedInterstitialAd(mActivity, () -> {
            mActivity.startActivity(new Intent(mActivity, ActivityCastFiles.class)
                    .putExtra("video", true));
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        private final ShapeableImageView videoThumbnailImg;
        private final TextView videoDurationTxt;

        public VideoViewHolder(View view) {
            super(view);
            videoThumbnailImg = view.findViewById(R.id.videoThumbnailImg);
            videoDurationTxt = view.findViewById(R.id.videoDurationTxt);
        }
    }
}
