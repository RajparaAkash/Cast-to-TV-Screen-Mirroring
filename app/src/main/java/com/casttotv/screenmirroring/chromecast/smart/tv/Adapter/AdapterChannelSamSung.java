package com.casttotv.screenmirroring.chromecast.smart.tv.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ItemviewChannelLgBinding;
import com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.model.AppSS;

import java.util.ArrayList;
import java.util.Random;

public class AdapterChannelSamSung extends RecyclerView.Adapter<AdapterChannelSamSung.ViewHolder> {
    private final String[] color = {"#D10143", "#2196F3", "#2CB4F1", "#D91BFA", "#B40039", "#FFC107", "#FF9800", "#FF5722", "#2FF837", "#14D5ED", "#87EC13"};
    private final Context context;
    private final ClickListener listener;
    private ArrayList<AppSS> listApp = new ArrayList<>();

    public AdapterChannelSamSung(Context context2, ClickListener clickListener) {
        this.context = context2;
        this.listener = clickListener;
    }

    public final Context getContext() {
        return this.context;
    }

    public final ClickListener getListener() {
        return this.listener;
    }

    public final void setListApp(ArrayList<AppSS> arrayList) {
        this.listApp.clear();
        this.listApp.addAll(arrayList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(ItemviewChannelLgBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.binding.cvMain.setCardBackgroundColor(Color.parseColor(color[new Random().nextInt(color.length)]));
        viewHolder.binding.getRoot().setOnClickListener(view -> {
            AppSS appSS = listApp.get(i);
            listener.onClick(appSS);
        });
        viewHolder.binding.tvName.setText(listApp.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return listApp.size();
    }

    public interface ClickListener {
        void onClick(AppSS appSS);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemviewChannelLgBinding binding;

        public ViewHolder(ItemviewChannelLgBinding itemChannelLgBinding) {
            super(itemChannelLgBinding.getRoot());
            this.binding = itemChannelLgBinding;
        }
    }
}
