package com.casttotv.screenmirroring.chromecast.smart.tv.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.casttotv.screenmirroring.chromecast.smart.tv.Adapter.AdapterChannelFireTV;
import com.casttotv.screenmirroring.chromecast.smart.tv.Adapter.AdapterChannelLg;
import com.casttotv.screenmirroring.chromecast.smart.tv.Adapter.AdapterChannelNew;
import com.casttotv.screenmirroring.chromecast.smart.tv.Adapter.AdapterChannelSamSung;
import com.casttotv.screenmirroring.chromecast.smart.tv.Adapter.AdapterChannelSony;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.ModelChannel;
import com.casttotv.screenmirroring.chromecast.smart.tv.MyApplication;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.Common;
import com.casttotv.screenmirroring.chromecast.smart.tv.UtilRemote.TVType;
import com.casttotv.screenmirroring.chromecast.smart.tv.AndroidTv.AndroidTvManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Api.Api;
import com.casttotv.screenmirroring.chromecast.smart.tv.Api.App;
import com.casttotv.screenmirroring.chromecast.smart.tv.Api.Description;
import com.casttotv.screenmirroring.chromecast.smart.tv.Database.ConvertDatabase;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.FragmentChannelRokuBinding;
import com.casttotv.screenmirroring.chromecast.smart.tv.FireTv.utils.FireTVController;
import com.casttotv.screenmirroring.chromecast.smart.tv.Lg.LGTV;
import com.casttotv.screenmirroring.chromecast.smart.tv.Lg.LGTVSingleTon;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.adapter.AdapterChannelRoku;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.tasks.ChannelTask;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.tasks.RxRequestTask;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils.RokuRequestTypes;
import com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.WebSocketUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.model.AppSS;
import com.casttotv.screenmirroring.chromecast.smart.tv.Samsung.model.Icon;
import com.casttotv.screenmirroring.chromecast.smart.tv.Sony.RetrofitClient;
import com.casttotv.screenmirroring.chromecast.smart.tv.Sony.model.AppSony;
import com.casttotv.screenmirroring.chromecast.smart.tv.Sony.model.ListApp;
import com.connectsdk.core.AppInfo;
import com.connectsdk.service.capability.Launcher;
import com.connectsdk.service.command.ServiceCommandError;
import com.google.gson.Gson;
import com.jaku.core.JakuRequest;
import com.jaku.model.Channel;
import com.jaku.request.LaunchAppRequest;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.functions.Action1;

public class FragmentChannel extends Fragment {

    public static final Companion Companion = new Companion(null);
    private final CompositeDisposable bin = new CompositeDisposable();
    private final FragmentChannelWebSocketListener webSocketListener = new FragmentChannelWebSocketListener(this);
    public ArrayList<Icon> listIcon;
    public AdapterChannelFireTV mAdapterFireTv;
    public String cookie = "";
    public ArrayList<AppSS> listApp;
    public AdapterChannelLg mAdapterLg;
    public AdapterChannelSamSung mAdapterSS;
    public AdapterChannelSony mAdapterSony;
    FragmentChannelRokuBinding binding;
    AdapterChannelNew adapterChannelNewFav;
    AdapterChannelNew adapterChannelNew;
    private int countFail;
    private AdapterChannelRoku mAdapter;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public ArrayList<Icon> getListIcon() {
        if (listIcon != null) {
            return listIcon;
        }
        return null;
    }

    public void setListIcon(ArrayList<Icon> arrayList) {
        this.listIcon = arrayList;
    }

    public int getCountFail() {
        return this.countFail;
    }

    public void setCountFail(int i) {
        this.countFail = i;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        binding = FragmentChannelRokuBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        super.onViewCreated(view, bundle);

        ArrayList<ModelChannel> channelArrayList = new ArrayList<>();
        channelArrayList.add(new ModelChannel(1, "channel_icon_youtube", "Youtube", "https://www.youtube.com"));
        channelArrayList.add(new ModelChannel(2, "channel_icon_netflix", "Netflix", "https://www.netflix.com/title.*"));
        channelArrayList.add(new ModelChannel(3, "channel_icon_play_store", "Play Store", "http://play.google.com/store/apps/"));
        channelArrayList.add(new ModelChannel(4, "channel_icon_prime_video", "Prime Video", "https://app.primevideo.com"));
        channelArrayList.add(new ModelChannel(6, "channel_icon_disney", "Disney+", "https://www.disneyplus.com"));
        channelArrayList.add(new ModelChannel(7, "channel_icon_spotify", "Spotify", "https://open.spotify.com/category"));
        channelArrayList.add(new ModelChannel(8, "channel_icon_tunin_radio", "Tunin Radio", "https://tunein.com/"));
        channelArrayList.add(new ModelChannel(9, "channel_icon_youtube_kids", "Youtube Kids", "https://www.kids.youtube.com"));
        channelArrayList.add(new ModelChannel(10, "channel_icon_qq_ee", "OQ.EE", "https://oq.ee/home/"));
        channelArrayList.add(new ModelChannel(11, "channel_icon_my_canal", "My Canal", "https://www.canalplus.com*"));
        channelArrayList.add(new ModelChannel(12, "channel_icon_youtube_music", "Youtube Music", "https://music.youtube.com/title.*"));
        channelArrayList.add(new ModelChannel(13, "channel_icon_salto", "Salto", "https://www.salto.fr*"));
        channelArrayList.add(new ModelChannel(14, "channel_icon_plex", "Plex", "http://watch.plex.tv/movies-and-shows/list/"));

        for (int i = 0; i < channelArrayList.size(); i++) {
            if (ConvertDatabase.getInstance(getActivity()).channelDao().isChannelExist(channelArrayList.get(i).getId()) == 0) {
                ConvertDatabase.getInstance(getActivity()).channelDao().insert(channelArrayList.get(i));
            }
        }

        if (TVConnectUtils.getInstance().getConnectableDevice() == null) {
            binding.llEmpty.setVisibility(View.VISIBLE);
        } else if (TVType.isRokuTV(TVConnectUtils.getInstance().getConnectableDevice())) {
            loadChannelRoku();
        } else if (TVType.isFireTV(TVConnectUtils.getInstance().getConnectableDevice())) {
            loadChannelFireTv();
        } else if (TVType.isSonyTV(TVConnectUtils.getInstance().getConnectableDevice())) {
            loadChannelSonyTV();
        } else if (TVType.isSamsungTV(TVConnectUtils.getInstance().getConnectableDevice())) {
            loadChannelSamsungTV();
        } else if (TVType.isLGTV(TVConnectUtils.getInstance().getConnectableDevice())) {
            loadChannelLGTV();
        } else if (TVType.isNewAndroidTV(TVConnectUtils.getInstance().getConnectableDevice())) {
            loadChannelAndroidTv();
        } else {
            binding.llEmpty.setVisibility(View.VISIBLE);
        }
    }

    private void loadChannelAndroidTv() {
        binding.lnChannelLgFake.setVisibility(View.VISIBLE);
        binding.ivYoutube.setOnClickListener(view -> {
            AndroidTvManager.INSTANCE.openApp("https://www.youtube.com");
        });
        binding.ivNetflix.setOnClickListener(view -> {
            AndroidTvManager.INSTANCE.openApp("https://www.netflix.com/title.*");
        });

        adapterChannelNewFav = new AdapterChannelNew(getActivity(), new AdapterChannelNew.OnFavChangedListener() {
            @Override
            public void onChanged() {
                getChannelData();
            }
        });
        binding.rvChannelFakeFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvChannelFakeFav.setAdapter(adapterChannelNewFav);

        adapterChannelNew = new AdapterChannelNew(getActivity(), new AdapterChannelNew.OnFavChangedListener() {
            @Override
            public void onChanged() {
                getChannelData();
            }
        });
        binding.rvChannelFake.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvChannelFake.setAdapter(adapterChannelNew);

        getChannelData();
    }

    public void getChannelData() {
        List<ModelChannel> allChannelList = ConvertDatabase.getInstance(getActivity()).channelDao().getAllChannel();

        if (allChannelList != null && !allChannelList.isEmpty()) {

            ArrayList<ModelChannel> arrayList1 = new ArrayList<>();
            ArrayList<ModelChannel> arrayList2 = new ArrayList<>();

            for (int i = 0; i < allChannelList.size(); i++) {
                if (allChannelList.get(i).getIsFavorite()) {
                    arrayList1.add(allChannelList.get(i));
                } else {
                    arrayList2.add(allChannelList.get(i));
                }
            }

            if (arrayList1 != null && !arrayList1.isEmpty()) {
                Log.e("fatal", "getChannelData 111: " + arrayList1.size());
                adapterChannelNewFav.updateList(arrayList1);
                binding.cvChannelFakeFav.setVisibility(View.VISIBLE);
            } else {
                binding.cvChannelFakeFav.setVisibility(View.GONE);
            }

            if (arrayList2 != null && !arrayList2.isEmpty()) {
                Log.e("fatal", "getChannelData 222: " + arrayList2.size());
                adapterChannelNew.updateList(arrayList2);
                binding.cvChannelFake.setVisibility(View.VISIBLE);
            } else {
                binding.cvChannelFake.setVisibility(View.GONE);
            }

        }
    }

    private void loadChannelLGTV() {
        LGTVSingleTon lGTVSingleTon = LGTVSingleTon.INSTANCE;
        LGTV instance = lGTVSingleTon.getInstance(requireContext());
        instance.loadMainPreferences();
        binding.ivYoutube.setOnClickListener(view -> {
            instance.send_key("Exit", LGTV.KEY_INDEX.fromInt(44));
            instance.send_key("Youtube", LGTV.KEY_INDEX.fromInt(11));
        });
        binding.ivNetflix.setOnClickListener(view -> {
            instance.send_key("Exit", LGTV.KEY_INDEX.fromInt(44));
            instance.send_key("Netflix", LGTV.KEY_INDEX.fromInt(12));
        });
        mAdapterLg = new AdapterChannelLg(new Function1<AppInfo, Unit>() {
            @Override
            public Unit invoke(AppInfo appInfo) {
                try {
                    ((Launcher) TVConnectUtils.getInstance().getConnectableDevice().getCapability(Launcher.class)).launchApp(appInfo.getId(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Unit.INSTANCE;
            }
        });
        binding.rvChannel.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.rvChannel.hasFixedSize();
        binding.rvChannel.setAdapter(mAdapterLg);
        try {
            if (TVConnectUtils.getInstance().getConnectableDevice().getCapability(Launcher.class) != null) {
                ((Launcher) TVConnectUtils.getInstance().getConnectableDevice().getCapability(Launcher.class)).getAppList(new Launcher.AppListListener() {
                    @Override
                    public void onSuccess(List<AppInfo> list) {
                        binding.lnChannelLgFake.setVisibility(View.GONE);
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mAdapterLg != null) {
                                    mAdapterLg.submitList(list);
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(ServiceCommandError serviceCommandError) {
                        binding.lnChannelLgFake.setVisibility(View.VISIBLE);
                    }
                });
                return;
            }
            binding.lnChannelLgFake.setVisibility(View.VISIBLE);
        } catch (Exception ignored) {
        }
    }

    private void loadChannelSamsungTV() {
        Common.INSTANCE.setChannel(true);
        binding.clEmptySs.setVisibility(View.VISIBLE);
        setListIcon(new ArrayList<>());
        listApp = new ArrayList<>();
        Context requireContext = requireContext();
        mAdapterSS = new AdapterChannelSamSung(requireContext, new AdapterChannelSamSung.ClickListener() {
            @Override
            public void onClick(AppSS appSS) {
                WebSocketUtils webSocketUtils = WebSocketUtils.INSTANCE;
                String appId = appSS.getAppId();
                Integer appType = appSS.getAppType();
                webSocketUtils.openAppOnSamSung(appId, appType);
            }
        });
        binding.rvChannel.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.rvChannel.setAdapter(mAdapterSS);
        if (WebSocketUtils.INSTANCE.isConnected()) {
            WebSocketUtils.INSTANCE.getAppOnSamSung(webSocketListener);
        }
    }

    private void loadChannelSonyTV() {
        String string;
        mAdapterSony = new AdapterChannelSony(new Function1<AppSony, Unit>() {
            @Override
            public Unit invoke(AppSony appSony) {
                Call<String> allApp = RetrofitClient.getInstance(TVConnectUtils.getInstance().getConnectableDevice().getIpAddress()).getMyApi().getAllApp(RequestBody.Companion.create("{\"method\":\"setActiveApp\",\"id\":601,\"params\":[{\"uri\":\"" + appSony.getUri() + "\"}],\"version\":\"1.0\"}", MediaType.Companion.parse("application/xml")), "application/xml", "\"urn:schemas-sony-com:service:IRCC:1#X_SendIRCC\"", cookie, "1234");
                if (allApp != null) {
                    allApp.enqueue(new Callback<String>() {

                        @Override
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable th) {
                        }

                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        }
                    });
                }
                return Unit.INSTANCE;
            }
        });
        binding.rvChannel.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.rvChannel.hasFixedSize();
        binding.rvChannel.setAdapter(mAdapterSony);
        Bundle arguments = getArguments();
        if (arguments != null && (string = arguments.getString("cookie")) != null) {
            this.cookie = string;
            Call<String> allApp = RetrofitClient.getInstance(TVConnectUtils.getInstance().getConnectableDevice().getIpAddress()).getMyApi().getAllApp(RequestBody.Companion.create("{\"method\":\"getApplicationList\", \"id\":60,\"params\":[],\"version\":\"1.0\"}", MediaType.Companion.parse("application/xml")), "application/xml", "\"urn:schemas-sony-com:service:IRCC:1#X_SendIRCC\"", string, "1234");
            if (allApp != null) {
                allApp.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ListApp listApp = (ListApp) new Gson().fromJson(response.body(), ListApp.class);
                            if (listApp != null) {
                                requireActivity().runOnUiThread(() -> {
                                    if (mAdapterSony != null) {
                                        mAdapterSony.submitList(listApp.getResult().get(0));
                                    }
                                });
                            } else {
                                binding.llEmpty.setVisibility(View.VISIBLE);
                            }
                        } else {
                            binding.llEmpty.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        binding.llEmpty.setVisibility(View.VISIBLE);
                    }
                });
            }
        }
    }

    private void loadChannelFireTv() {
        mAdapterFireTv = new AdapterChannelFireTV(new Function1<App, Unit>() {
            @Override
            public Unit invoke(App app) {
                Call<Description> call;
                Api api = FireTVController.INSTANCE.getApi();
                if (api != null) {
                    FireTVController fireTVController = FireTVController.INSTANCE;
                    String token = fireTVController.getToken(requireContext());
                    String appId = app.getAppId();
                    call = api.openApp(FireTVController.apiKeyFireTV, token, appId);
                } else {
                    call = null;
                }
                if (call != null) {
                    call.enqueue(new Callback<Description>() {

                        @Override
                        public void onFailure(@NonNull Call<Description> call, @NonNull Throwable th) {
                        }

                        @Override
                        public void onResponse(@NonNull Call<Description> call, @NonNull Response<Description> response) {
                        }
                    });
                }
                return Unit.INSTANCE;
            }
        });
        Call<List<App>> call = null;
        binding.rvChannel.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.rvChannel.hasFixedSize();
        binding.rvChannel.setAdapter(mAdapterFireTv);
        Api api = FireTVController.INSTANCE.getApi();
        if (api != null) {
            FireTVController fireTVController = FireTVController.INSTANCE;
            Context requireContext = requireContext();
            call = api.getApps(FireTVController.apiKeyFireTV, fireTVController.getToken(requireContext));
        }
        if (call != null) {
            call.enqueue(new Callback<List<App>>() {
                @Override
                public void onResponse(@NonNull Call<List<App>> call, @NonNull Response<List<App>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<App> body = (List<App>) response.body();
                        if (mAdapterFireTv != null) {
                            mAdapterFireTv.submitList((List<App>) body);
                        }
                        Log.d("AAA", String.valueOf(body != null ? body.size() : null));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<App>> call, @NonNull Throwable t) {
                    Log.e("AAA", "FF " + t.getMessage());
                }
            });
        }
    }

    private void loadChannelRoku() {
        mAdapter = new AdapterChannelRoku(new Function1<Channel, Unit>() {
            @Override
            public Unit invoke(Channel channel) {
                rx.Observable.fromCallable(new RxRequestTask(requireActivity().getApplicationContext(), new JakuRequest(new LaunchAppRequest("http://" + TVConnectUtils.getInstance().getConnectableDevice().getIpAddress() + ":8060", channel.getId()), null), RokuRequestTypes.launch)).subscribeOn(rx.schedulers.Schedulers.io()).observeOn(rx.android.schedulers.AndroidSchedulers.mainThread()).subscribe(new Action1() {
                    @Override
                    public void call(Object o) {

                    }
                });
                return Unit.INSTANCE;
            }
        });
        binding.rvChannel.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.rvChannel.hasFixedSize();
        binding.rvChannel.setAdapter(mAdapter);
        bin.add(Observable.fromCallable(new ChannelTask(requireContext())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override
            public void accept(Object obj) throws Exception {
                if (mAdapter != null) {
                    mAdapter.submitList((List) obj);
                }
            }
        }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.bin.dispose();
    }

    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public FragmentChannel instance(Intent intent) {
            Bundle bundle = new Bundle();
            bundle.putString("cookie", MyApplication.getInstance().myCookie);
            FragmentChannel channelFragment = new FragmentChannel();
            channelFragment.setArguments(bundle);
            return channelFragment;
        }
    }
}
