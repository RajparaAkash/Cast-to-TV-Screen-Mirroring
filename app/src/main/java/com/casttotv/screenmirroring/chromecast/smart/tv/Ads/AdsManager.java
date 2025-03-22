package com.casttotv.screenmirroring.chromecast.smart.tv.Ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.casttotv.screenmirroring.chromecast.smart.tv.BuildConfig;
import com.casttotv.screenmirroring.chromecast.smart.tv.MyApplication;
import com.casttotv.screenmirroring.chromecast.smart.tv.Preferences.PrefHelper;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.NetworkUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;

import java.util.Objects;

public class AdsManager {

    /* Adaptive Banner Ad */
    public static void displayAdaptiveBannerAd(Activity activity, ViewGroup adContainer) {
        int currentVersionCode = BuildConfig.VERSION_CODE;
        if (!MyApplication.canRequestAds || !NetworkUtils.isInternetAvailable(activity)
                || PrefHelper.getInstance().getAdsData().getAppA() == 0
                || PrefHelper.getInstance().getAdsData().getAppV() == currentVersionCode) {
            adContainer.removeAllViews();
            adContainer.setVisibility(View.GONE);
            return;
        }
        adContainer.setVisibility(View.VISIBLE);
        loadAdmobBannerAd(activity, PrefHelper.getInstance().getAdsData().getBannerTag(), adContainer);
    }

    public static void displayCollapsibleBannerAd(Activity activity, ViewGroup adContainer, String position) {
        int currentVersionCode = BuildConfig.VERSION_CODE;
        if (!MyApplication.canRequestAds || !NetworkUtils.isInternetAvailable(activity)
                || PrefHelper.getInstance().getAdsData().getAppA() == 0
                || PrefHelper.getInstance().getAdsData().getAppV() == currentVersionCode) {
            adContainer.removeAllViews();
            adContainer.setVisibility(View.GONE);
            return;
        }
        adContainer.setVisibility(View.VISIBLE);
        loadAdmobBannerAdCollapsible(activity, PrefHelper.getInstance().getAdsData().getBannerTag(), adContainer, position);
    }

    private static void loadAdmobBannerAd(Activity activity, String bannerAdUnitId, ViewGroup adContainer) {
        AdView adView = new AdView(activity);
        adView.setAdUnitId(bannerAdUnitId);
        AdSize adSize = calculateAdSize(activity, adContainer);
        adView.setAdSize(adSize);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                super.onAdFailedToLoad(adError);
                adContainer.removeAllViews();
                adContainer.setVisibility(View.GONE);
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                adContainer.removeAllViews();
                adContainer.addView(adView);
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdSwipeGestureClicked() {
                super.onAdSwipeGestureClicked();
            }
        });
    }

    private static void loadAdmobBannerAdCollapsible(Activity activity, String bannerAdUnitId, ViewGroup adContainer, String position) {
        AdView adView = new AdView(activity);
        adView.setAdUnitId(bannerAdUnitId);
        AdSize adSize = calculateAdSize(activity, adContainer);
        adView.setAdSize(adSize);

        Bundle extras = new Bundle();
        extras.putString("collapsible", position);
        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                super.onAdFailedToLoad(adError);
                adContainer.removeAllViews();
                adContainer.setVisibility(View.GONE);
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                adContainer.removeAllViews();
                adContainer.addView(adView);
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdSwipeGestureClicked() {
                super.onAdSwipeGestureClicked();
            }
        });
    }

    public static AdSize calculateAdSize(Activity activity, ViewGroup adContainer) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        float density = metrics.density;

        float adWidthInPixels = adContainer.getWidth();

        if (adWidthInPixels == 0) {
            adWidthInPixels = metrics.widthPixels;
        }

        int adWidth = (int) (adWidthInPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }


    /* Native Ad */
    public static NativeAd adNative = null;
    public static boolean isAdNativeLoading = false;

    public static void loadNativeAds(Context context) {
        int currentVersionCode = BuildConfig.VERSION_CODE;
        if (!MyApplication.canRequestAds || PrefHelper.getInstance().getAdsData().getAppA() == 0 || PrefHelper.getInstance().getAdsData().getAppV() == currentVersionCode) {
            return;
        }

        String nativeAdTag = PrefHelper.getInstance().getAdsData().getNativeTag();
        if (NetworkUtils.isInternetAvailable(context) && nativeAdTag != null && !nativeAdTag.isEmpty()) {
            loadNativeAdFromAdmob(context);
        }
    }

    public static void displayLargeNativeAd(Activity activity, ViewGroup largeAdContainer, ViewGroup adContainer) {
        if (shouldHideAds()) {
            hideNativeAds(largeAdContainer, adContainer);
            return;
        }

        if (adNative != null) {
            NativeAd nativeAd = adNative;
            displayNativeAd(activity, nativeAd, largeAdContainer, adContainer, R.layout.ad_layout_native_large);
        } else {
            hideNativeAds(largeAdContainer, adContainer);
            loadNativeAds(activity);
        }
    }

    public static void displaySmallNativeAd(Activity activity, ViewGroup smallAdContainer, ViewGroup adContainer) {
        if (shouldHideAds()) {
            hideNativeAds(smallAdContainer, adContainer);
            return;
        }

        if (adNative != null) {
            NativeAd nativeAd = adNative;
            displayNativeAd(activity, nativeAd, smallAdContainer, adContainer, R.layout.ad_layout_native_small);
        } else {
            hideNativeAds(smallAdContainer, adContainer);
            loadNativeAds(activity);
        }
    }

    private static boolean shouldHideAds() {
        int currentVersionCode = BuildConfig.VERSION_CODE;
        return PrefHelper.getInstance().getAdsData().getAppA() == 0 || PrefHelper.getInstance().getAdsData().getAppV() == currentVersionCode;
    }

    private static void hideNativeAds(ViewGroup adLayout, ViewGroup parentLayout) {
        adLayout.removeAllViews();
        adLayout.setVisibility(View.GONE);
        parentLayout.setVisibility(View.GONE);
    }

    private static void displayNativeAd(Activity activity, NativeAd nativeAd, ViewGroup adLayout, ViewGroup parentLayout, int layoutResId) {
        parentLayout.setVisibility(View.VISIBLE);
        adLayout.setVisibility(View.VISIBLE);

        NativeAdView adView = (NativeAdView) activity.getLayoutInflater().inflate(layoutResId, adLayout, false);
        populateGoogleNativeAdView(nativeAd, adView);

        adLayout.removeAllViews();
        adLayout.addView(adView);

        loadNativeAds(activity);
    }

    private static void loadNativeAdFromAdmob(Context context) {
        if (isAdNativeLoading) {
            return;
        }

        isAdNativeLoading = true;
        String nativeTag = PrefHelper.getInstance().getAdsData().getNativeTag();

        AdLoader.Builder builder = new AdLoader.Builder(context, nativeTag);
        builder.forNativeAd(nativeAd -> {
            isAdNativeLoading = false;
            adNative = nativeAd;
        });

        VideoOptions videoOptions = new VideoOptions.Builder().setStartMuted(true).build();
        NativeAdOptions adOptions = new NativeAdOptions.Builder().setVideoOptions(videoOptions).build();
        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                isAdNativeLoading = false;
                // Log or handle ad load failure
            }
        }).build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public static void populateGoogleNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        // Set the media view.
        adView.setMediaView(adView.findViewById(R.id.ad_media));

        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline and mediaContent are guaranteed to be in every NativeAd.
        ((TextView) Objects.requireNonNull(adView.getHeadlineView())).setText(nativeAd.getHeadline());
        Objects.requireNonNull(adView.getMediaView()).setMediaContent(nativeAd.getMediaContent());

        // These assets aren't guaranteed to be in every NativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            Objects.requireNonNull(adView.getBodyView()).setVisibility(View.INVISIBLE);
        } else {
            Objects.requireNonNull(adView.getBodyView()).setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            Objects.requireNonNull(adView.getCallToActionView()).setVisibility(View.GONE);
        } else {
            Objects.requireNonNull(adView.getCallToActionView()).setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            Objects.requireNonNull(adView.getIconView()).setVisibility(View.GONE);
        } else {
            if (Objects.requireNonNull(adView.getIconView()).getVisibility() == View.VISIBLE) {
                ((ImageView) Objects.requireNonNull(adView.getIconView())).setImageDrawable(
                        nativeAd.getIcon().getDrawable());
                adView.getIconView().setVisibility(View.VISIBLE);
            }
        }

        if (nativeAd.getPrice() == null) {
            Objects.requireNonNull(adView.getPriceView()).setVisibility(View.GONE);
        } else {
            Objects.requireNonNull(adView.getPriceView()).setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            Objects.requireNonNull(adView.getStoreView()).setVisibility(View.GONE);
        } else {
            Objects.requireNonNull(adView.getStoreView()).setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            Objects.requireNonNull(adView.getStarRatingView()).setVisibility(View.GONE);
        } else {
            ((RatingBar) Objects.requireNonNull(adView.getStarRatingView())).setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            Objects.requireNonNull(adView.getAdvertiserView()).setVisibility(View.INVISIBLE);
        } else {
            ((TextView) Objects.requireNonNull(adView.getAdvertiserView())).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        adView.setNativeAd(nativeAd);

        VideoController vc = Objects.requireNonNull(nativeAd.getMediaContent()).getVideoController();

        if (nativeAd.getMediaContent() != null && nativeAd.getMediaContent().hasVideoContent()) {
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
        }
    }


    /* Interstitial Ad */
    public static InterstitialAd adInterstitial = null;
    public static boolean isAdInterstitialLoading = false;
    public static AdCompletionListener adCompletionListener;
    public static long lastAdCloseTime = 0;
    private static int clickCount = 0;

    public interface AdCompletionListener {
        void onAdCompleted();
    }

    public static void displayTimeBasedInterstitialAd(Activity activity, AdCompletionListener adListener) {
        adCompletionListener = adListener;

        if (!canRequestAd()) {
            completeAd();
            return;
        }

        clickCount++;
        long currentTime = System.currentTimeMillis();
        if (PrefHelper.getInstance().getAdsData().getAdSec() != 0 && PrefHelper.getInstance().getAdsData().getInterCount() != 0) {
            int adIntervalMillis = PrefHelper.getInstance().getAdsData().getAdSec() * 1000;
            boolean isTimeConditionMet = (currentTime - lastAdCloseTime) >= adIntervalMillis;

            int adClickThreshold = PrefHelper.getInstance().getAdsData().getInterCount();
            boolean isClickConditionMet = (clickCount >= adClickThreshold);

            if (isTimeConditionMet || isClickConditionMet) {
                Log.d("TAG", "isTimeConditionMet :: " + isTimeConditionMet);
                Log.d("TAG", "isClickConditionMet :: " + isClickConditionMet);

                if (adInterstitial != null) {
                    InterstitialAd interstitialAd = adInterstitial;
                    setupFullScreenCallback(activity, interstitialAd);
                    interstitialAd.show(activity);
                } else {
                    completeAd();
                    loadInterstitialAd(activity);
                }
            } else {
                completeAd();
            }

        } else if (PrefHelper.getInstance().getAdsData().getAdSec() != 0) {
            int adIntervalMillis = PrefHelper.getInstance().getAdsData().getAdSec() * 1000;
            boolean isTimeConditionMet = (currentTime - lastAdCloseTime) >= adIntervalMillis;

            if (isTimeConditionMet) {
                Log.d("TAG", "isTimeConditionMet :: " + isTimeConditionMet);

                if (adInterstitial != null) {
                    adInterstitial.show(activity);
                } else {
                    completeAd();
                    loadInterstitialAd(activity);
                }
            } else {
                completeAd();
            }
        } else if (PrefHelper.getInstance().getAdsData().getInterCount() != 0) {
            int adClickThreshold = PrefHelper.getInstance().getAdsData().getInterCount();
            boolean isClickConditionMet = (clickCount >= adClickThreshold);

            if (isClickConditionMet) {
                Log.d("TAG", "isClickConditionMet :: " + isClickConditionMet);

                if (adInterstitial != null) {
                    adInterstitial.show(activity);
                } else {
                    completeAd();
                    loadInterstitialAd(activity);
                }
            } else {
                completeAd();
            }
        } else {
            completeAd();
        }
    }

    public static void loadInterstitialAd(Activity activity) {
        if (!canRequestAd() || isAdInterstitialLoading) {
            return;
        }
        isAdInterstitialLoading = true;
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(activity, PrefHelper.getInstance().getAdsData().getInterTag(), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd ad) {
                adInterstitial = ad;
                isAdInterstitialLoading = false;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                isAdInterstitialLoading = false;
            }
        });
    }

    private static void setupFullScreenCallback(Activity activity, InterstitialAd interstitialAd) {
        interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                lastAdCloseTime = System.currentTimeMillis();
                clickCount = 0;
                loadInterstitialAd(activity);
                completeAd();
                MyApplication.isAdShowing = false;
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                loadInterstitialAd(activity);
                completeAd();
                MyApplication.isAdShowing = false;
            }

            @Override
            public void onAdShowedFullScreenContent() {
                MyApplication.isAdShowing = true;
            }
        });
    }

    public void showInterstitialSplash(Activity activity, AdCompletionListener adListener, ViewGroup adContainer) {
        adCompletionListener = adListener;

        if (!canRequestAd()) {
            completeAd();
            return;
        }

        if (PrefHelper.getInstance().getAdsData().getBannerTag() == null
                || PrefHelper.getInstance().getAdsData().getBannerTag().equalsIgnoreCase("")
                || PrefHelper.getInstance().getAdsData().getBannerTag().equalsIgnoreCase("null")) {

            loadAndShowInterstitialSplash(activity);
            return;
        }
        adContainer.setVisibility(View.VISIBLE);

        AdView adView = new AdView(activity);
        adView.setAdUnitId(PrefHelper.getInstance().getAdsData().getBannerTag());
        AdSize adSize = calculateAdSize(activity, adContainer);
        adView.setAdSize(adSize);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                super.onAdFailedToLoad(adError);
                adContainer.removeAllViews();
                adContainer.setVisibility(View.GONE);

                loadAndShowInterstitialSplash(activity);
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                adContainer.removeAllViews();
                adContainer.addView(adView);

                loadAndShowInterstitialSplash(activity);
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdSwipeGestureClicked() {
                super.onAdSwipeGestureClicked();
            }
        });
    }

    private void loadAndShowInterstitialSplash(Activity activity) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(activity, PrefHelper.getInstance().getAdsData().getInterTag(), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                adInterstitial = interstitialAd;
                setupFullScreenCallback(activity, interstitialAd);
                adInterstitial.show(activity);
                AdsManager.loadNativeAds(activity);
                new AppOpenAdManager(MyApplication.getInstance());
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                completeAd();
                AdsManager.loadNativeAds(activity);
                new AppOpenAdManager(MyApplication.getInstance());
                loadInterstitialAd(activity);
            }
        });
    }

    private static boolean canRequestAd() {
        if (!MyApplication.canRequestAds) return false;

        int appA = PrefHelper.getInstance().getAdsData().getAppA();
        int currentVersionCode = BuildConfig.VERSION_CODE;
        int excludedVersionCode = PrefHelper.getInstance().getAdsData().getAppV();

        return appA != 0 && currentVersionCode != excludedVersionCode;
    }

    private static void completeAd() {
        if (adCompletionListener != null) {
            adCompletionListener.onAdCompleted();
            adCompletionListener = null;
        }
    }

}
