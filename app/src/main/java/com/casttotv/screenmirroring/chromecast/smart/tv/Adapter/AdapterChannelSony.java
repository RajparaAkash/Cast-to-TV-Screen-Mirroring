package com.casttotv.screenmirroring.chromecast.smart.tv.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ItemviewChannelBinding;
import com.casttotv.screenmirroring.chromecast.smart.tv.Sony.model.AppSony;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class AdapterChannelSony extends ListAdapter<AppSony, AdapterChannelSony.MainViewHolder> {
    private final Function1<AppSony, Unit> clickAction;

    public AdapterChannelSony(Function1<? super AppSony, Unit> function1) {
        super(new DiffCallback());
        this.clickAction = (Function1<AppSony, Unit>) function1;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainViewHolder(ItemviewChannelBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder mainViewHolder, int i) {
        Glide.with(mainViewHolder.binding.ivChannel).load(((AppSony) getItem(i)).getIcon()).into(mainViewHolder.binding.ivChannel);
        mainViewHolder.binding.ivChannel.setOnClickListener(view -> {
            AppSony item = getItem(i);
            clickAction.invoke((AppSony) item);
        });
    }

    private static final class DiffCallback extends DiffUtil.ItemCallback<AppSony> {
        public boolean areItemsTheSame(AppSony appSony, AppSony appSony2) {
            return Intrinsics.areEqual(appSony.getTitle(), appSony2.getTitle());
        }

        public boolean areContentsTheSame(@NonNull AppSony appSony, @NonNull AppSony appSony2) {
            return Intrinsics.areEqual(appSony, appSony2);
        }
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        ItemviewChannelBinding binding;

        public MainViewHolder(ItemviewChannelBinding itemChannelBinding) {
            super(itemChannelBinding.getRoot());
            this.binding = itemChannelBinding;
        }
    }
}
