package com.bryan.martinez.grabarvideo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Activity_GrabarVideo extends AppCompatActivity {

    private final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private final int REQUEST_CAMERA = 1;
    private final int CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_grabar_video);

    }

    public void examplePermission(View view) {

        checkPermissionWrite();
    }

    private void checkPermissionCamara(){

        int permisoCamara = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA);

    // permiso para acceder a la camara
        if (permisoCamara != PackageManager.PERMISSION_GRANTED) {
            // request permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            }
        }else {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(intent, CAMERA);
        }

    }

    private void checkPermissionWrite(){

        int permisoEscritura = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permisoEscritura != PackageManager.PERMISSION_GRANTED) {
            // request permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_WRITE_EXTERNAL_STORAGE);
            }

        }else {
            checkPermissionCamara();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(REQUEST_WRITE_EXTERNAL_STORAGE == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // OK Do something with..
                checkPermissionCamara();
            } else {
                // The user does not grant permissions
                finish();
            }
        }
        if (REQUEST_CAMERA == requestCode){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // OK Do something with..
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent, CAMERA);
            } else {
                // The user does not grant permissions
                finish();
            }
        }
        else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
