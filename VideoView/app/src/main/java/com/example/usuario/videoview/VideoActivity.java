package com.example.usuario.videoview;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = (VideoView) findViewById(R.id.videoView_video);

        String path = "android.resource://"+getPackageName()+"/"+R.raw.cris;

        Uri pathUri = Uri.parse(path);
        //Uri path = Uri.parse("https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4");

        MediaController mediaController = new MediaController(this);
        //videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        //videoView.setVideoURI(pathUri);
        videoView.setVideoPath(path);

        videoView.start();
    }
}
