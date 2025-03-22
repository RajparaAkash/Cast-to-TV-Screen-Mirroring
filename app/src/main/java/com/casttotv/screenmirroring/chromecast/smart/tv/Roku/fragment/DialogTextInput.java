package com.casttotv.screenmirroring.chromecast.smart.tv.Roku.fragment;

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
import android.widget.EditText;
import android.widget.TextView;

import com.casttotv.screenmirroring.chromecast.smart.tv.Controllers.TVConnectUtils;
import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.DialogFragmentTestInputBinding;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.tasks.RequestCallback;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.tasks.RequestTask;
import com.casttotv.screenmirroring.chromecast.smart.tv.Roku.utils.RokuRequestTypes;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jaku.core.JakuRequest;
import com.jaku.core.KeypressKeyValues;
import com.jaku.request.KeypressRequest;

import java.util.ArrayDeque;

public class DialogTextInput extends BottomSheetDialogFragment {
    DialogFragmentTestInputBinding binding;
    private String mOldText = "";
    private EditText mTextBox;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(true);
    }


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
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
//            if (binding.textBox.getText().toString().length() >= 1) {
//                binding.textBox.setText(removeLastChar(binding.textBox.getText().toString()));
//            }
            binding.textBox.setText("");
        });

        setupTextBox();

    }


//    @Override
//    public Dialog onCreateDialog(Bundle bundle) {
//        View inflate = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_test_input, (ViewGroup) null);
//
//        inflate.findViewById(R.id.ivClose).setOnClickListener(view -> {
//            dismiss();
//        });
//        inflate.findViewById(R.id.ivClear).setOnClickListener(view -> {
////            if (mTextBox.getText().toString().length() >= 1) {
////                mTextBox.setText(removeLastChar(mTextBox.getText().toString()));
////            }
//            mTextBox.setText("");
//        });
//
//        setupTextBox();
//        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
//        builder.setView(inflate);
//        AlertDialog create = builder.create();
//        create.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        return create;
//    }

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
                        DialogTextInput.this.sendBackspace();
                    }
                    DialogTextInput.this.mOldText = charSequence2;
                } else if (length > 1) {
                    charSequence2.replace(mOldText, "");
                    DialogTextInput.this.mOldText = charSequence2;
                    sendStringLiteral(charSequence2);
                } else {
                    String str = null;
                    if (charSequence2.length() > 0) {
                        str = charSequence2.substring(charSequence2.length() - 1);
                    }
                    if (mOldText.length() > charSequence2.length()) {
                        str = "BACKSPACE";
                    }
                    DialogTextInput.this.mOldText = charSequence2;
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
        new RequestTask(new JakuRequest(new KeypressRequest("http://" + TVConnectUtils.getInstance().getConnectableDevice().getIpAddress() + ":8060", KeypressKeyValues.BACKSPACE.getValue()), null), new RequestCallback() {

            @Override
            public void onErrorResponse(RequestTask.Result result) {
            }

            @Override
            public void requestResult(RokuRequestTypes rokuRequestTypes, RequestTask.Result result) {
            }
        }).execute(RokuRequestTypes.keypress);
    }

    private void sendStringLiteral(String str) {
        new RequestTask(new JakuRequest(new KeypressRequest("http://" + TVConnectUtils.getInstance().getConnectableDevice().getIpAddress() + ":8060", KeypressKeyValues.LIT_.getValue() + str), null), new RequestCallback() {

            @Override
            public void requestResult(RokuRequestTypes rokuRequestTypes, RequestTask.Result result) {
                Log.d("sasas", "OK " + result.mResultValue);
            }

            @Override
            public void onErrorResponse(RequestTask.Result result) {
                Log.d("sasas", "error");
            }
        }).execute(RokuRequestTypes.keypress);
        new ArrayDeque();
    }
}
