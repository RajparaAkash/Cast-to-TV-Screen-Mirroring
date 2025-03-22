package com.casttotv.screenmirroring.chromecast.smart.tv.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.casttotv.screenmirroring.chromecast.smart.tv.R;
import com.casttotv.screenmirroring.chromecast.smart.tv.Util.FileUtils;

public class DialogFileInfo extends Dialog {

    Activity activity;
    String filePath;

    public DialogFileInfo(Activity activity, String filePath) {
        super(activity);
        this.activity = activity;
        this.filePath = filePath;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_file_info);

        TextView fileNameTxt = findViewById(R.id.fileNameTxt);
        TextView fileSizeTxt = findViewById(R.id.fileSizeTxt);
        TextView fileResolutionTxt = findViewById(R.id.fileResolutionTxt);
        TextView filePathTxt = findViewById(R.id.filePathTxt);
        LinearLayout fileResolutionLay = findViewById(R.id.fileResolutionLay);

        if (filePath != null && !filePath.isEmpty()) {
            fileNameTxt.setText(FileUtils.getFileNameFromPath(filePath));
            fileSizeTxt.setText(FileUtils.getFileSize(filePath));
            filePathTxt.setText(filePath);

            if (FileUtils.isImage(filePath)) {
                fileResolutionLay.setVisibility(View.VISIBLE);
                fileResolutionTxt.setText(FileUtils.getFileResolution(filePath));
            }

            if (FileUtils.isVideo(filePath)) {
                fileResolutionLay.setVisibility(View.VISIBLE);
                fileResolutionTxt.setText(FileUtils.getVideoResolution(filePath));
            }
        }

        Window window = getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
    }
}
