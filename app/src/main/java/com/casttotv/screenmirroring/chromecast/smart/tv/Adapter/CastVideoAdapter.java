package com.casttotv.screenmirroring.chromecast.smart.tv.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MediaModel;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.google.android.material.card.MaterialCardView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CastVideoAdapter extends RecyclerView.Adapter<CastVideoAdapter.ChannelHolder> {

    private final Context context;
    private final List<MediaModel> mediaList;
    private IItemClick listener;

    public interface IItemClick {
        void clickItem(int position);
    }

    public CastVideoAdapter(Context context, List<MediaModel> mediaList) {
        this.context = context;
        this.mediaList = mediaList != null ? mediaList : new ArrayList<>();
    }

    public void setOnClickListener(IItemClick listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChannelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemview_cast_video, parent, false);
        return new ChannelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelHolder holder, int position) {
        holder.bindData(mediaList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mediaList == null) return 0;
        return mediaList.size();
    }

    public class ChannelHolder extends RecyclerView.ViewHolder {
        private final MaterialCardView materialCardView;
        private final ImageView imageview;

        public ChannelHolder(@NonNull View itemView) {
            super(itemView);
            materialCardView = itemView.findViewById(R.id.materialCardView);
            imageview = itemView.findViewById(R.id.imageview);
        }

        public void bindData(MediaModel mediaModel) {
            int position = getAdapterPosition();
            if (position == RecyclerView.NO_POSITION || mediaModel == null) return;

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.clickItem(position);
                }
            });

            // Set border color based on selection
            materialCardView.setStrokeColor(context.getResources().getColor(
                    mediaModel.isSelected ? R.color.colorPrimary : android.R.color.transparent
            ));

            // Load image using Glide
            Glide.with(context)
                    .load(Uri.fromFile(new File(mediaModel.getPhotoUri())))
                    .placeholder(R.drawable.placeholder_img)
                    .error(R.drawable.placeholder_img)
                    .into(imageview);
        }
    }

    public void setSelectedPosition(int position) {
        if (position < 0 || position >= mediaList.size()) return;

        for (MediaModel media : mediaList) {
            media.isSelected = false;
        }
        mediaList.get(position).isSelected = true;
        notifyDataSetChanged();
    }

    public void clearItems() {
        mediaList.clear();
        notifyDataSetChanged();
    }

    public void addItems(List<MediaModel> newMediaList) {
        if (newMediaList == null || newMediaList.isEmpty()) return;

        mediaList.clear();
        mediaList.addAll(newMediaList);

        // Set the first item as selected if applicable
        if (!mediaList.isEmpty()) {
            mediaList.get(0).isSelected = true;
        }

        notifyDataSetChanged();
    }
}
