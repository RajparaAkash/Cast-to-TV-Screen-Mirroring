package com.casttotv.screenmirroring.chromecast.smart.tv.FileImage;

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

import java.util.ArrayList;
import java.util.List;

public class ImageAlbumAdapter extends RecyclerView.Adapter<ImageAlbumAdapter.ViewHolder> {

    private final Activity activity;
    private final List<ImageAlbums> imageAlbumList;

    public ImageAlbumAdapter(Activity activity, List<ImageAlbums> imageAlbumList) {
        this.activity = activity;
        this.imageAlbumList = imageAlbumList;
    }

    public void setData(List<ImageAlbums> newImageAlbums) {
        this.imageAlbumList.clear();
        this.imageAlbumList.addAll(newImageAlbums);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.itemview_image_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ImageAlbums imageAlbums = imageAlbumList.get(position);

        Glide.with(activity).asBitmap()
                .load(imageAlbums.getCoverUri()).thumbnail(0.1f)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(false)
                        .error(R.drawable.ic_launcher_background))
                .into(holder.imageAlbumsImg);

        holder.albumNameTxt.setText(imageAlbums.getName());
        holder.imageCountTxt.setText(activity.getResources().getQuantityString(R.plurals.number_of_image, imageAlbums.getAlbumPhotos().size(), imageAlbums.getAlbumPhotos().size()));

        holder.itemView.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                AdsManager.displayTimeBasedInterstitialAd(activity, () -> {
                    Intent intent = new Intent(activity, ImageListActivity.class);
                    intent.putExtra("folderName", imageAlbums.getName());
                    intent.putParcelableArrayListExtra("folderData", new ArrayList<>(imageAlbums.getAlbumPhotos()));
                    activity.startActivity(intent);
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageAlbumList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ShapeableImageView imageAlbumsImg;
        private final TextView albumNameTxt;
        private final TextView imageCountTxt;

        public ViewHolder(@NonNull View view) {
            super(view);
            imageAlbumsImg = view.findViewById(R.id.imageAlbumsImg);
            albumNameTxt = view.findViewById(R.id.albumNameTxt);
            imageCountTxt = view.findViewById(R.id.imageCountTxt);
        }
    }
}
