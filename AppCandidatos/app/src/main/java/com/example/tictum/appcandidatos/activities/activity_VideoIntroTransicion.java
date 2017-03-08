package com.example.tictum.appcandidatos.activities;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

import com.example.tictum.appcandidatos.R;

public class activity_VideoIntroTransicion extends AppCompatActivity{

        private VideoView videoView;
        private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout__video_intro_transicion);

        // Muestra el Activity en modo Portrait.
        this.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setUpVideoView();
    }

    private void setUpVideoView(){
        // Prepara la URI del vídeo que será reproducido.
       // String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.video_example;
        //Uri uri = Uri.parse(uriPath);
    }

    }
