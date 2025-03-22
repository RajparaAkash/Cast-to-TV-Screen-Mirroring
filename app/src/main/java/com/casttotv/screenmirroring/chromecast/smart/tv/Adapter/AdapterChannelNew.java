package com.casttotv.screenmirroring.chromecast.smart.tv.Adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.casttotv.screenmirroring.chromecast.smart.tv.Model.ModelChannel;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.AndroidTv.AndroidTvManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Database.ConvertDatabase;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ItemviewChannelNewBinding;

import java.util.ArrayList;

public class AdapterChannelNew extends RecyclerView.Adapter<AdapterChannelNew.ViewHolder> {

    Activity activity;
    ArrayList<ModelChannel> channelArrayList;
    OnFavChangedListener listener;

    public interface OnFavChangedListener {
        void onChanged();
    }

    public AdapterChannelNew(Activity activity, OnFavChangedListener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    public void updateList(ArrayList<ModelChannel> channelArrayList) {
        this.channelArrayList = channelArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemviewChannelNewBinding.inflate(LayoutInflater.from(activity), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelChannel modelChannel = channelArrayList.get(position);
        Resources resources = activity.getResources();
        final int resourceId = resources.getIdentifier(modelChannel.getIcon(), "drawable", activity.getPackageName());

        holder.binding.ivChannel.setImageResource(resourceId);
        holder.binding.tvChannelName.setText(modelChannel.getName());

        if (modelChannel.getIsFavorite()) {
            holder.binding.ivFavorite.setImageResource(R.drawable.channel_icon_fav);
        } else {
            holder.binding.ivFavorite.setImageResource(R.drawable.channel_icon_unfav);
        }

        holder.binding.ivFavorite.setOnClickListener(view -> {
            modelChannel.setIsFavorite(!modelChannel.getIsFavorite());
            ConvertDatabase.getInstance(activity).channelDao().update(modelChannel);
            notifyDataSetChanged();
            listener.onChanged();
        });

        holder.itemView.setOnClickListener(view -> {
            AndroidTvManager.INSTANCE.openApp(modelChannel.getUrl());
        });

        if (position == channelArrayList.size() - 1) {
            holder.binding.view.setVisibility(View.GONE);
        } else {
            holder.binding.view.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return channelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemviewChannelNewBinding binding;

        public ViewHolder(ItemviewChannelNewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
