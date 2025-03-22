package com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;

import java.util.ArrayList;
import java.util.List;

public class AudioAlbumAdapter extends RecyclerView.Adapter<AudioAlbumAdapter.ViewHolder> {

    private final Activity activity;
    private final List<AudioAlbumModel> audioAlbumList;

    public AudioAlbumAdapter(Activity activity,List<AudioAlbumModel> audioAlbumList) {
        this.activity = activity;
        this.audioAlbumList = audioAlbumList;
    }

    public void setData(List<AudioAlbumModel> newAudioAlbums) {
        this.audioAlbumList.clear();
        this.audioAlbumList.addAll(newAudioAlbums);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.itemview_audio_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AudioAlbumModel album = audioAlbumList.get(position);
        holder.bindData(album);
    }

    @Override
    public int getItemCount() {
        return audioAlbumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView audioFolderNameTxt;
        private final TextView audioCountTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            audioFolderNameTxt = itemView.findViewById(R.id.audioFolderNameTxt);
            audioCountTxt = itemView.findViewById(R.id.audioCountTxt);
        }

        public void bindData(AudioAlbumModel album) {
            audioFolderNameTxt.setText(album.getNameAlbum());
            audioCountTxt.setText(album.getArrSong().size() + " Songs");

            itemView.setOnClickListener(new ThrottleClickListener() {
                @Override
                public void onThrottleClick(View view) {
                    AdsManager.displayTimeBasedInterstitialAd(activity, () -> {
                        Intent mIntent = new Intent(activity, AudioListActivity.class);
                        mIntent.putExtra("folderName", album.getNameAlbum());
                        mIntent.putParcelableArrayListExtra("folderData", new ArrayList<>(album.getArrSong()));
                        activity.startActivity(mIntent);
                    });
                }
            });
        }
    }
}
