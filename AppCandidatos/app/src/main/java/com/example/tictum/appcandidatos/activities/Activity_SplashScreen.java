package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.tictum.appcandidatos.R;

public class Activity_SplashScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_splash_screen);

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
        },3000);
    }
}
