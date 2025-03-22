package com.casttotv.screenmirroring.chromecast.smart.tv.AndroidTv;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.DialogFragmentTestInputBinding;
import com.github.kunal52.remote.Remotemessage;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DialogTextInputAndroidTv extends BottomSheetDialogFragment {
    DialogFragmentTestInputBinding binding;
    private String mOldText = "";

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        binding = DialogFragmentTestInputBinding.inflate(layoutInflater, viewGroup, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View v, Bundle bundle) {
        super.onViewCreated(v, bundle);

        binding.ivClose.setOnClickListener(view -> {
            dismiss();
        });
        binding.ivClear.setOnClickListener(view -> {
            binding.textBox.setText("");
            sendBackspace();
        });

        setupTextBox();

    }

    @Override
    public void onStart() {
        super.onStart();
        showSoftKeyboard(binding.textBox);
    }

    private void showSoftKeyboard(final View view) {
        if (view.requestFocus()) {
            final InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            view.postDelayed(new Runnable() {

                public void run() {
                    view.requestFocus();
                    inputMethodManager.showSoftInput(view, 1);
                }
            }, 100);
        }
    }

    private void setupTextBox() {
        binding.textBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6 || keyEvent.getKeyCode() != 67 || keyEvent.getAction() != 0) {
                    return false;
                }
                sendBackspace();
                return true;
            }
        });

        binding.textBox.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 67 || keyEvent.getAction() != 0) {
                    return false;
                }
                sendBackspace();
                return true;
            }
        });
        binding.textBox.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String charSequence2 = charSequence.toString();
                int length = charSequence2.length() - mOldText.length();
                if (charSequence2.equals("")) {
                    int length2 = mOldText.length() - charSequence2.length();
                    for (int i4 = 0; i4 < length2; i4++) {
                        sendBackspace();
                    }
                    mOldText = charSequence2;
                } else if (length > 1) {
                    charSequence2.replace(mOldText, "");
                    mOldText = charSequence2;
                    sendStringLiteral(charSequence2);
                } else {
                    String str = null;
                    if (charSequence2.length() > 0) {
                        str = charSequence2.substring(charSequence2.length() - 1);
                    }
                    if (mOldText.length() > charSequence2.length()) {
                        str = "BACKSPACE";
                    }
                    mOldText = charSequence2;
                    if (str == null) {
                        return;
                    }
                    if (str.equals("BACKSPACE")) {
                        sendBackspace();
                        return;
                    }
                    if (str.equals(" ")) {
                        str = "%20";
                    }
                    sendStringLiteral(str);
                }
            }
        });
    }

    private void sendBackspace() {
        AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_CLEAR);
    }

    private void sendStringLiteral(String str) {
        try {
            AndroidTvManager.INSTANCE.sendKey(Remotemessage.RemoteKeyCode.KEYCODE_A);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("fatal", "sendStringLiteral: " + e.getMessage());
        }
    }
}
