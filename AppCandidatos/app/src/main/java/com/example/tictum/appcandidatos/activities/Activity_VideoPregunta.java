package com.example.tictum.appcandidatos.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.tictum.appcandidatos.R;

public class Activity_VideoPregunta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout__video_pregunta);


        alertOptionsVideo();

        // Muestra el Activity en modo Portrait.
        this.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
                Intent intent=new Intent(Activity_VideoPregunta.this,Activity_GrabarRespuesta.class);
            }


        });

    }
}
