package com.connectsdk.service.webos.lgcast.remotecamera.capture;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.RggbChannelVector;
import android.view.Surface;

import com.connectsdk.service.webos.lgcast.remotecamera.service.CameraUtility;


public class CameraCaptureBulder {
    private CameraCharacteristics mCameraCharacteristics;
    private CaptureRequest.Builder mCameraRequestBuilder;

    public CameraCaptureBulder(Context context, CameraDevice cameraDevice, String cameraId) throws Exception {
        this.mCameraCharacteristics = CameraUtility.getCameraCharacteristics(context, cameraId);
        CaptureRequest.Builder createCaptureRequest = cameraDevice != null ? cameraDevice.createCaptureRequest(1) : null;
        this.mCameraRequestBuilder = createCaptureRequest;
        if (this.mCameraCharacteristics == null || createCaptureRequest == null) {
            throw new Exception("Invalid arguments");
        }
        createCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, 4);
        this.mCameraRequestBuilder.set(CaptureRequest.CONTROL_MODE, 1);
    }

    public void addTarget(Surface outputTarget) {
        if (outputTarget != null) {
            this.mCameraRequestBuilder.addTarget(outputTarget);
        }
    }

    public boolean setBrightness(int value) {
        int calculateBrightness = CameraUtility.calculateBrightness(this.mCameraCharacteristics, value);
        if (calculateBrightness == -1) {
            return false;
        }
        this.mCameraRequestBuilder.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(calculateBrightness));
        return true;
    }

    public boolean setWhiteBalance(int value) {
        RggbChannelVector calculateWhiteBalance = CameraUtility.calculateWhiteBalance(value);
        if (calculateWhiteBalance == null) {
            return false;
        }
        this.mCameraRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 0);
        this.mCameraRequestBuilder.set(CaptureRequest.COLOR_CORRECTION_MODE, 0);
        this.mCameraRequestBuilder.set(CaptureRequest.COLOR_CORRECTION_GAINS, calculateWhiteBalance);
        return true;
    }

    public boolean setAutoWhiteBalance(boolean auto) {
        this.mCameraRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(auto ? 1 : 0));
        return true;
    }

    public CaptureRequest build() {
        return this.mCameraRequestBuilder.build();
    }
}
