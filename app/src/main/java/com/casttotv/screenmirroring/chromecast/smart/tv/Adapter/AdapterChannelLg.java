package com.casttotv.screenmirroring.chromecast.smart.tv.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ItemviewChannelLgBinding;
import com.connectsdk.core.AppInfo;

import java.util.Random;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class AdapterChannelLg extends ListAdapter<AppInfo, AdapterChannelLg.MainViewHolder> {
    private final Function1<AppInfo, Unit> clickAction;
    private final String[] color = {"#D10143", "#2196F3", "#2CB4F1", "#D91BFA", "#B40039", "#FFC107", "#FF9800", "#FF5722", "#2FF837", "#14D5ED", "#87EC13"};

    public AdapterChannelLg(Function1<? super AppInfo, Unit> function1) {
        super(new DiffCallback());
        this.clickAction = (Function1<AppInfo, Unit>) function1;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainViewHolder(ItemviewChannelLgBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder mainViewHolder, int i) {
        mainViewHolder.binding.tvName.setText(((AppInfo) getItem(i)).getName());
        mainViewHolder.binding.cvMain.setCardBackgroundColor(Color.parseColor(color[new Random().nextInt(color.length)]));
        mainViewHolder.binding.getRoot().setOnClickListener(view -> {
            AppInfo item = getItem(i);
            clickAction.invoke((AppInfo) item);
        });
    }

    private static final class DiffCallback extends DiffUtil.ItemCallback<AppInfo> {
        public boolean areItemsTheSame(AppInfo appInfo, AppInfo appInfo2) {
            return Intrinsics.areEqual(appInfo.getId(), appInfo2.getId());
        }

        public boolean areContentsTheSame(@NonNull AppInfo appInfo, @NonNull AppInfo appInfo2) {
            return Intrinsics.areEqual(appInfo, appInfo2);
        }
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        ItemviewChannelLgBinding binding;

        public MainViewHolder(ItemviewChannelLgBinding itemChannelLgBinding) {
            super(itemChannelLgBinding.getRoot());
            this.binding = itemChannelLgBinding;
        }
    }
}
