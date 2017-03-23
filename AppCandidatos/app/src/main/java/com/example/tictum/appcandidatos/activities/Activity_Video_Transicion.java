package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Respuesta;
import com.example.tictum.appcandidatos.beans.Video;

import java.util.List;

public class Activity_Video_Transicion extends AppCompatActivity {

    private VideoView videoView;
    private Entrevista entrevista;
    private Respuesta respuesta;
    private List<Video> listaVideos;
    private Intent intent;
    private int numeroPreguntasVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.layout_video_intro_transicion);

        // recibo el objeto entrevista de la activity anterior y reproduzco el video transicion que tiene, el primero del List<Video>
        entrevista = (Entrevista) getIntent().getSerializableExtra("entrevista");
        listaVideos = entrevista.getListaVideos();
        listaVideos.remove(0);
        numeroPreguntasVideo = listaVideos.size() - 1;

        //recibo el bean respuesta
        respuesta = (Respuesta) getIntent().getSerializableExtra("respuesta");

        //Obtengo el video de transicion que es el primero en la lista de videos ya que se ha borrado el video introducción en caso de que lo hubiera
        Video videoTrans = listaVideos.get(0);

        // donde vamos a reproducir el video
        videoView = (VideoView) findViewById(R.id.videoView);

        // ruta del archivo que vamos a reproducir que sera la ruta del servidor
        String path = videoTrans.getLinkVideo();

        // convertimos la ruta a una uri para la reproduccion
        Uri pathUri = Uri.parse(path);

        MediaController mediaController = new MediaController(this);

        videoView.setMediaController(mediaController);

        // añadimos el path
        videoView.setVideoPath(path);

        // reproducimos el video
        videoView.start();

        intent = new Intent(Activity_Video_Transicion.this, Activity_VideoPregunta.class);


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                intent.putExtra("entrevista", entrevista);
                intent.putExtra("respuesta", respuesta);
                intent.putExtra("numeroPreguntasVideo", numeroPreguntasVideo);
                intent.putExtra("numeroPreguntaVideo", 1);
                intent.putExtra("numeroPreguntasViveo", numeroPreguntasVideo);
                intent.putExtra("numeroPregunta", 1);
                startActivity(intent);
                finish();
            }
        });
    }
}
