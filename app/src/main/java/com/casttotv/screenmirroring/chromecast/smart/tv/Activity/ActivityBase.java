package com.casttotv.screenmirroring.chromecast.smart.tv.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.LocaleHelper;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.NetworkChangeReceiver;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.NetworkUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

public class ActivityBase extends AppCompatActivity implements NetworkChangeReceiver.NetworkChangeListener {

    private NetworkChangeReceiver networkReceiver;
    private Dialog noConnectionDialog;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        networkReceiver = new NetworkChangeReceiver(this);

        if (!NetworkUtils.isInternetAvailable(this)) {
            displayNoConnectionDialog(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (noConnectionDialog != null && noConnectionDialog.isShowing()) {
            noConnectionDialog.dismiss();
        }
    }

    @Override
    public void onNetworkStatusChanged(boolean isConnected) {
        if (isConnected) {
            if (noConnectionDialog != null && noConnectionDialog.isShowing()) {
                noConnectionDialog.dismiss();
            }
        } else {
            displayNoConnectionDialog(this);
        }
    }

    private void displayNoConnectionDialog(Activity activity) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;  // Avoid showing the dialog if the activity is not in a valid state
        }

        if (noConnectionDialog != null && noConnectionDialog.isShowing()) {
            return;
        }
        noConnectionDialog = new Dialog(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_no_connection, null);
        noConnectionDialog.setContentView(dialogView);
        noConnectionDialog.setCancelable(false);

        Window window = noConnectionDialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setStatusBarColor(Color.WHITE);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        MaterialCardView connectWifiCard = dialogView.findViewById(R.id.connectWifiCard);
        connectWifiCard.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                if (NetworkUtils.isInternetAvailable(ActivityBase.this)) {
                    noConnectionDialog.dismiss();
                } else {
                    Toast.makeText(ActivityBase.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        noConnectionDialog.show();
    }

    public void startReviewFlow() {
        try {
            ReviewManager reviewManager = ReviewManagerFactory.create(getApplicationContext());
            Task<ReviewInfo> request = reviewManager.requestReviewFlow();
            request.addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // We can get the ReviewInfo object
                    ReviewInfo reviewInfo = task.getResult();
                    launchReviewFlow(reviewManager, reviewInfo);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void launchReviewFlow(ReviewManager reviewManager, ReviewInfo reviewInfo) {
        try {
            Task<Void> reviewFlow = reviewManager.launchReviewFlow(this, reviewInfo);
            reviewFlow.addOnCompleteListener(task -> {
                // The flow has finished. The API does not indicate whether the user
                // reviewed or not, or even whether the review dialog was shown. Thus, no
                // matter the result, we continue our app flow.
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LocaleHelper.onAttach(context));
    }
}
