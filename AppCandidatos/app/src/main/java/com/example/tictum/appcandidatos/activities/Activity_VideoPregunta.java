package com.example.tictum.appcandidatos.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Respuesta;
import com.example.tictum.appcandidatos.beans.Video;

import java.util.List;

public class Activity_VideoPregunta extends AppCompatActivity {

    private VideoView videoView;
    private TextView numeroPreguntaTextView;
    private Entrevista entrevista;
    private List<Video> listaVideos;
    private Respuesta respuesta;
    private Intent intent;
    private int numeroPreguntasVideo;
    private int numeroPreguntaVideo;
    private boolean isUltimaPregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.layout_video_pregunta);

        // recibo el objeto entrevista de la activity anterior y reproduzco el video transicion que tiene, el primero del List<Video>
        entrevista = (Entrevista) getIntent().getSerializableExtra("entrevista");
        numeroPreguntasVideo = (int) getIntent().getSerializableExtra("numeroPreguntasVideo");
        numeroPreguntaVideo = (int) getIntent().getSerializableExtra("numeroPreguntaVideo");
        listaVideos = entrevista.getListaVideos();
        listaVideos.remove(0);

        //se comprueba si es la última pregunta
        if(numeroPreguntaVideo == numeroPreguntasVideo){
            isUltimaPregunta = true;
        }
        else{
            isUltimaPregunta = false;
        }

        //recibo el bean respuesta
        respuesta = (Respuesta) getIntent().getSerializableExtra("respuesta");

        //Modificao el TextView del numero de pregunta
        numeroPreguntaTextView = (TextView) findViewById(R.id.numeroPregunta);
        numeroPreguntaTextView.setText("Pregunta" + String.valueOf(numeroPreguntaVideo) + "/" + String.valueOf(numeroPreguntasVideo));

        //Obtengo el video de pregunta que es el primero en la lista de videos ya que se ha borrado el video anterior
        Video videoPregunta = entrevista.getListaVideos().get(0);

        // donde vamos a reproducir el video
        videoView = (VideoView) findViewById(R.id.videoView_video);

        // ruta del archivo que vamos a reproducir que sera la ruta del servidor
        String path = videoPregunta.getLinkVideo();

        // convertimos la ruta a una uri para la reproduccion
        Uri pathUri = Uri.parse(path);

        MediaController mediaController = new MediaController(this);

        videoView.setMediaController(mediaController);

        // añadimos el path
        videoView.setVideoPath(path);

        // reproducimos el video
        videoView.start();

        intent = new Intent(Activity_VideoPregunta.this, Activity_GrabarRespuesta.class);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                intent.putExtra("entrevista", entrevista);
                intent.putExtra("respuesta", respuesta);
                intent.putExtra("numeroPreguntasVideo", numeroPreguntasVideo);
                intent.putExtra("numeroPreguntaVideo", numeroPreguntaVideo + 1);
                intent.putExtra("isUltimaPregunta", isUltimaPregunta);
                startActivity(intent);
                finish();
            }
        });



    }





    private void alertOptionsVideo() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_VideoPregunta.this);

        builder.setTitle("Video Pregunta");
        builder.setMessage("¿Repetir el video o realizar una video-respuesta?");


        builder.setPositiveButton("RIGHT", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {


                    }
                }

        );

        builder.setNeutralButton("LEFT", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                Intent intent=new Intent(Activity_VideoPregunta.this,Activity_GrabarRespuesta0.class);
            }


        });

    }
}
