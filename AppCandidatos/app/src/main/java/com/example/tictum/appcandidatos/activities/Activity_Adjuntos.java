package com.example.tictum.appcandidatos.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.adapter.AdaptadorAdjuntos;

public class Activity_Adjuntos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout__adjuntos);

        //Inflar lista de adjuntos
       //Adjunto[] listaAdjuntos = {};
        //Adjunto adjunto1 = new Adjunto(0, "add cv", 0);
        //Adjunto adjunto2 = new Adjunto(0, "add cv2", 0);

       // AdaptadorAdjuntos adapter = new AdaptadorAdjuntos(this, listaAdjuntos);
        //ListView lista = (ListView) findViewById(R.id.listaAdjuntos);
        //lista.setAdapter(adapter);

    }
}
