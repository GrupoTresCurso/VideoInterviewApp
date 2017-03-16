package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.tictum.appcandidatos.R;

public class activity_VideoIntroTransicion extends AppCompatActivity{

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout__video_intro_transicion);

        // Muestra el Activity en modo Portrait.
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // donde vamos a reproducir el video
        videoView = (VideoView) findViewById(R.id.videoView);

        // ruta del archivo que vamos a reproducir
        String path = "android.resource://" + getPackageName() +"/" + R.raw.countdown;

        // convertimos la ruta a una uri para la reproduccion
        Uri pathUri = Uri.parse(path);

        // añadimos el path
        videoView.setVideoPath(path);

        // reproducimos el video
        videoView.start();

        // Despues de verse el video completo empezamos la actividad del formulario
       // Intent intent = new Intent(activity_VideoIntroTransicion.this,Activity_Formulario.class);
       // startActivity(intent);
    }
}
