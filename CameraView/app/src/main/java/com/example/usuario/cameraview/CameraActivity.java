package com.example.usuario.cameraview;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class CameraActivity extends AppCompatActivity {

    int permisoEscritura = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    int permisoCamara = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA);

    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    final static int REQUEST_VIDEO_CAPTURED = 1;
    Uri uriVideo = null;
    VideoView videoviewPlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);



        Button buttonRecording = (Button)findViewById(R.id.recording);
        Button buttonPlayback = (Button)findViewById(R.id.playback);



        buttonRecording.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent, REQUEST_VIDEO_CAPTURED);
            }});

        buttonPlayback.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(uriVideo == null){
                    Toast.makeText(CameraActivity.this,
                            "No Video",
                            Toast.LENGTH_LONG)
                            .show();
                }else{
                    Toast.makeText(CameraActivity.this,
                            "Playback: " + uriVideo.getPath(),
                            Toast.LENGTH_LONG)
                            .show();
                    videoviewPlay.setVideoURI(uriVideo);
                    videoviewPlay.start();
                }
            }});
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
            if (MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE == requestCode) {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }

            if (MY_PERMISSIONS_REQUEST_CAMERA == requestCode) {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
// TODO Auto-generated method stub
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_VIDEO_CAPTURED){
                uriVideo = data.getData();
                Toast.makeText(CameraActivity.this,
                        uriVideo.getPath(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        }else if(resultCode == RESULT_CANCELED){
            uriVideo = null;
            Toast.makeText(CameraActivity.this,
                    "Cancelled!",
                    Toast.LENGTH_LONG)
                    .show();
        }
    }
    }

