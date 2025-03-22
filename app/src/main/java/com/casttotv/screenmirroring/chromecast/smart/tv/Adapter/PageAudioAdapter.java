package com.casttotv.screenmirroring.chromecast.smart.tv.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio.AudioModel;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;

import java.util.List;

public class PageAudioAdapter extends PagerAdapter {

    List<AudioModel> listImage;

    @Override
    public int getCount() {
        List<AudioModel> list = this.listImage;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public PageAudioAdapter(List<AudioModel> list) {
        this.listImage = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_cast_viewpager, viewGroup, false);

        Glide.with(viewGroup.getContext())
                .load(R.drawable.placeholder_cast_audio)
                .into((ImageView) inflate.findViewById(R.id.imageView));

        viewGroup.addView(inflate);
        return inflate;
    }

    @Override 
    public boolean isViewFromObject(View view, @NonNull Object obj) {
        return view.equals(obj);
    }

    @Override 
    public void destroyItem(ViewGroup viewGroup, int i, @NonNull Object obj) {
        viewGroup.removeView((View) obj);
    }

    public void clearItems() {
        this.listImage.clear();
    }

    public void addItems(List<AudioModel> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.listImage.clear();
        this.listImage.addAll(list);
        if (!list.isEmpty()) {
            list.get(0).isSelected = true;
        }
        notifyDataSetChanged();
    }
}
