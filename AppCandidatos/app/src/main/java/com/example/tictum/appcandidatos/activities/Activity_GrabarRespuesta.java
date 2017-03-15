package com.example.tictum.appcandidatos.activities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.previews.CameraPreview;

public class Activity_GrabarRespuesta extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout__grabar_respuesta);

        // Create an instance of Camera
        mCamera = getFrontCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);


    }

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /** Check if this device has a front camera */
    private boolean checkFrontCameraHardware(Camera camera) {
        if (camera.getNumberOfCameras() > 1){
            // this device has more than two cameras
            return true;
        } else {
            // this device has less than two cameras
            return false;
        }
    }

    /** A safe way to get an instance of the Camera object.  */
    //Abre la camara normal
    public static Camera getCameraInstance(){
        Camera camera = null;
        try {
            camera = Camera.open(1); // attempt to get a Camera instance
        }
        catch (Exception e){
            Log.d("Excepcion", "getCameraInstance", e);
        }
        return camera; // returns null if camera is unavailable
    }

    //Abre la camara
    private Camera getFrontCameraInstance() {
        int cameraCount = 0;
        Camera camera = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                try {
                    camera = Camera.open(camIdx);
                } catch (RuntimeException e) {
                    Log.e("Excepcion", "Camara frontal falla al abrirse: " + e.getLocalizedMessage());
                }
            }
        }

        return camera;
    }
}
