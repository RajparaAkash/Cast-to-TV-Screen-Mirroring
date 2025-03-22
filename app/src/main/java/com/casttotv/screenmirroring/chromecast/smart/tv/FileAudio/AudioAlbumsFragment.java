package com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Utils;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AudioAlbumsFragment extends Fragment {

    private AudioAlbumAdapter audioAlbumAdapter;
    public RecyclerView audioRv;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_audio, viewGroup, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View view) {
        audioRv = view.findViewById(R.id.audioRv);
        audioRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        audioAlbumAdapter = new AudioAlbumAdapter(requireActivity(), new ArrayList<>());
        audioRv.setAdapter(audioAlbumAdapter);

        getAllAlbums();
    }

    private void getAllAlbums() {
        Observable.create((ObservableOnSubscribe<ArrayList<AudioAlbumModel>>) emitter -> {
                    emitter.onNext(Utils.getAllAudioFolders(requireContext()));
                    emitter.onComplete();
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<AudioAlbumModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ArrayList<AudioAlbumModel> audioAlbums) {
                        if (audioAlbums.isEmpty()) {
                            // Handle no data available case here if needed
                        }
                        audioAlbumAdapter.setData(audioAlbums);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
