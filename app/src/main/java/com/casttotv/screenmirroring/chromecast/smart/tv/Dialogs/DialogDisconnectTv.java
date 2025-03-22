package com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.casttotv.screenmirroring.chromecast.smart.tv.Preferences.PreferenceApp;
import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.google.android.material.card.MaterialCardView;

public class DialogDisconnectTv extends Dialog {

    Context context;

    public DialogDisconnectTv(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_disconnect_tv);

        MaterialCardView disconnectCard = findViewById(R.id.disconnectCard);
        MaterialCardView cancelCard = findViewById(R.id.cancelCard);

        disconnectCard.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {

                if (PreferenceApp.isVibrate()) {
                    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    if (vibrator != null && vibrator.hasVibrator()) {
                        vibrator.vibrate(200);
                    }
                }

                if (TVConnectUtils.getInstance() != null && TVConnectUtils.getInstance().isConnected()) {
                    TVConnectUtils.getInstance().disconnect();
                }
                dismiss();
                Toast.makeText(context, "Disconnect successful", Toast.LENGTH_SHORT).show();
            }
        });

        cancelCard.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                dismiss();
            }
        });

        Window window = getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
    }
}
