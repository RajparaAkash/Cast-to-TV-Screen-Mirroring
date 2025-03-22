package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.Welcome;
import com.casttotv.screenmirroring.chromecast.smart.tv.Preferences.PreferenceApp;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.Utils;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivityWelcomeBinding;

import java.util.ArrayList;
import java.util.List;

public class ActivityWelcome extends ActivityBase {

    List<Welcome> welcomeList = new ArrayList<>();
    AdapterWelcome adapterWelcome;

    private ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.fullScreenLightStatusBar(this);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AdsManager.displaySmallNativeAd(this, findViewById(R.id.llNativeSmall), findViewById(R.id.llAds));

        configureBackPressedBehavior();
        setupViewPager();
        buttonClickListener();
    }

    private void buttonClickListener() {
        binding.skipTxt.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                skipClickListener();
            }
        });

        binding.nextTxt.setOnClickListener(v -> {
            nextClickListener();
        });

        binding.getStartTxt.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View view) {
                skipClickListener();
            }
        });
    }

    private void setupViewPager() {

        welcomeList.add(new Welcome(R.drawable.intro_screen_img_01, getResources().getString(R.string.intro_title_1), getResources().getString(R.string.intro_des_1)));
        welcomeList.add(new Welcome(R.drawable.intro_screen_img_02, getResources().getString(R.string.intro_title_2), getResources().getString(R.string.intro_des_2)));
        welcomeList.add(new Welcome(R.drawable.intro_screen_img_03, getResources().getString(R.string.intro_title_3), getResources().getString(R.string.intro_des_3)));
        welcomeList.add(new Welcome(R.drawable.intro_screen_img_04, getResources().getString(R.string.intro_title_4), getResources().getString(R.string.intro_des_4)));

        adapterWelcome = new AdapterWelcome(this, welcomeList);
        binding.viewPager.setAdapter(adapterWelcome);
        binding.dotsIndicator.setViewPager(binding.viewPager);

        binding.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position < 3) {
                    binding.getStartTxt.setVisibility(View.GONE);
                    binding.nextSkipLayout.setVisibility(View.VISIBLE);
                } else {
                    binding.nextSkipLayout.setVisibility(View.GONE);
                    binding.getStartTxt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void nextClickListener() {
        int currentItem = binding.viewPager.getCurrentItem();
        if (currentItem < adapterWelcome.getCount() - 1) {
            binding.viewPager.setCurrentItem(currentItem + 1);
        } else {
            skipClickListener();
        }
    }

    private void skipClickListener() {
        PreferenceApp.set_FirstTime(false);
        AdsManager.displayTimeBasedInterstitialAd(this, () -> {
            startActivity(new Intent(ActivityWelcome.this, ActivityPermission.class));
            finish();
        });
    }

    public static class AdapterWelcome extends PagerAdapter {

        private final Context context;
        private final List<Welcome> welcomeList;

        public AdapterWelcome(Context context, List<Welcome> pages) {
            this.context = context;
            this.welcomeList = pages;
        }

        @Override
        public int getCount() {
            return welcomeList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.itemview_welcome, container, false);

            AppCompatImageView welcomeImg = view.findViewById(R.id.welcomeImg);
            TextView welcomeTitleTxt = view.findViewById(R.id.welcomeTitleTxt);
            TextView welcomeDescTxt = view.findViewById(R.id.welcomeDescTxt);

            Welcome page = welcomeList.get(position);
            /*welcomeImg.setImageResource(page.imageResId);*/
            Glide.with(context).load(page.imageResId).placeholder(R.drawable.placeholder_img).into(welcomeImg);
            welcomeTitleTxt.setText(page.titleTxt);
            welcomeDescTxt.setText(page.descriptionTxt);

            container.addView(view);

            return view;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    private void configureBackPressedBehavior() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(ActivityWelcome.this, ActivityAppRate.class));
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}