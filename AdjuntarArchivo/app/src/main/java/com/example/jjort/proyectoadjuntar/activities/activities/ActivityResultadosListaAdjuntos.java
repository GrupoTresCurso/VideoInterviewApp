package com.example.jjort.proyectoadjuntar.activities.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jjort.proyectoadjuntar.R;
import com.example.jjort.proyectoadjuntar.activities.adapters.AdaptadorAdjuntos;
import com.example.jjort.proyectoadjuntar.activities.beans.Archivo;

public class ActivityResultadosListaAdjuntos extends AppCompatActivity {

    private Archivo adjunto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resultados_lista_adjuntos);


        adjunto = new Archivo(0, "", " ");

        Archivo[] listaAdjuntos = {adjunto};


        AdaptadorAdjuntos adapter = new AdaptadorAdjuntos(this, listaAdjuntos);
        // Donde vamos a mostrar los objetos de Adjuntos
        ListView lista = (ListView) findViewById(R.id.listaAdjuntos);
        // Adjuntamos el adaptador
        lista.setAdapter(adapter);





    }
}
