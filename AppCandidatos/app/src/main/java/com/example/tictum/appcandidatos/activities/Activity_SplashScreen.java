package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tictum.appcandidatos.R;

public class Activity_SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout__splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // llamamos a la siguiente actividad despues de la imagen de inicio
                Intent intent = new Intent(Activity_SplashScreen.this,Activity_ListaEntrevistas.class);
                // llamamos a la nueva actividad
                startActivity(intent);
                // terminamos esta actividad para que no puedan volver a ella
                finish();
            }
            // el tiempo que se va a mostrar el splash screen
        },4000);
    }
}
