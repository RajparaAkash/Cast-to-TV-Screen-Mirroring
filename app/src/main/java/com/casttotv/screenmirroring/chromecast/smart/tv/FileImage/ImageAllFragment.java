package com.casttotv.screenmirroring.chromecast.smart.tv.FileImage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MediaModel;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.FragmentImageAllBinding;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Utils;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ImageAllFragment extends Fragment {

    private ArrayList<MediaModel> imageList;
    private ImageListAdapter imageListAdapter;

    private FragmentImageAllBinding binding;

    public ImageAllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentImageAllBinding.inflate(inflater, container, false);
        setupRecyclerView();
        loadVideos();
        return binding.getRoot();
    }

    private void setupRecyclerView() {
        binding.imageListRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        imageList = new ArrayList<>();
        imageListAdapter = new ImageListAdapter(requireActivity(), imageList);
        binding.imageListRv.setAdapter(imageListAdapter);
    }

    private void loadVideos() {
        binding.progressBar.setVisibility(View.VISIBLE);
        getAllPhoto();
    }

    private void getAllPhoto() {
        Observable.create(new ObservableOnSubscribe<ArrayList<MediaModel>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<MediaModel>> observableEmitter) {
                observableEmitter.onNext(Utils.getAllPhotos(requireActivity()));
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<MediaModel>>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable th) {
                showNoDataLayout(true);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onNext(ArrayList<MediaModel> arrayList) {
                if (arrayList.isEmpty()) {
                    showNoDataLayout(true);
                } else {
                    showNoDataLayout(false);
                    imageListAdapter.setData(arrayList);
                    imageList.clear();
                    imageList.addAll(arrayList);
                }
            }
        });
    }

    private void showNoDataLayout(boolean noData) {
        if (noData) {
            binding.progressBar.setVisibility(View.GONE);
            binding.imageListRv.setVisibility(View.GONE);
            binding.noItemFoundLay.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
            binding.noItemFoundLay.setVisibility(View.GONE);
            binding.imageListRv.setVisibility(View.VISIBLE);
        }
    }
}