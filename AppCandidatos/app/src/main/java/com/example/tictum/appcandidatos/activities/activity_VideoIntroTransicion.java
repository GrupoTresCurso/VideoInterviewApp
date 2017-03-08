package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.MediaController;
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
        // Despues de verse el video completo empezamos la actividad del formulario
        Intent intent = new Intent(activity_VideoIntroTransicion.this,Activity_Formulario.class);
        startActivity(intent);
    }

    private void setUpVideoView(){
        // Modificar para reproducir el video de la base de datos
        // Prepara la URI del vídeo que será reproducido.
        //videoView.setVideoURI("http://www.mysite.com/videos/myvideo.3gp");
       // String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.video_example;
        //Uri uri = Uri.parse(uriPath);

        // Se crean los controles multimedia.
        MediaController mediaController = new MediaController(this);

        // Inicializa la VideoView.
        videoView = (VideoView) findViewById(R.id.videoView);
        // Asigna los controles multimedia a la VideoView.
        videoView.setMediaController(mediaController);


        // intentamos reproducir el video, si hay un error al intentar acceder a el entonces error
        try {
            // Asigna la URI del vídeo que será reproducido a la vista.
            //videoView.setVideoURI(uri);
            // Se asigna el foco a la VideoView.
            videoView.requestFocus();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();

            // el video esta listo para ser reproducido
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
            /*
             * Se indica al reproductor multimedia que el vídeo
             * se reproducirá en un loop (on repeat).
             */
                    mediaPlayer.setLooping(true);

                    if (position == 0) {
                /*
                 * Si tenemos una posición en savedInstanceState,
                 * el vídeo debería comenzar desde aquí.
                 */
                        videoView.start();
                    } else {
                /*
                 * Si venimos de un Activity "resumed",
                 * la reproducción del vídeo será pausada.
                 */
                        videoView.pause();
                    }
                }
            });

        }
        }


    }
