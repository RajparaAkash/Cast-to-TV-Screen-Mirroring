package com.casttotv.screenmirroring.chromecast.smart.tv.FileImage;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityCastFiles;
import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityConnect;
import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.ManagerDataPlay;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MediaModel;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {

    private final ArrayList<MediaModel> listMedia;
    private final Activity activity;
    private int currentItem = 0;

    public ImageListAdapter(Activity activity, ArrayList<MediaModel> list) {
        this.listMedia = list;
        this.activity = activity;
    }

    public void setData(List<MediaModel> list) {
        this.listMedia.clear();
        this.listMedia.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.itemview_image_file, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(listMedia.get(position), position);
    }

    @Override
    public int getItemCount() {
        return listMedia.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView imageThumbnailImg;

        public ViewHolder(View view) {
            super(view);
            imageThumbnailImg = view.findViewById(R.id.imageThumbnailImg);
        }

        public void bindData(MediaModel mediaModel, int position) {

            Glide.with(activity).asBitmap().load(mediaModel.getPhotoUri()).thumbnail(0.1f)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .skipMemoryCache(false)
                            .error(R.drawable.ic_launcher_background))
                    .into(imageThumbnailImg);

            itemView.setOnClickListener(new ThrottleClickListener() {
                @Override
                public void onThrottleClick(View v) {
                    if (TVConnectUtils.getInstance().isConnected()) {
                        currentItem = position;
                        gotoPlay();
                    } else {
                        AdsManager.displayTimeBasedInterstitialAd(activity, () -> {
                            activity.startActivity(new Intent(activity, ActivityConnect.class));
                        });
                    }
                }
            });
        }
    }

    public void gotoPlay() {
        try {
            ManagerDataPlay.getInstance().setTypePlay(0);
            ManagerDataPlay.getInstance().setListMedia(listMedia);
            ManagerDataPlay.getInstance().setPosSelected(currentItem);

            AdsManager.displayTimeBasedInterstitialAd(activity, () -> {
                activity.startActivity(new Intent(activity, ActivityCastFiles.class)
                        .putExtra("photo", true));
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
