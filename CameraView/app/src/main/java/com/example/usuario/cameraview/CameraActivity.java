package com.example.usuario.cameraview;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

public class CameraActivity extends AppCompatActivity {

    private CameraPreview mPreview;
    private boolean recording = false;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        //Solicitar permisos
        getPermissions();

        mPreview = (CameraPreview) findViewById(R.id.camera_preview);
        mPreview.setOnTouchListener(new View.OnTouchListener() {

            @Override

            public boolean onTouch(View view, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (recording) {
                        Log.i("YO", "Saliendo.");
                        mPreview.getRecorder().stop();
                        mPreview.getRecorder().release();
                        finish();
                    } else {

                        recording = true;
                        Log.i("YO", "Grabando.");
                        mPreview.getRecorder().start();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    //Permisos par la camara
    public void getPermissions(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }
        else{
           // mCamera = getCameraInstance();

            // Configurar orientación de la camara
            //setCameraDisplayOrientation(this, 1, mCamera);


            // Create our Preview view and set it as the content of our activity.
            //mPreview = new CameraPreview(this, mCamera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(mPreview);
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                   // mCamera = getCameraInstance();

                    // Configurar orientación de la camara
                  //  setCameraDisplayOrientation(this, 1, mCamera);


                    // Create our Preview view and set it as the content of our activity.
                   // mPreview = new CameraPreview(this, mCamera);
                    FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
                    preview.addView(mPreview);



                } else {

                    // permission denied,
                }
            }

            return;
        }

        // other 'case' lines to check for other
        // permissions this app might request
    }
}
