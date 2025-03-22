package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jaku.model.Channel;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ItemviewChannelBinding;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils.CommandHelper;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class AdapterChannelRoku extends ListAdapter<Channel, AdapterChannelRoku.MainViewHolder> {
    private final Function1<Channel, Unit> clickAction;

    public AdapterChannelRoku(Function1<? super Channel, Unit> function1) {
        super(new DiffCallback());
        this.clickAction = (Function1<Channel, Unit>) function1;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainViewHolder(ItemviewChannelBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder mainViewHolder, int i) {
        Glide.with(mainViewHolder.binding.ivChannel).load(
                CommandHelper.getIconURL(mainViewHolder.binding.ivChannel.getContext(),
                ((Channel) getItem(i)).getId())).into(mainViewHolder.binding.ivChannel);

        mainViewHolder.binding.ivChannel.setOnClickListener(view -> {
            Channel item = getItem(i);
            clickAction.invoke((Channel) item);
        });
    }

    private static final class DiffCallback extends DiffUtil.ItemCallback<Channel> {
        public boolean areItemsTheSame(Channel channel, Channel channel2) {
            return Intrinsics.areEqual(channel.getId(), channel2.getId());
        }

        public boolean areContentsTheSame(@NonNull Channel channel, @NonNull Channel channel2) {
            return Intrinsics.areEqual(channel, channel2);
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
