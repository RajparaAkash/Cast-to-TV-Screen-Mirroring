package com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Activity.ActivityConnect;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AudioAllFragment extends Fragment {

    public RecyclerView audioRv;
    public LinearLayout noItemFoundLay;

    private AudioListAdapter audioListAdapter;
    private AudioActivity audioActivity;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        audioRv = view.findViewById(R.id.audioRv);
        noItemFoundLay = view.findViewById(R.id.noItemFoundLay);

        // Setting up RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        audioRv.setLayoutManager(linearLayoutManager);

        // Initializing adapter with an empty list
        audioListAdapter = new AudioListAdapter(new ArrayList<>(), requireContext());
        audioRv.setAdapter(audioListAdapter);

        // Setting click listener
        audioListAdapter.setClickListener((list, position) -> {
            if (TVConnectUtils.getInstance().isConnected()) {
                if (audioActivity != null) {
                    audioActivity.gotoPlay(list, position);
                }
            } else {
                AdsManager.displayTimeBasedInterstitialAd(requireActivity(), () -> {
                    startActivity(new Intent(requireContext(), ActivityConnect.class));
                });
            }
        });

        // Fetching songs
        getAllSongs();
    }

    private void getAllSongs() {
        Observable.create((ObservableOnSubscribe<List<AudioModel>>) emitter -> {
                    List<AudioModel> audioList = Utils.getAllAudioFromDevice(requireContext());
                    emitter.onNext(audioList);
                    emitter.onComplete();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AudioModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        // Optional: Handle subscription logic if needed
                    }

                    @Override
                    public void onNext(@NonNull List<AudioModel> audioList) {
                        if (audioList.isEmpty()) {
                            showNoDataLayout(true);
                        } else {
                            showNoDataLayout(false);
                            if (audioActivity != null) {
                                audioActivity.audioModelArrayList.clear();
                                audioActivity.audioModelArrayList.addAll(audioList);
                            }
                            audioListAdapter.setData(audioList);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        // Log the error if needed
                        showNoDataLayout(true);
                    }

                    @Override
                    public void onComplete() {
                        // Optional: Handle completion logic if needed
                    }
                });
    }

    private void showNoDataLayout(boolean noData) {
        if (noData) {
            audioRv.setVisibility(View.GONE);
            noItemFoundLay.setVisibility(View.VISIBLE);
        } else {
            audioRv.setVisibility(View.VISIBLE);
            noItemFoundLay.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AudioActivity) {
            audioActivity = (AudioActivity) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement AudioActivity");
        }
    }
}
