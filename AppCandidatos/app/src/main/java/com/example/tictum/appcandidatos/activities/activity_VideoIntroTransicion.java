package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Video;

public class activity_VideoIntroTransicion extends AppCompatActivity{

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.layout__video_intro_transicion);

        Entrevista entrevista = (Entrevista)getIntent().getSerializableExtra("entrevista");

        if (entrevista.TieneVideoIntro()) {
            // obtenemos el primer video que es el de transicion para todas las entrevistas si lo tiene
            Video videoIntro = entrevista.getListaVideos().get(0);

            // donde vamos a reproducir el video
            videoView = (VideoView) findViewById(R.id.videoView);

            // ruta del archivo que vamos a reproducir que sera la ruta del servidor
            String path = videoIntro.getLinkVideo();

            // convertimos la ruta a una uri para la reproduccion
            Uri pathUri = Uri.parse(path);

            MediaController mediaController = new MediaController(this);

            videoView.setMediaController(mediaController);

            // añadimos el path
            videoView.setVideoPath(path);

            // reproducimos el video
            videoView.start();

            // si posicion actual del video (tpo en seg) coincide con la duracion final del video llamamos a la actividad
            if (videoView.getCurrentPosition() == videoView.getDuration()) {
                // Despues de verse el video completo empezamos la actividad del formulario
                Intent intent = new Intent(activity_VideoIntroTransicion.this, Activity_PreguntaText.class);
                startActivity(intent);
            }
        } else {
            // si no tiene video intro entonces vamos al formulario con las preguntas
            Intent intent = new Intent(activity_VideoIntroTransicion.this, Activity_PreguntaText.class);
            startActivity(intent);
        }
    }
}
