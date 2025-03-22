package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.casttotv.screenmirroring.chromecast.smart.tv.Ads.AdsManager;
import com.casttotv.screenmirroring.chromecast.smart.tv.Model.Language;
import com.casttotv.screenmirroring.chromecast.smart.tv.Preferences.PreferenceApp;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.LocaleHelper;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.ActivityLanguageBinding;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Locale;

public class ActivityLanguage extends ActivityBase {

    private String selectedLanguageCode = "en";
    private ActivityLanguageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AdsManager.displaySmallNativeAd(this, findViewById(R.id.llNativeSmall), findViewById(R.id.llAds));

        configureBackPressedBehavior();
        setupRecyclerView();
        buttonClickListener();
    }

    private void setupRecyclerView() {
        String savedLanguageCode = PreferenceApp.get_LanguageCode();
        ArrayList<Language> languageList = getLanguageList(savedLanguageCode);

        binding.languageRv.setLayoutManager(new GridLayoutManager(this, 2));
        AdapterLanguage adapter = new AdapterLanguage(this, languageList, selectedLanguage -> {
            selectedLanguageCode = selectedLanguage.languageCode;
        });
        binding.languageRv.setAdapter(adapter);
    }

    private void buttonClickListener() {
        binding.languageSaveTxt.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                saveSelectedLanguage();
                navigateToNextScreen();
            }
        });
    }

    private void saveSelectedLanguage() {
        PreferenceApp.set_LanguageCode(selectedLanguageCode);
        LocaleHelper.setLocale(this, PreferenceApp.get_LanguageCode());
    }

    private void navigateToNextScreen() {
        Class<?> targetActivity = PreferenceApp.isFirstTime() ? ActivityWelcome.class : ActivityDashboard.class;
        Intent intent = new Intent(ActivityLanguage.this, targetActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        AdsManager.displayTimeBasedInterstitialAd(ActivityLanguage.this, () -> {
            startActivity(intent);
        });
    }

    private ArrayList<Language> getLanguageList(String savedLanguageCode) {
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(new Language("en", R.drawable.language_english_img, "English", savedLanguageCode.equals("en")));
        languages.add(new Language("zh", R.drawable.language_chinese_img, "Chinese", savedLanguageCode.equals("zh")));
        languages.add(new Language("hi", R.drawable.language_hindi_img, "Hindi", savedLanguageCode.equals("hi")));
        languages.add(new Language("es", R.drawable.language_spanish_img, "Spanish", savedLanguageCode.equals("es")));
        languages.add(new Language("ar", R.drawable.language_arabic_img, "Arabic", savedLanguageCode.equals("ar")));
        languages.add(new Language("fr", R.drawable.language_french_img, "French", savedLanguageCode.equals("fr")));
        languages.add(new Language("bn", R.drawable.language_bengali_img, "Bengali", savedLanguageCode.equals("bn")));
        languages.add(new Language("ru", R.drawable.language_russian_img, "Russian", savedLanguageCode.equals("ru")));
        languages.add(new Language("nl", R.drawable.language_dutch_img, "Dutch", savedLanguageCode.equals("nl")));
        languages.add(new Language("pt", R.drawable.language_portuguese_img, "Portuguese", savedLanguageCode.equals("pt")));
        languages.add(new Language("sw", R.drawable.language_swahili_img, "Swahili", savedLanguageCode.equals("sw")));
        languages.add(new Language("in", R.drawable.language_indonesian_img, "Indonesian", savedLanguageCode.equals("in")));
        languages.add(new Language("ur", R.drawable.language_urdu_img, "Urdu", savedLanguageCode.equals("ur")));
        languages.add(new Language("ja", R.drawable.language_japanese_img, "Japanese", savedLanguageCode.equals("ja")));
        languages.add(new Language("de", R.drawable.language_german_img, "German", savedLanguageCode.equals("de")));
        languages.add(new Language("pa", R.drawable.language_hindi_img, "Punjabi", savedLanguageCode.equals("pa")));
        languages.add(new Language("fa", R.drawable.language_persian_img, "Persian", savedLanguageCode.equals("fa")));
        languages.add(new Language("jv", R.drawable.language_indonesian_img, "Javanese", savedLanguageCode.equals("jv")));
        languages.add(new Language("vi", R.drawable.language_vietnamese_img, "Vietnamese", savedLanguageCode.equals("vi")));
        languages.add(new Language("it", R.drawable.language_italian_img, "Italian", savedLanguageCode.equals("it")));
        languages.add(new Language("tr", R.drawable.language_turkish_img, "Turkish", savedLanguageCode.equals("tr")));
        languages.add(new Language("mr", R.drawable.language_hindi_img, "Marathi", savedLanguageCode.equals("mr")));
        languages.add(new Language("te", R.drawable.language_hindi_img, "Telugu", savedLanguageCode.equals("te")));
        languages.add(new Language("ta", R.drawable.language_hindi_img, "Tamil", savedLanguageCode.equals("ta")));
        languages.add(new Language("sv", R.drawable.language_swedish_img, "Swedish", savedLanguageCode.equals("sv")));
        languages.add(new Language("ko", R.drawable.language_korean_img, "Korean", savedLanguageCode.equals("ko")));
        languages.add(new Language("ha", R.drawable.language_hausa_img, "Hausa", savedLanguageCode.equals("ha")));
        languages.add(new Language("th", R.drawable.language_thai_img, "Thai", savedLanguageCode.equals("th")));
        languages.add(new Language("gu", R.drawable.language_hindi_img, "Gujarati", savedLanguageCode.equals("gu")));
        languages.add(new Language("pl", R.drawable.language_polish_img, "Polish", savedLanguageCode.equals("pl")));

        return languages;
    }

    public static class AdapterLanguage extends RecyclerView.Adapter<AdapterLanguage.ViewHolder> {

        private final Context context;
        private final ArrayList<Language> languageList;
        private final LanguageListener languageListener;
        private String langCode;

        public AdapterLanguage(Context context, ArrayList<Language> languageList, LanguageListener languageListener) {
            this.context = context;
            this.languageList = languageList;
            this.languageListener = languageListener;
            this.langCode = PreferenceApp.get_LanguageCode();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_language, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Language language = languageList.get(position);
            boolean isSelected = langCode.equals(language.languageCode);

            // Set color and drawable based on selection
            holder.languageCv.setStrokeColor(ColorStateList.valueOf(ContextCompat.getColor(context,
                    isSelected ? R.color.colorPrimary : R.color.langStrokeColor)));
            holder.languageSelectImg.setImageDrawable(ContextCompat.getDrawable(context,
                    isSelected ? R.drawable.lang_selected_img : R.drawable.lang_unselected_img));

            // Set flag icon and language text
            holder.languageFlagImg.setImageResource(language.languageIcon);
            holder.languageNameTxt.setText(language.languageName);

            // Set localized display language
            Locale locale = new Locale(language.languageCode);
            holder.languageTitleTxt.setText(String.format("(%s)", locale.getDisplayLanguage(locale)));

            holder.languageCv.setOnClickListener(view -> {
                langCode = language.languageCode;
                notifyDataSetChanged();
                languageListener.onClick(language);
            });
        }

        @Override
        public int getItemCount() {
            return languageList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            private final MaterialCardView languageCv;
            private final AppCompatImageView languageFlagImg;
            private final AppCompatImageView languageSelectImg;
            private final TextView languageNameTxt;
            private final TextView languageTitleTxt;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                languageCv = itemView.findViewById(R.id.languageCv);
                languageFlagImg = itemView.findViewById(R.id.languageFlagImg);
                languageSelectImg = itemView.findViewById(R.id.languageSelectImg);
                languageNameTxt = itemView.findViewById(R.id.languageNameTxt);
                languageTitleTxt = itemView.findViewById(R.id.languageTitleTxt);
            }
        }
    }

    public interface LanguageListener {
        void onClick(Language modelLanguage);
    }

    private void configureBackPressedBehavior() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (PreferenceApp.isFirstTime()) {
                    startActivity(new Intent(ActivityLanguage.this, ActivityAppRate.class));
                } else {
                    finish();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        binding.backLay.setOnClickListener(view -> {
            callback.handleOnBackPressed();
        });
    }
}