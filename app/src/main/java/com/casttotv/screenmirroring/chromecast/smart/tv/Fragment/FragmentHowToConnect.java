//package com.casttotv.screenmirroring.chromecast.smart.tv.Fragment;
//
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
//import com.casttotv.screenmirroring.chromecast.smart.tv.databinding.FragmentHowToConnectBinding;
//
//public class FragmentHowToConnect extends BottomSheetDialogFragment {
//    FragmentHowToConnectBinding binding;
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
//        binding = FragmentHowToConnectBinding.inflate(getLayoutInflater());
//        return binding.getRoot();
//    }
//
//    @Override
//    public Dialog onCreateDialog(Bundle bundle) {
//        Dialog onCreateDialog = super.onCreateDialog(bundle);
//        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) onCreateDialog;
//        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface dialogInterface) {
//                setupBottomSheet(dialogInterface);
//            }
//        });
//        return bottomSheetDialog;
//    }
//
//    @Override
//    public void onViewCreated(View v, Bundle bundle) {
//        super.onViewCreated(v, bundle);
//        binding.ivClose.setOnClickListener(view -> {
//            Dialog dialog = getDialog();
//            if (dialog != null) {
//                dialog.dismiss();
//            }
//        });
//        binding.tvGotIt.setOnClickListener(view -> {
//            Dialog dialog = getDialog();
//            if (dialog != null) {
//                dialog.dismiss();
//            }
//        });
//    }
//
//    private void setupBottomSheet(DialogInterface dialogInterface) {
//        View findViewById = ((BottomSheetDialog) dialogInterface).findViewById(com.google.android.material.R.id.design_bottom_sheet);
//        if (findViewById != null) {
//            findViewById.setBackgroundColor(0);
//        }
//    }
//
//}
