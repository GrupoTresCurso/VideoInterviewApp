package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.adapter.AdaptadorEntrevistas;
import com.example.tictum.appcandidatos.beans.Candidato;
import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Pregunta;
import com.example.tictum.appcandidatos.beans.Video;

import java.util.ArrayList;
import java.util.List;

public class Activity_ListaEntrevistas extends AppCompatActivity {

    Entrevista entrevista1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mostrar_entrevistas);
        // Creamos la lista de las entrevistas para hacerles un listener a cada una
        // esta lista se va a traer de la base de datos
        // el adaptador para ver la lista de las entrevistas

        String preguntaPrueba1 = "¿Cuantos años tienes?";
        String preguntaPrueba2 = "¿Cuántos años de experiencia en java tienes?";
        String preguntaPrueba3 = "Marca los lenguajes con los que has trabajado";

        String[] opcionesPrueba1 = {};
        String[] opcionesPrueba2 = {"Menos de 2","Entre 2 y 4","Más de 4"};
        String[] opcionesPrueba3 = {"java","C","phyton"};

        Pregunta pregunta1 = new Pregunta(1,preguntaPrueba1,"text",opcionesPrueba1,1);
        Pregunta pregunta2 = new Pregunta(2,preguntaPrueba2,"radioButton",opcionesPrueba2,2);
        Pregunta pregunta3 = new Pregunta(3,preguntaPrueba3,"checkBox",opcionesPrueba3,3);

        List<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
        listaPreguntas.add(pregunta1);
        listaPreguntas.add(pregunta2);
        listaPreguntas.add(pregunta3);

        Formulario form1 = new Formulario(1,"form1",listaPreguntas,1);
        Formulario form2 = new Formulario(2,"form2",listaPreguntas,2);

        List<Formulario> listaformularios = new ArrayList<Formulario>();
        listaformularios.add(form1);
        listaformularios.add(form2);

        Video videoPrueba = new Video(1,"video1","android.resource://"+getPackageName()+"/"+R.raw.prueba_vertical,1,"transicion");
        List<Video> listaVideos = new ArrayList<Video>();
        listaVideos.add(videoPrueba);
        Formulario formSatisfaccion = new Formulario(3,"formSatisfaccion",listaPreguntas,3);
        String mensajePrueba = "Muchas gracias, hasta luego";
        List<Candidato> listaCandidatos = new ArrayList<Candidato>();
        Candidato candidato1 = new Candidato(1,"pepe","lopez","00000","email@email.com",24,"hombre","123456789","ruta/del/CV");
        listaCandidatos.add(candidato1);

        entrevista1 = new Entrevista(1,"entrevista1","Programador",true,listaformularios,listaVideos,formSatisfaccion,mensajePrueba,listaCandidatos);
        Entrevista[] listaEntrevista = {entrevista1};

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
                    Intent intent = new Intent(Activity_ListaEntrevistas.this, Activity_Video_Intro_Transicion.class);
                    // Mandamos el objeto entrevista que ha seleccionado el usuario
                    intent.putExtra("entrevista", entrevistaSelected);
                    // LLamamos a la actividad Siguiente
                    startActivity(intent);
                }
        });
    }
}
