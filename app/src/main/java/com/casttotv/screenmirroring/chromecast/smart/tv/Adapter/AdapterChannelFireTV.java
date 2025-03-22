package com.casttotv.screenmirroring.chromecast.smart.tv.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.casttotv.screenmirroring.chromecast.smart.tv.Api.App;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ItemviewChannelBinding;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class AdapterChannelFireTV extends ListAdapter<App, AdapterChannelFireTV.MainViewHolder> {
    private final Function1<App, Unit> clickAction;

    public AdapterChannelFireTV(Function1<? super App, Unit> function1) {
        super(new DiffCallback());
        this.clickAction = (Function1<App, Unit>) function1;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainViewHolder(ItemviewChannelBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder mainViewHolder, int i) {
        Glide.with(mainViewHolder.binding.ivChannel)
                .load(((App) getItem(i)).getIconArtSmallUri())
                .into(mainViewHolder.binding.ivChannel);

        mainViewHolder.binding.ivChannel.setOnClickListener(view -> {
            App item = getItem(i);
            clickAction.invoke((App) item);
        });
    }

    private static final class DiffCallback extends DiffUtil.ItemCallback<App> {
        public boolean areItemsTheSame(App app, App app2) {
            return Intrinsics.areEqual(app.getAppId(), app2.getAppId());
        }

        public boolean areContentsTheSame(@NonNull App app, @NonNull App app2) {
            return Intrinsics.areEqual(app, app2);
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
