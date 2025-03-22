package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.casttotv.screenmirroring.chromecast.smart.tv.Adapter.CastAudioAdapter;
import com.casttotv.screenmirroring.chromecast.smart.tv.Adapter.CastImageAdapter;
import com.casttotv.screenmirroring.chromecast.smart.tv.Adapter.CastVideoAdapter;
import com.casttotv.screenmirroring.chromecast.smart.tv.Adapter.PageAudioAdapter;
import com.casttotv.screenmirroring.chromecast.smart.tv.Adapter.PageImageAdapter;
import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.BrowserAction;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.ManagerDataPlay;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVType;
import com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs.DialogDisconnectTv;
import com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs.DialogFileInfo;
import com.casttotv.screenmirroring.chromecast.smart.tv.FileAudio.AudioModel;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MediaModel;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.MessageEvent;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ServeHTTPD2;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Tracking;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Volume.CommandHelper;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Volume.RokuRequestTypes;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Volume.RxRequestTask;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivityCastFilesBinding;
import com.connectsdk.core.MediaInfo;
import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.service.capability.MediaControl;
import com.connectsdk.service.capability.MediaPlayer;
import com.connectsdk.service.capability.VolumeControl;
import com.connectsdk.service.command.ServiceCommandError;
import com.jaku.core.JakuRequest;
import com.jaku.core.KeypressKeyValues;
import com.jaku.request.KeypressRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ActivityCastFiles extends ActivityBase implements View.OnClickListener {

    private static final String TAG = ActivityCastFiles.class.getSimpleName();
    private final int REFRESH_INTERVAL_MS;
    CountDownTimer countDownTimer;

    boolean isPhoto;
    boolean isVideo;
    boolean isAudio;
    private int currentPos;
    private final MediaControl.DurationListener durationListener;
    public MediaControl.PlayStateListener playStateListener;
    private final MediaControl.PositionListener positionListener;
    private File file;
    boolean isMute;
    private boolean isPlaying;
    private MediaControl mMediaControl;
    int port;
    private Timer refreshTimer;
    private ServeHTTPD2 serveHTTPD;
    String titleCurrent;
    public long totalTimeDuration;
    private int typeCast;
    private List<MediaModel> listMedia = new ArrayList<>();
    private List<AudioModel> listAudio = new ArrayList<>();

    private Dialog waitDialog;
    ActivityCastFilesBinding binding;

    public ActivityCastFiles() {
        currentPos = 0;
        port = 8093;
        typeCast = 0;
        isPlaying = false;
        totalTimeDuration = -1L;
        isMute = false;
        titleCurrent = "";
        REFRESH_INTERVAL_MS = (int) TimeUnit.SECONDS.toMillis(1L);

        durationListener = new MediaControl.DurationListener() {
            @Override
            public void onError(ServiceCommandError serviceCommandError) {
                Log.e(TAG, "DurationListener onError: " + serviceCommandError.getMessage());
            }

            @Override
            public void onSuccess(Long duration) {
                totalTimeDuration = duration;
                Log.d(TAG, "DurationListener onSuccess: " + duration);

                binding.durationTotalTxt.setText(formatTime(duration.intValue()));
                binding.durationSeekbar.setMax(duration.intValue());
            }
        };

        playStateListener = new MediaControl.PlayStateListener() {
            @Override
            public void onError(ServiceCommandError serviceCommandError) {
                Log.e(TAG, "PlayStateListener onError: " + serviceCommandError.getMessage());
            }

            @Override
            public void onSuccess(MediaControl.PlayStateStatus playStateStatus) {
                switch (playStateStatus) {
                    case Playing:
                        startUpdating();
                        if (mMediaControl != null && TVConnectUtils.getInstance().getConnectableDevice().hasCapability(MediaControl.Duration)) {
                            mMediaControl.getDuration(durationListener);
                        }
                        break;

                    case Finished:
                        binding.durationCurrentTxt.setText("00:00");
                        binding.durationTotalTxt.setText("00:00");
                        binding.durationSeekbar.setProgress(0);
                        stopUpdating();
                        break;

                    default:
                        stopUpdating();
                        break;
                }
            }
        };

        positionListener = new MediaControl.PositionListener() {
            @Override
            public void onError(ServiceCommandError serviceCommandError) {
            }

            @Override
            public void onSuccess(Long l) {
                binding.durationCurrentTxt.setText(formatTime(l.intValue()));
                binding.durationSeekbar.setProgress(l.intValue());
            }
        };
    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        binding = ActivityCastFilesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EventBus.getDefault().register(this);

        AdsManager.displayAdaptiveBannerAd(this, findViewById(R.id.llAdaptiveBanner));

        if (getIntent() != null) {
            isPhoto = getIntent().getBooleanExtra("photo", isPhoto);
            isVideo = getIntent().getBooleanExtra("video", isVideo);
            isAudio = getIntent().getBooleanExtra("audio", isAudio);
        }

        configureBackPressedBehavior();
        dialogLoading();
        initView();

        if (isPlaying) {
            isPlaying = false;
            binding.castVideoPlayPauseIcon.setImageResource(R.drawable.cast_pause_img);
            return;
        } else {
            binding.castVideoPlayPauseIcon.setImageResource(R.drawable.cast_play_img);
            isPlaying = true;
        }
        countDownTimer = new CountDownTimer((long) (3.9 * 1000), 30) {
            @Override
            public void onTick(long millisUntilFinished) {
                double time = (double) (millisUntilFinished / 10) / 4;
                int progress = (int) (100 - time);
                binding.progressCircular.setProgress(progress);
            }

            @Override
            public void onFinish() {

            }
        };
    }

    public void dialogLoading() {
        try {
            waitDialog = new Dialog(ActivityCastFiles.this);
            waitDialog.setContentView(R.layout.dialog_please_wait);
            Objects.requireNonNull(waitDialog.getWindow()).setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            waitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            waitDialog.setCanceledOnTouchOutside(false);
            waitDialog.setCancelable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dismissWaitDialog() {
        if (waitDialog != null && waitDialog.isShowing() && (!isDestroyed() || !isFinishing())) {
            waitDialog.dismiss();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final MessageEvent messageEvent) {
        if (TVConnectUtils.getInstance().isConnected()) {
            binding.tvConnectImg.setImageResource(R.drawable.tv_connected_img);
        } else {
            binding.tvConnectImg.setImageResource(R.drawable.tv_disconnect_img);
            finish();
        }

        if (messageEvent.getMessage().equals("KEY_TIME_WEB")) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    binding.durationCurrentTxt.setText(formatTime(messageEvent.getDuration() * 1000));
                    binding.durationSeekbar.setProgress(((int) messageEvent.getDuration()) * 1000);

                    if (isPlaying) {
                        binding.castVideoPlayPauseIcon.setImageResource(R.drawable.cast_pause_img);
                    } else {
                        binding.castVideoPlayPauseIcon.setImageResource(R.drawable.cast_play_img);
                    }
                }
            });
        }
    }


    private void initView() {
        if (TVConnectUtils.getInstance().isConnected()) {
            binding.tvConnectImg.setImageResource(R.drawable.tv_connected_img);
        } else {
            binding.tvConnectImg.setImageResource(R.drawable.tv_disconnect_img);
        }

        binding.tvConnectLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                if (TVConnectUtils.getInstance().isConnected()) {
                    new DialogDisconnectTv(ActivityCastFiles.this).show();
                } else {
                    AdsManager.displayTimeBasedInterstitialAd(ActivityCastFiles.this, () -> {
                        startActivity(new Intent(ActivityCastFiles.this, ActivityConnect.class));
                    });
                }
            }
        });

        binding.fileInfoLay.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                String filePath = "";
                if (isAudio) {
                    if (ManagerDataPlay.getInstance().getListAudio() != null && !ManagerDataPlay.getInstance().getListAudio().isEmpty()) {
                        filePath = ManagerDataPlay.getInstance().getListAudio().get(currentPos).getSongPath();
                    }
                } else {
                    if (ManagerDataPlay.getInstance().getListMedia() != null && !ManagerDataPlay.getInstance().getListMedia().isEmpty()) {
                        filePath = ManagerDataPlay.getInstance().getListMedia().get(currentPos).getPhotoUri();
                    }
                }
                DialogFileInfo dialogPairingCode = new DialogFileInfo(ActivityCastFiles.this, filePath);
                dialogPairingCode.show();
            }
        });

        binding.castImageViewPager.disableScroll(false);
        binding.castVideoViewPager.disableScroll(false);

        binding.castImagePlayPauseIcon.setOnClickListener(this);
        binding.castImagePreviousIcon.setOnClickListener(this);
        binding.castImageNextIcon.setOnClickListener(this);

        binding.castVideoPlayPauseIcon.setOnClickListener(this);
        binding.castVideoPreviousIcon.setOnClickListener(this);
        binding.castVideoNextIcon.setOnClickListener(this);

        binding.castVolumeImg.setOnClickListener(this);
        binding.castVolumeDownImg.setOnClickListener(this);
        binding.castVolumeUpImg.setOnClickListener(this);
        binding.castVideoStopImg.setOnClickListener(this);

        try {
            listMedia = ManagerDataPlay.getInstance().getListMedia();
            listAudio = ManagerDataPlay.getInstance().getListAudio();
            typeCast = ManagerDataPlay.getInstance().getTypePlay();
            currentPos = ManagerDataPlay.getInstance().getPosSelected();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (TVConnectUtils.getInstance().getConnectableDevice() != null) {
            binding.durationSeekbar.setEnabled(TVConnectUtils.getInstance().getConnectableDevice().hasCapability(MediaControl.Seek));
        }
        binding.durationSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                try {
                    if (typeCast != 0) {
                        if (TVConnectUtils.getInstance().isConnectWeb) {
                            if (typeCast == 2) {
                                BrowserAction.seekAudio(seekBar.getProgress());
                            } else {
                                BrowserAction.seek(seekBar.getProgress());
                            }
                        } else if (TVConnectUtils.getInstance().isConnected()) {
                            mMediaControl.seek(seekBar.getProgress(), null);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        setSeekBarDuration();

        switch (typeCast) {
            case 0:
                if (listMedia == null) {
                    Toast.makeText(this, "Sorry, we are getting an error, please try again", Toast.LENGTH_SHORT).show();
                    return;
                } else if (listMedia.size() <= currentPos) {
                    return;
                } else {
                    binding.headerTxt.setText(getString(R.string.image));
                    binding.imageMainLay.setVisibility(View.VISIBLE);
                    binding.videoMainLay.setVisibility(View.GONE);
                    castPhoto(ManagerDataPlay.getInstance().getListMedia().get(currentPos).getPhotoUri());
                    setOfflineImageData();
                    return;
                }
            case 1:
                if (listMedia == null) {
                    Toast.makeText(this, "Sorry, we are getting an error, please try again", Toast.LENGTH_SHORT).show();
                    return;
                } else if (listMedia.size() <= currentPos) {
                    return;
                } else {
                    binding.headerTxt.setText(getString(R.string.video));
                    binding.imageMainLay.setVisibility(View.GONE);
                    binding.videoMainLay.setVisibility(View.VISIBLE);
                    castVideo();
                    setOfflineVideoData();
                    return;
                }
            case 2:
                if (listAudio == null) {
                    Toast.makeText(this, "Sorry, we are getting an error, please try again", Toast.LENGTH_SHORT).show();
                    return;
                } else if (listAudio.size() <= currentPos) {
                    return;
                } else {
                    binding.headerTxt.setText(getString(R.string.audio));
                    binding.imageMainLay.setVisibility(View.GONE);
                    binding.videoMainLay.setVisibility(View.VISIBLE);
                    castAudio();
                    setOfflineAudioData();
                    return;
                }
            case 3:
                binding.headerTxt.setText(getString(R.string.youtube));
                castYoutube(ManagerDataPlay.getInstance().pathCast);
                binding.imageMainLay.setVisibility(View.GONE);
                binding.videoMainLay.setVisibility(View.VISIBLE);
            default:
                return;
        }
    }

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 0;
    final long PERIOD_MS = 6000;
    Runnable Update;
    Handler handler;
    boolean isPlay = false;

    private void setSeekBarDuration() {
        if (TVConnectUtils.getInstance().isConnectWeb && ManagerDataPlay.getInstance().duration != null) {
            binding.durationTotalTxt.setText(formatTime(ManagerDataPlay.getInstance().duration));
            binding.durationSeekbar.setMax(ManagerDataPlay.getInstance().duration.intValue());
        }
    }

    private void setOfflineImageData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        binding.castImageListRv.setLayoutManager(linearLayoutManager);

        CastImageAdapter castImageAdapter = new CastImageAdapter(this, new ArrayList<>());
        castImageAdapter.setOnClickListener(i -> {
            if (!isPlay && binding.castImagePreviousIcon.getVisibility() == View.VISIBLE) {
                currentPos = i;
                binding.castImageViewPager.setCurrentItem(i, true);
            }
        });
        binding.castImageListRv.setAdapter(castImageAdapter);

        ArrayList<MediaModel> arrayList = new ArrayList<>();
        arrayList.add(new MediaModel());
        PageImageAdapter pageImageAdapter = new PageImageAdapter(arrayList);
        binding.castImageViewPager.setAdapter(pageImageAdapter);
        binding.castImageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                castImageAdapter.setSelectedPosition(i);
                linearLayoutManager.scrollToPosition(i);

                if (!isPlay && binding.castImagePreviousIcon.getVisibility() == View.VISIBLE) {
                    currentPos = i;
                    castPhoto(ManagerDataPlay.getInstance().getListMedia().get(currentPos).getPhotoUri());
                }
            }
        });
        CastImageAdapter imageAdapter2 = (CastImageAdapter) binding.castImageListRv.getAdapter();
        if (imageAdapter2 != null) {
            imageAdapter2.clearItems();
            imageAdapter2.addItems(ManagerDataPlay.getInstance().getListMedia());
        }
        pageImageAdapter.clearItems();
        pageImageAdapter.addItems(ManagerDataPlay.getInstance().getListMedia());
        binding.castImageViewPager.setAdapter(pageImageAdapter);
        binding.castImageViewPager.setCurrentItem(currentPos, true);
    }

    private void setOfflineVideoData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        binding.castVideoListRv.setLayoutManager(linearLayoutManager);

        CastVideoAdapter castVideoAdapter = new CastVideoAdapter(this, new ArrayList<>());
        castVideoAdapter.setOnClickListener(i -> {
            currentPos = i;
            binding.castVideoViewPager.setCurrentItem(i, true);
        });
        binding.castVideoListRv.setAdapter(castVideoAdapter);

        ArrayList<MediaModel> arrayList = new ArrayList<>();
        arrayList.add(new MediaModel());
        PageImageAdapter pageImageAdapter = new PageImageAdapter(arrayList);
        binding.castVideoViewPager.setAdapter(pageImageAdapter);

        binding.castVideoViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                castVideoAdapter.setSelectedPosition(i);
                linearLayoutManager.scrollToPosition(i);
                currentPos = i;
                castVideo();
            }
        });
        CastVideoAdapter castVideoAdapter1 = (CastVideoAdapter) binding.castVideoListRv.getAdapter();
        if (castVideoAdapter1 != null) {
            castVideoAdapter1.clearItems();
            castVideoAdapter1.addItems(ManagerDataPlay.getInstance().getListMedia());
        }
        pageImageAdapter.clearItems();
        pageImageAdapter.addItems(ManagerDataPlay.getInstance().getListMedia());
        binding.castVideoViewPager.setAdapter(pageImageAdapter);
        binding.castVideoViewPager.setCurrentItem(currentPos, true);
    }

    private void setOfflineAudioData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        binding.castVideoListRv.setLayoutManager(linearLayoutManager);

        CastAudioAdapter castAudioAdapter = new CastAudioAdapter(this, new ArrayList<>());
        castAudioAdapter.setOnClickListener(i -> {
            currentPos = i;
            binding.castVideoViewPager.setCurrentItem(i, true);
        });
        binding.castVideoListRv.setAdapter(castAudioAdapter);

        ArrayList<AudioModel> arrayList = new ArrayList<>();
        arrayList.add(new AudioModel());
        PageAudioAdapter pageImageAdapter = new PageAudioAdapter(arrayList);
        binding.castVideoViewPager.setAdapter(pageImageAdapter);

        binding.castVideoViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                castAudioAdapter.setSelectedPosition(i);
                linearLayoutManager.scrollToPosition(i);
                currentPos = i;
                castAudio();
            }
        });

        CastAudioAdapter castAudioAdapter1 = (CastAudioAdapter) binding.castVideoListRv.getAdapter();
        if (castAudioAdapter1 != null) {
            castAudioAdapter1.clearItems();
            castAudioAdapter1.addItems(ManagerDataPlay.getInstance().getListAudio());
        }
        pageImageAdapter.clearItems();
        pageImageAdapter.addItems(ManagerDataPlay.getInstance().getListAudio());
        binding.castVideoViewPager.setAdapter(pageImageAdapter);
        binding.castVideoViewPager.setCurrentItem(currentPos, true);
    }

    public void castPhoto(String str) {
        try {
            Tracking.trackCast(this, "start_cast", TVConnectUtils.getInstance().getConnectableDevice().getId(),
                    TVType.getTVType(TVConnectUtils.getInstance().getConnectableDevice()), "cast_photo_off");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (waitDialog != null) {
            waitDialog.show();
        }
        if (port == 0) {
            port = nextFreePort();
        }
        if (!listMedia.isEmpty()) {
            try {
                if (serveHTTPD != null) {
                    serveHTTPD.stop();
                }
                serveHTTPD = new ServeHTTPD2(port);
                serveHTTPD.start();
                String formatIpAddress = Formatter.formatIpAddress(((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getIpAddress());
                if (!str.contains("http")) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(new File(str).getAbsolutePath(), options);
                    int i = options.outHeight;
                    int i2 = options.outWidth;
                    str = "http://" + formatIpAddress + ":" + port + listMedia.get(currentPos).getPhotoUri();
                    if (TVConnectUtils.getInstance().isConnectWeb) {
                        try {
                            BrowserAction.displayImage(str, String.valueOf(i), String.valueOf(i2), "0");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dismissWaitDialog();
                                    countDownTimer.start();
                                }
                            });
                            return;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append(listMedia.size());
                sb.append("");
                StringBuilder sb2 = new StringBuilder();
                sb2.append(currentPos);
                sb2.append("");
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            if (TVConnectUtils.getInstance().getConnectableDevice() != null) {
                try {
                    TVConnectUtils.getInstance().getConnectableDevice().getCapability(MediaPlayer.class).displayImage(new MediaInfo.Builder(str, "image/jpeg").setTitle("Cast Photo").setDescription(str).build(), new MediaPlayer.LaunchListener() {
                        @Override
                        public void onSuccess(MediaPlayer.MediaLaunchObject object) {
                            Log.d("Akash", "onSuccess :: castPhoto");
                            dismissWaitDialog();
                            countDownTimer.start();
                            try {
                                Tracking.trackCast(ActivityCastFiles.this, "cast_success", TVConnectUtils.getInstance().getConnectableDevice().getId(), TVType.getTVType(TVConnectUtils.getInstance().getConnectableDevice()), "cast_photo_off");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ServiceCommandError error) {
                            Log.d("Akash", "onError :: castPhoto");
                            dismissWaitDialog();
                            try {
                                Tracking.trackCast(ActivityCastFiles.this, "cast_fail", TVConnectUtils.getInstance().getConnectableDevice().getId(), TVType.getTVType(TVConnectUtils.getInstance().getConnectableDevice()), "cast_photo_off");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
        }
    }

    public void castVideo() {
        if (waitDialog != null) {
            waitDialog.show();
        }
        Tracking.trackCast(this, "start_cast", new ConnectableDevice().getId(),
                TVType.getTVType(TVConnectUtils.getInstance().getConnectableDevice()), "cast_video_off");

        if (port == 0) {
            port = nextFreePort();
        }
        if (listMedia == null || listMedia.isEmpty()) {
            return;
        }
        String photoUri = listMedia.get(currentPos).getPhotoUri();

        try {
            if (serveHTTPD != null) {
                serveHTTPD.stop();
                serveHTTPD = null;
            }
            serveHTTPD = new ServeHTTPD2(port);
            serveHTTPD.start();
        } catch (IOException unused2) {
            Log.e(TAG, "castVideo: IOException ==> " + unused2.getMessage());
        }

        String formatIpAddress = Formatter.formatIpAddress(((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getIpAddress());
        file = new File(photoUri);

        String str;
        if (getIntent().getBooleanExtra("isOnlineVideo", false)) {
            str = photoUri;
        } else {
            str = "http://" + formatIpAddress + ":" + port + file.getPath();
        }

        if (TVConnectUtils.getInstance().isConnectWeb) {
            ManagerDataPlay.getInstance().duration = listMedia.get(currentPos).getDuration();
            setSeekBarDuration();

            BrowserAction.playVideo(str);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dismissWaitDialog();
                }
            });
            return;
        }

        Log.e(TAG, "castVideo str is not connected to Web : " + str);
        if (TVConnectUtils.getInstance().getConnectableDevice() == null) {
            Log.e(TAG, "castVideo: returned");
            return;
        }
        try {
            ((MediaPlayer) TVConnectUtils.getInstance().getConnectableDevice().getCapability(MediaPlayer.class)).playMedia(new MediaInfo.Builder(str, "video/mp4").setTitle(file.getName()).setDescription("Casting your Video").build(), true, new MediaPlayer.LaunchListener() {
                @Override
                public void onError(ServiceCommandError serviceCommandError) {
                    Log.d("Akash", "onSuccess :: castVideo");
                    isPlaying = false;
                    dismissWaitDialog();
                    try {
                        Tracking.trackCast(ActivityCastFiles.this, "cast_fail", TVConnectUtils.getInstance().getConnectableDevice().getId(),
                                TVType.getTVType(TVConnectUtils.getInstance().getConnectableDevice()), "cast_video_off");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override
                public void onSuccess(MediaPlayer.MediaLaunchObject mediaLaunchObject) {
                    Log.d("Akash", "onSuccess :: castVideo");
                    isPlaying = true;
                    binding.castVideoPlayPauseIcon.setImageResource(R.drawable.cast_pause_img);
                    controlDuration(mediaLaunchObject);
                    dismissWaitDialog();
                    try {
                        Tracking.trackCast(ActivityCastFiles.this, "cast_success", TVConnectUtils.getInstance().getConnectableDevice().getId(),
                                TVType.getTVType(TVConnectUtils.getInstance().getConnectableDevice()), "cast_video_off");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void castAudio() {
        if (waitDialog != null) {
            waitDialog.show();
        }
        if (port == 0) {
            port = nextFreePort();
        }
        if (!listAudio.isEmpty()) {
            String songPath = listAudio.get(currentPos).getSongPath();
            try {
                if (serveHTTPD != null) {
                    serveHTTPD.stop();
                }
                serveHTTPD = new ServeHTTPD2(port);
                serveHTTPD.start();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            String formatIpAddress = Formatter.formatIpAddress(((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getIpAddress());
            file = new File(songPath);
            String str = "http://" + formatIpAddress + ":" + port + file.getPath();
            if (TVConnectUtils.getInstance().isConnectWeb) {
                ManagerDataPlay.getInstance().duration = listAudio.get(currentPos).getDuration();
                setSeekBarDuration();

                BrowserAction.playAudio(str);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissWaitDialog();
                    }
                });
            } else if (TVConnectUtils.getInstance().getConnectableDevice() != null) {

                try {
                    Tracking.trackCast(this, "start_cast", TVConnectUtils.getInstance().getConnectableDevice().getId(), TVType.getTVType(TVConnectUtils.getInstance().getConnectableDevice()), "cast_audio");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    TVConnectUtils.getInstance().getConnectableDevice().getCapability(MediaPlayer.class).playMedia(new MediaInfo.Builder(str, "audio/mp3").setTitle(file.getName()).setDescription("Casting your Audio").build(), true, new MediaPlayer.LaunchListener() {
                        @Override
                        public void onSuccess(MediaPlayer.MediaLaunchObject object) {
                            Log.d("Akash", "onSuccess :: castAudio");
                            isPlaying = true;
                            binding.castVideoPlayPauseIcon.setImageResource(R.drawable.cast_pause_img);
                            controlDuration(object);
                            dismissWaitDialog();
                            try {
                                Tracking.trackCast(ActivityCastFiles.this, "cast_success", TVConnectUtils.getInstance().getConnectableDevice().getId(), TVType.getTVType(TVConnectUtils.getInstance().getConnectableDevice()), "cast_audio");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ServiceCommandError error) {
                            Log.d("Akash", "onError :: castAudio");
                            isPlaying = false;
                            dismissWaitDialog();
                            try {
                                Tracking.trackCast(ActivityCastFiles.this, "cast_fail", TVConnectUtils.getInstance().getConnectableDevice().getId(), TVType.getTVType(TVConnectUtils.getInstance().getConnectableDevice()), "cast_audio");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    private void castYoutube(String str) {

        if (waitDialog != null) {
            waitDialog.show();
        }
        if (this.port == 0) {
            this.port = nextFreePort();
        }
        try {
            binding.castVideoViewPager.setVisibility(View.GONE);
            binding.thumbnailYoutubeVideoImg.setVisibility(View.VISIBLE);
            Glide.with(this).load(ManagerDataPlay.getInstance().thumbCast)
                    .centerCrop()
                    .placeholder(R.drawable.placeholder_img)
                    .into(binding.thumbnailYoutubeVideoImg);

            if (TVConnectUtils.getInstance().isConnectWeb) {
                BrowserAction.playVideo(str);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissWaitDialog();
                    }
                });
                return;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        try {
            ServeHTTPD2 serveHTTPD2 = this.serveHTTPD;
            if (serveHTTPD2 != null) {
                serveHTTPD2.stop();
            }
            ServeHTTPD2 serveHTTPD22 = new ServeHTTPD2(this.port);
            this.serveHTTPD = serveHTTPD22;
            serveHTTPD22.start();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        Formatter.formatIpAddress(((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getIpAddress());
        this.file = new File(str);
        try {
            MediaInfo build = new MediaInfo.Builder(str, "video/mp4").setTitle(this.file.getName())
                    .setDescription("Casting your Video").build();
            build.getUrl();
            ((MediaPlayer) TVConnectUtils.getInstance().getConnectableDevice().getCapability(MediaPlayer.class))
                    .playMedia(build, true, new MediaPlayer.LaunchListener() {
                        @Override
                        public void onError(ServiceCommandError serviceCommandError) {
                            Log.d("Akash", "onError :: castYoutube");
                            serviceCommandError.toString();
                            isPlaying = false;
                            dismissWaitDialog();
                            try {
                                Tracking.trackCast(ActivityCastFiles.this, "cast_fail", TVConnectUtils.getInstance().getConnectableDevice().getId(),
                                        TVType.getTVType(TVConnectUtils.getInstance().getConnectableDevice()), "cast_youtube");
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }

                        @Override
                        public void onSuccess(MediaPlayer.MediaLaunchObject mediaLaunchObject) {
                            Log.d("Akash", "onSuccess :: castYoutube");
                            mediaLaunchObject.toString();
                            isPlaying = true;
                            binding.castVideoPlayPauseIcon.setImageResource(R.drawable.cast_pause_img);
                            controlDuration(mediaLaunchObject);
                            dismissWaitDialog();
                            try {
                                Tracking.trackCast(ActivityCastFiles.this, "cast_success", TVConnectUtils.getInstance().getConnectableDevice().getId(),
                                        TVType.getTVType(TVConnectUtils.getInstance().getConnectableDevice()), "cast_youtube");
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                    });
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void controlDuration(MediaPlayer.MediaLaunchObject mediaLaunchObject) {
        mMediaControl = mediaLaunchObject.mediaControl;
        stopUpdating();
        enableMedia();
    }

    public void stopUpdating() {
        if (refreshTimer == null) {
            return;
        }
        refreshTimer.cancel();
        this.refreshTimer = null;
    }

    private void enableMedia() {
        try {
            if (TVConnectUtils.getInstance().getConnectableDevice() != null && TVConnectUtils.getInstance().getConnectableDevice().hasCapability(MediaControl.PlayState_Subscribe) && !isPlaying) {
                mMediaControl.subscribePlayState(playStateListener);
                return;
            }
            if (mMediaControl != null) {
                mMediaControl.getDuration(durationListener);
            }
            startUpdating();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startUpdating() {
        try {
            if (refreshTimer != null) {
                refreshTimer.cancel();
                this.refreshTimer = null;
            }
            refreshTimer = new Timer();
            refreshTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (mMediaControl != null && TVConnectUtils.getInstance().getConnectableDevice() != null && TVConnectUtils.getInstance().getConnectableDevice().hasCapability(MediaControl.Position)) {
                        mMediaControl.getPosition(positionListener);
                    }
                    if (mMediaControl == null || TVConnectUtils.getInstance().getConnectableDevice() == null || !TVConnectUtils.getInstance().getConnectableDevice().hasCapability(MediaControl.Duration) || totalTimeDuration > 0) {
                        return;
                    }
                    mMediaControl.getDuration(durationListener);
                }
            }, 0L, REFRESH_INTERVAL_MS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int nextFreePort() {
        Random random = new Random();
        int nextInt = random.nextInt(999);
        while (true) {
            int i3 = nextInt + 8000;
            if (isLocalPortFree(i3)) {
                return i3;
            }
            nextInt = random.nextInt(999);
        }
    }

    private boolean isLocalPortFree(int i) {
        try {
            new ServerSocket(i).close();
            return true;
        } catch (IOException exception) {
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.castImagePlayPauseIcon) {
            if (isPlay) {
                isPlay = false;
                binding.castImagePlayPauseIcon.setImageResource(R.drawable.cast_play_img);
                binding.castImageNextIcon.setVisibility(View.VISIBLE);
                binding.castImagePreviousIcon.setVisibility(View.VISIBLE);
                binding.fileInfoLay.setVisibility(View.VISIBLE);
                binding.progressCircular.setVisibility(View.GONE);
                if (timer != null)
                    timer.cancel();
                if (countDownTimer != null)
                    countDownTimer.cancel();
                binding.castImageViewPager.disableScroll(false);
            } else {
                isPlay = true;
                binding.castImagePlayPauseIcon.setImageResource(R.drawable.cast_pause_img);
                binding.castImageNextIcon.setVisibility(View.GONE);
                binding.castImagePreviousIcon.setVisibility(View.GONE);
                binding.fileInfoLay.setVisibility(View.GONE);
                binding.progressCircular.setVisibility(View.GONE);
                setAnimation((ArrayList) listMedia);
                binding.castImageViewPager.disableScroll(true);
            }
        } else if (id == R.id.castImageNextIcon) {
            try {
                currentPos++;
                if (typeCast == 0) {
                    if (listMedia != null && !listMedia.isEmpty()) {
                        if (currentPos >= listMedia.size()) {
                            currentPos = 0;
                        }
                    }
                }
                binding.castImageViewPager.setCurrentItem(currentPos, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.castVideoNextIcon) {
            try {
                int i2 = currentPos + 1;
                currentPos = i2;
                binding.castVideoViewPager.setCurrentItem(currentPos, true);

                if (typeCast == 2) {
                    if (i2 >= listAudio.size()) {
                        currentPos = 0;
                    }
                } else if (typeCast == 1) {
                    if (i2 >= listMedia.size()) {
                        currentPos = 0;
                    }
                } else if (typeCast != 3) {
                    return;
                } else {
                    Toast.makeText(this, "Not Support", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (id == R.id.castVideoPlayPauseIcon) {
            if (TVConnectUtils.getInstance().isConnectWeb) {
                int i4 = typeCast;
                if (i4 == 2 || i4 == 10) {
                    if (isPlaying) {
                        isPlaying = false;
                        binding.castVideoPlayPauseIcon.setImageResource(R.drawable.cast_play_img);
                        BrowserAction.pauseAudio();
                        return;
                    }
                    binding.castVideoPlayPauseIcon.setImageResource(R.drawable.cast_pause_img);
                    isPlaying = true;
                    BrowserAction.playingAudio();
                } else if (isPlaying) {
                    binding.castVideoPlayPauseIcon.setImageResource(R.drawable.cast_play_img);
                    isPlaying = false;
                    BrowserAction.pause();
                } else {
                    binding.castVideoPlayPauseIcon.setImageResource(R.drawable.cast_pause_img);
                    isPlaying = true;
                    BrowserAction.play();
                }
            } else if (isPlaying) {
                try {
                    mMediaControl.pause(null);
                    isPlaying = false;
                    binding.castVideoPlayPauseIcon.setImageResource(R.drawable.cast_play_img);
                    binding.castVideoViewPager.disableScroll(false);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } else {
                try {
                    mMediaControl.play(null);
                    binding.castVideoPlayPauseIcon.setImageResource(R.drawable.cast_pause_img);
                    isPlaying = true;
                    binding.castVideoViewPager.disableScroll(true);
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        } else if (id == R.id.castImagePreviousIcon) {
            try {
                currentPos--;
                int i5 = typeCast;
                if (i5 == 0) {
                    if (listMedia != null && !listMedia.isEmpty()) {
                        if (currentPos < 0) {
                            currentPos = listMedia.size() - 1;
                        }
                    }
                }
                binding.castImageViewPager.setCurrentItem(currentPos, true);
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        } else if (id == R.id.castVideoPreviousIcon) {
            try {
                int i6 = currentPos - 1;
                currentPos = i6;
                int i7 = typeCast;

                binding.castVideoViewPager.setCurrentItem(currentPos, true);

                if (i7 == 2) {
                    if (i6 < 0) {
                        currentPos = listAudio.size() - 1;
                    }
                } else if (i7 == 1) {
                    if (i6 < 0) {
                        currentPos = listMedia.size() - 1;
                    }
                } else if (i7 != 3) {
                    return;
                } else {
                    Toast.makeText(this, "Not Support", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e6) {
                e6.printStackTrace();
            }
        } else if (id == R.id.castVolumeImg) {
            setVolumeMute();
        } else if (id == R.id.castVolumeDownImg) {
            if (TVConnectUtils.getInstance().isConnectWeb) {
                if (!isMute) {
                    if (typeCast == 2) {
                        BrowserAction.volumeDownAudio(10);
                    } else {
                        BrowserAction.volumeDown(10);
                    }
                }
            } else if (TVConnectUtils.getInstance().isConnected()) {
                if (!isMute) {
                    setupVolume(false);
                }
            }
        } else if (id == R.id.castVolumeUpImg) {
            if (TVConnectUtils.getInstance().isConnectWeb) {
                if (!isMute) {
                    if (typeCast == 2) {
                        BrowserAction.volumeUpAudio(10);
                    } else {
                        BrowserAction.volumeUp(10);
                    }
                }
            } else if (TVConnectUtils.getInstance().isConnected()) {
                if (!isMute) {
                    setupVolume(true);
                }
            }
        } else if (id == R.id.castVideoStopImg) {
            if (TVConnectUtils.getInstance().isConnectWeb) {
                if (this.typeCast == 2) {
                    BrowserAction.stopAudio();
                } else {
                    BrowserAction.stop();
                }
            }
            getOnBackPressedDispatcher().onBackPressed();
        }
    }

    public void setAnimation(ArrayList list) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        binding.progressCircular.setMax(100);
        binding.progressCircular.setProgress(0);

        handler = new Handler();
        Update = new Runnable() {
            public void run() {
                if (currentPage == list.size() - 1) {
                    currentPage = 0;
                }

                currentPos = currentPos + 1;

                if (currentPos >= list.size()) {
                    currentPos = 0;
                }

                castPhoto(ManagerDataPlay.getInstance().getListMedia().get(currentPos).getPhotoUri());
                binding.castImageViewPager.setCurrentItem(currentPos, true);
                binding.progressCircular.setProgress(0);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    public void setVolumeMute() {
        try {
            if (TVConnectUtils.getInstance().isConnectWeb) {
                if (isMute) {
                    if (typeCast == 2) {
                        binding.castVolumeImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cast_volume_on_img));
                        BrowserAction.unMuteAudio();
                    } else {
                        binding.castVolumeImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cast_volume_on_img));
                        BrowserAction.unMute();
                    }
                } else if (typeCast == 2) {
                    binding.castVolumeImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cast_volume_off_img));
                    BrowserAction.muteAudio();
                } else {
                    binding.castVolumeImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cast_volume_off_img));
                    BrowserAction.mute();
                }
                isMute = !isMute;
            } else if (TVConnectUtils.getInstance().isConnected()) {
                if (isMute) {
                    if (typeCast == 2) {
                        binding.castVolumeImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cast_volume_on_img));
                        BrowserAction.unMuteAudio();
                    } else {
                        binding.castVolumeImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cast_volume_on_img));
                        BrowserAction.unMute();
                    }
                } else if (typeCast == 2) {
                    binding.castVolumeImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cast_volume_off_img));
                    BrowserAction.muteAudio();
                } else {
                    binding.castVolumeImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cast_volume_off_img));
                    BrowserAction.mute();
                }
                isMute = !isMute;
                isMute = isMute ? false : true;
            }
            if (TVType.getTVType(TVConnectUtils.getInstance().getConnectableDevice()).equalsIgnoreCase("RokuTV")) {
                isMute = isMute ? false : true;
                performKeypress(KeypressKeyValues.VOLUME_MUTE);
            }
            VolumeControl volumeControl = (VolumeControl) TVConnectUtils.getInstance().getConnectableDevice().getCapability(VolumeControl.class);
            if (volumeControl == null) {
                return;
            }
            boolean z = !isMute;
            isMute = z;
            volumeControl.setMute(z, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupVolume(boolean z) {
        float max;
        try {
            if (TVType.getTVType(TVConnectUtils.getInstance().getConnectableDevice()).equalsIgnoreCase("RokuTV")) {
                if (z) {
                    performKeypress(KeypressKeyValues.VOLUME_UP);
                } else {
                    performKeypress(KeypressKeyValues.VOLUME_DOWN);
                }
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        VolumeControl volumeControl = (VolumeControl) TVConnectUtils.getInstance().getConnectableDevice().getCapability(VolumeControl.class);
        if (volumeControl != null) {
            if (z) {
                max = Math.min(TVConnectUtils.getInstance().volume + 0.01f, 1.0f);
            } else {
                max = Math.max(TVConnectUtils.getInstance().volume - 0.01f, 0.0f);
            }
            TVConnectUtils.getInstance().volume = max;
            volumeControl.setVolume(TVConnectUtils.getInstance().volume, null);
        }
    }

    private void performKeypress(KeypressKeyValues keypressKeyValues) {
        if (TVConnectUtils.getInstance().isConnected()) {
            String deviceURL = CommandHelper.getDeviceURL(this);
            StringBuilder sb = new StringBuilder();
            sb.append("performKeypress: ");
            sb.append(deviceURL);
            performRequest(new JakuRequest(new KeypressRequest(deviceURL, keypressKeyValues.getValue()), null), RokuRequestTypes.keypress);
        }
    }

    private void performRequest(JakuRequest jakuRequest, RokuRequestTypes rokuRequestTypes) {
        if (getApplicationContext() != null) {
            Observable.fromCallable(new RxRequestTask(getApplicationContext(), jakuRequest, rokuRequestTypes))
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
                        @Override
                        public void accept(Object o) {

                        }
                    });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (serveHTTPD != null) {
            serveHTTPD.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
        EventBus.getDefault().unregister(this);
    }

    public String formatTime(long j) {
        int i = (int) (j / 1000);
        int i2 = i / 3600;
        int i3 = i % 3600;
        int i4 = i3 / 60;
        int i5 = i3 % 60;
        return i2 > 0 ? String.format(Locale.US, "%d:%02d:%02d", i2, i4, i5) : String.format(Locale.US, "%d:%02d", i4, i5);
    }

    private void configureBackPressedBehavior() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (mMediaControl != null)
                    mMediaControl.pause(null);
                if (timer != null)
                    timer.cancel();
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        binding.backLay.setOnClickListener(view -> {
            callback.handleOnBackPressed();
        });
    }
}
