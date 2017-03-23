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
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Pregunta;
import com.example.tictum.appcandidatos.beans.Respuesta;
import com.example.tictum.appcandidatos.beans.Video;

public class Activity_Video_Intro extends AppCompatActivity {

    private VideoView videoView;
    private Entrevista entrevista;
    private Formulario formulario;
    private Pregunta preguntaActual;
    private Intent intent;
    private Respuesta respuesta;
    private int idCandidato;
    private int numeroPreguntasFormulario1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.layout_video_intro_transicion);


        // recibo el objeto entrevista de la activity anterior y reproduzco el video intro que tiene
        entrevista = (Entrevista) getIntent().getSerializableExtra("entrevista");


        //Se crea un bean Respuesta que se irá enviando y rellenando a través de los siguientes activities
        respuesta = new Respuesta();
        respuesta.setIdEntrevista(entrevista.getIdEntrevista());
        idCandidato = 1; //ID inventado, hay mirar cómo gestionar el envío y recogida del id del candidato que está realizando la entrevista
        respuesta.setIdCandidato(idCandidato);

        if (entrevista.TieneVideoIntro()) {
            // obtenemos el primer video que es el de introduccion para todas las entrevistas si lo tiene
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

                    // Obtenemos la lista de formularios que tiene asociados la entrevista
                    formulario = entrevista.getFormularios().get(0);

                    //Miramos el numero de preguntas para mostrarlo en la interfaz
                    numeroPreguntasFormulario1 = formulario.getPreguntas().size();
                    // obtenemos la primera pregunta de la lista para mostrarla al usuario para que la conteste
                    preguntaActual = formulario.getPreguntas().get(0);

                        if (preguntaActual.getTipoPregunta().equals("text")){

                            intent = new Intent(Activity_Video_Intro.this, Activity_PreguntaText.class);

                        } else if (preguntaActual.getTipoPregunta().equals("textArea")){

                             intent = new Intent(Activity_Video_Intro.this, Activity_PreguntaTextArea.class);

                        } else if (preguntaActual.getTipoPregunta().equals("checkBox")){

                             intent = new Intent(Activity_Video_Intro.this, Activity_PreguntaCheckBox.class);

                        } else if (preguntaActual.getTipoPregunta().equals("select")){

                            intent = new Intent(Activity_Video_Intro.this, Activity_PreguntaSelect.class);

                        } else if (preguntaActual.getTipoPregunta().equals("radioButton")){

                            intent = new Intent(Activity_Video_Intro.this, Activity_PreguntaRadioButton.class);
                        }

                        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                intent.putExtra("formulario", formulario);
                                intent.putExtra("preguntaActual", preguntaActual);
                                intent.putExtra("entrevista", entrevista);
                                intent.putExtra("respuesta", respuesta);
                                intent.putExtra("numeroPreguntasFormulario", numeroPreguntasFormulario1);
                                intent.putExtra("numeroPregunta", 1);
                                startActivity(intent);
                                finish();
                            }
                        });

        }
    }
}
