package com.casttotv.screenmirroring.chromecast.smart.tv.FireTv.fragment;

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

import com.casttotv.screenmirroring.chromecast.smart.tv.Api.Description;
import com.casttotv.screenmirroring.chromecast.smart.tv.Api.Text;
import com.casttotv.screenmirroring.chromecast.smart.tv.FireTv.utils.FireTVController;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.DialogFragmentTestInputBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogTextInputFireTv extends BottomSheetDialogFragment {
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
    public void onViewCreated(@NonNull View v, Bundle bundle) {
        super.onViewCreated(v, bundle);

        binding.ivClose.setOnClickListener(view -> {
            dismiss();
        });
        binding.ivClear.setOnClickListener(view -> {
            binding.textBox.setText("");
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
        if (FireTVController.INSTANCE.getApi() != null) {
            FireTVController.INSTANCE.getApi().remote(FireTVController.apiKeyFireTV, FireTVController.INSTANCE.getToken(requireContext()), "backspace").enqueue(new Callback<Description>() {

                @Override
                public void onFailure(@NonNull Call<Description> call, @NonNull Throwable th) {
                    Log.e("fatal", "TextInputDialogFireTv sendBackspace onFailure: " + th.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call<Description> call, @NonNull Response<Description> response) {
                }
            });
        }
    }

    private void sendStringLiteral(String str) {
        if (FireTVController.INSTANCE.getApi() != null) {
            FireTVController.INSTANCE.getApi().text(FireTVController.apiKeyFireTV, FireTVController.INSTANCE.getToken(requireContext()), new Text(str)).enqueue(new Callback<Description>() {

                @Override
                public void onFailure(@NonNull Call<Description> call, @NonNull Throwable th) {
                    Log.e("fatal", "TextInputDialogFireTv sendStringLiteral onFailure: " + th.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call<Description> call, @NonNull Response<Description> response) {
                }
            });
        }
    }
}
