package com.casttotv.screenmirroring.chromecast.smart.tv.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio.AudioModel;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;

import java.util.ArrayList;
import java.util.List;

public class CastAudioAdapter extends RecyclerView.Adapter<CastAudioAdapter.ChannelHolder> {

    private final Context context;
    private final List<AudioModel> audioList;
    private IItemClick listener;

    public interface IItemClick {
        void clickItem(int position);
    }

    public CastAudioAdapter(Context context, List<AudioModel> audioList) {
        this.context = context;
        this.audioList = audioList != null ? audioList : new ArrayList<>();
    }

    public void setOnClickListener(IItemClick listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChannelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemview_cast_audio, parent, false);
        return new ChannelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelHolder holder, int position) {
        holder.bindData(audioList.get(position));
    }

    @Override
    public int getItemCount() {
        if (audioList == null) return 0;
        return audioList.size();
    }

    public class ChannelHolder extends RecyclerView.ViewHolder {
        private final RelativeLayout audioLay;
        private final TextView audioNameTxt;

        public ChannelHolder(@NonNull View itemView) {
            super(itemView);
            audioLay = itemView.findViewById(R.id.audioLay);
            audioNameTxt = itemView.findViewById(R.id.audioNameTxt);
        }

        public void bindData(AudioModel audioModel) {
            int position = getAdapterPosition(); // Use this if getBindingAdapterPosition() is unavailable
            if (position == RecyclerView.NO_POSITION || audioModel == null) return;

            audioNameTxt.setText(audioModel.getSongTitle());

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.clickItem(position);
                }
            });

            if (audioModel.isSelected){
                audioLay.setBackgroundResource(R.drawable.bg_cast_audio_selected);
            }else {
                audioLay.setBackgroundResource(R.drawable.bg_cast_audio_unselected);
            }
        }
    }

    public void setSelectedPosition(int position) {
        if (position < 0 || position >= audioList.size()) return;

        for (AudioModel media : audioList) {
            media.isSelected = false;
        }
        audioList.get(position).isSelected = true;
        notifyDataSetChanged();
    }

    public void clearItems() {
        audioList.clear();
        notifyDataSetChanged();
    }

    public void addItems(List<AudioModel> newMediaList) {
        if (newMediaList == null || newMediaList.isEmpty()) return;

        audioList.clear();
        audioList.addAll(newMediaList);

        // Set the first item as selected if applicable
        if (!audioList.isEmpty()) {
            audioList.get(0).isSelected = true;
        }

        notifyDataSetChanged();
    }
}
