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

public class AudioArtistFragment extends Fragment {

    private AudioAlbumAdapter audioArtistAdapter;
    public RecyclerView audioRv;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = layoutInflater.inflate(R.layout.fragment_audio, viewGroup, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        audioRv = view.findViewById(R.id.audioRv);
        audioRv.setLayoutManager(new GridLayoutManager(getContext(), 3));

        // Initialize adapter with empty data
        audioArtistAdapter = new AudioAlbumAdapter(requireActivity(), new ArrayList<>());
        audioRv.setAdapter(audioArtistAdapter);

        // Fetch and display artist-wise audio data
        getAllArtist();
    }

    private void getAllArtist() {
        Observable.create((ObservableOnSubscribe<ArrayList<AudioAlbumModel>>) emitter -> {
                    // Fetch artist-wise grouped data
                    ArrayList<AudioAlbumModel> artistAlbums = Utils.getAllArtist(requireContext());
                    emitter.onNext(artistAlbums);
                    emitter.onComplete();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<AudioAlbumModel>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onNext(ArrayList<AudioAlbumModel> audioAlbumModels) {
                        // Update adapter with fetched data
                        audioArtistAdapter.setData(audioAlbumModels);
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
