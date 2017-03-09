package com.example.tictum.appcandidatos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.adapter.AdaptadorAdjuntos;
import com.example.tictum.appcandidatos.beans.Adjunto;

import java.util.ArrayList;
import java.util.List;

public class Activity_Adjuntos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout__adjuntos);

        //Inflar lista de adjuntos
       Adjunto[] listaAdjuntos = {};
        Adjunto adjunto1 = new Adjunto(0, "add cv", 0);
        Adjunto adjunto2 = new Adjunto(0, "add cv2", 0);

        AdaptadorAdjuntos adapter = new AdaptadorAdjuntos(this, listaAdjuntos);
        ListView lista = (ListView) findViewById(R.id.listaAdjuntos);
        lista.setAdapter(adapter);

    }
}
