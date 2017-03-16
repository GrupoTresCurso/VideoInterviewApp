package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.adapter.AdaptadorEntrevistas;
import com.example.tictum.appcandidatos.beans.Entrevista;

import java.util.ArrayList;


public class Activity_ListaEntrevistas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mostrar_entrevistas);
        // Creamos la lista de las entrevistas para hacerles un listener a cada una
        // esta lista se va a traer de la base de datos
        Entrevista[] listaEntrevista = {new Entrevista("Puesto 1"),new Entrevista("Puesto 2")};
        // el adaptador para ver la lista de las entrevistas
        AdaptadorEntrevistas adaptador = new AdaptadorEntrevistas(this,listaEntrevista);
        // Donde vamos a mostrar los objetos Entrevista
        ListView listaEntrevistas = (ListView) findViewById(R.id.listaEntrevistas);
        // Adjudicamos el adaptador
        listaEntrevistas.setAdapter(adaptador);


        // Un listener para cada objeto entrevista que se muestre en la lista y poder pasar a la sigueinte actividad
        listaEntrevistas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Cogemos la entrevista seleccionada para mandar su id y recuperar sus atributos
                    Entrevista entrevistaSelected = (Entrevista) parent.getItemAtPosition(position);
                    // Desde DONDE estamos hacia DONDE queremos ir
                    Intent intent = new Intent(Activity_ListaEntrevistas.this, activity_VideoIntroTransicion.class);
                    // Mandamos el objeto entrevista que ha seleccionado el usuario
                    //intent.putExtra("entrevista", entrevistaSelected);
                    // LLamamos a la actividad Siguiente
                    startActivity(intent);
                }
        });
    }
}
