package com.casttotv.screenmirroring.chromecast.smart.tv.FileVideo;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class VideoAlbumAdapter extends RecyclerView.Adapter<VideoAlbumAdapter.VideoFolderViewHolder> {

    private final Activity mActivity;
    private List<VideoAlbums> folderList;

    public VideoAlbumAdapter(Activity activity, List<VideoAlbums> list) {
        this.mActivity = activity;
        this.folderList = list;
    }

    @NonNull
    @Override
    public VideoFolderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VideoFolderViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.itemview_video_album, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(VideoFolderViewHolder holder, int i) {

        final VideoAlbums videoAlbums = this.folderList.get(i);

        Glide.with(mActivity).asBitmap().load(videoAlbums.getFirstVideoPath()).thumbnail(0.1f)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(false)
                        .error(R.drawable.ic_launcher_background))
                .into(holder.videoAlbumsImg);

        holder.albumNameTxt.setText(videoAlbums.getName());
        holder.videoCountTxt.setText(mActivity.getResources().getQuantityString(R.plurals.number_of_videos, videoAlbums.getVideoCount(), Integer.valueOf(videoAlbums.getVideoCount())));

        holder.itemView.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                AdsManager.displayTimeBasedInterstitialAd(mActivity, () -> {
                    final Intent intent = new Intent(mActivity, VideoListActivity.class);
                    intent.putExtra("folderName", videoAlbums.getName());
                    mActivity.startActivity(intent);
                });
            }
        });
    }

    public void setFolders(List<VideoAlbums> list) {
        this.folderList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.folderList.size();
    }

    public static class VideoFolderViewHolder extends RecyclerView.ViewHolder {

        private final ShapeableImageView videoAlbumsImg;
        private final TextView albumNameTxt;
        private final TextView videoCountTxt;

        public VideoFolderViewHolder(View view) {
            super(view);
            videoAlbumsImg = view.findViewById(R.id.videoAlbumsImg);
            albumNameTxt = view.findViewById(R.id.albumNameTxt);
            videoCountTxt = view.findViewById(R.id.videoCountTxt);
        }
    }
}
