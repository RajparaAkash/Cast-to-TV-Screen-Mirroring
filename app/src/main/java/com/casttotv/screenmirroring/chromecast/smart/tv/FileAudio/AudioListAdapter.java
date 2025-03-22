package com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Utils;

import java.util.List;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.ViewHolder> {

    private final List<AudioModel> listMedia;
    private final Context context;
    private OnItemClickPhoto clickListener;

    public AudioListAdapter(List<AudioModel> list, Context context) {
        this.listMedia = list;
        this.context = context;
    }

    public void setClickListener(OnItemClickPhoto listener) {
        this.clickListener = listener;
    }

    public void setData(List<AudioModel> list) {
        listMedia.clear();
        listMedia.addAll(list);
        notifyDataSetChanged();
    }

    public interface OnItemClickPhoto {
        void itemClick(List<AudioModel> list, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemview_audio_file, parent, false);
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
        private final TextView audioNameTxt;
        private final TextView audioDurationTxt;
        private final TextView audioExtensionTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            audioNameTxt = itemView.findViewById(R.id.audioNameTxt);
            audioDurationTxt = itemView.findViewById(R.id.audioDurationTxt);
            audioExtensionTxt = itemView.findViewById(R.id.audioExtensionTxt);
        }

        public void bindData(AudioModel audioModel, int position) {
            audioNameTxt.setText(audioModel.getSongTitle());

            // Set duration in minutes and seconds
            long duration = audioModel.getDuration();
            audioDurationTxt.setText(Utils.formatDuration(duration));

            // Get and set the audio file extension
            String extension = getFileExtension(audioModel.getSongPath());
            audioExtensionTxt.setText(extension != null ? extension : "Unknown");

            itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.itemClick(listMedia, position);
                }
            });
        }

        private String getFileExtension(String filePath) {
            if (filePath == null || !filePath.contains(".")) {
                return null;
            }
            return filePath.substring(filePath.lastIndexOf(".") + 1).toUpperCase();
        }
    }
}
