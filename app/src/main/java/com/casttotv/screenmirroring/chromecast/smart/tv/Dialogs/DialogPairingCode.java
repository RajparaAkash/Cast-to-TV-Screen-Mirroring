package com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.ThrottleClickListener;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.NumericKeyBoardTransformationMethod;
import com.google.android.material.card.MaterialCardView;

public class DialogPairingCode extends Dialog {

    Activity activity;
    private final DialogListener dialogListener;

    public DialogPairingCode(Activity activity, DialogListener dialogListener) {
        super(activity);
        this.activity = activity;
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_pin_code);

        MaterialCardView verifyCardView = findViewById(R.id.verifyCardView);
        MaterialCardView cancelCardView = findViewById(R.id.cancelCardView);
        EditText codeEditText = findViewById(R.id.codeEditText);

        verifyCardView.setEnabled(false);
        verifyCardView.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                dialogListener.onOk(codeEditText.getText().toString().trim());
                dismiss();
            }
        });

        cancelCardView.setOnClickListener(new ThrottleClickListener() {
            @Override
            public void onThrottleClick(View v) {
                dismiss();
                dialogListener.onCancel();
            }
        });

        codeEditText.setInputType(18);
        codeEditText.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        codeEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (codeEditText.getText().toString().isEmpty()) {
                    verifyCardView.setEnabled(false);
                } else {
                    verifyCardView.setEnabled(true);
                }
            }
        });
        showKeyboard(codeEditText);

        Window window = getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    public interface DialogListener {
        void onCancel();

        void onOk(String str);
    }

    public static void showKeyboard(final EditText editText) {
        editText.post(new Runnable() {
            public void run() {
                editText.requestFocus();
                ((InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(editText, 1);
            }
        });
    }
}
