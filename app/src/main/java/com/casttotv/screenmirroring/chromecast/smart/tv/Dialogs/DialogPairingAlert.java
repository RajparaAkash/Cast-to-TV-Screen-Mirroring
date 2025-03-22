package com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.google.android.material.card.MaterialCardView;

public class DialogPairingAlert extends Dialog {

    private final PairingListener pairingListener;

    public interface PairingListener {
        void onClick();
    }

    public DialogPairingAlert(Context context, PairingListener pairingListener) {
        super(context);
        this.pairingListener = pairingListener;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_pairing_alert);

        MaterialCardView okCardView = findViewById(R.id.okCardView);
        MaterialCardView cancelCardView = findViewById(R.id.cancelCardView);

        okCardView.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                if (pairingListener != null) {
                    pairingListener.onClick();
                }
                DialogPairingAlert.this.dismiss();
            }
        });

        cancelCardView.setOnClickListener(new ThrottleClickListener() {
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
