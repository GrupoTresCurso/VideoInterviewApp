package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Candidato;
import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Pregunta;
import com.example.tictum.appcandidatos.beans.Video;

import java.util.ArrayList;
import java.util.List;

public class Activity_SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout__splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // recibimos lista de entrevista y la metemos en base de datos
                // falta JSON
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

                Entrevista entrevista1 = new Entrevista(1,"entrevista1","programador",true,listaformularios,listaVideos,formSatisfaccion,mensajePrueba,listaCandidatos);
                Entrevista[] listaEntrevistas = {entrevista1};
                // llamamos a la siguiente actividad despues de la imagen de inicio
                Intent intent = new Intent(Activity_SplashScreen.this,Activity_ListaEntrevistas.class);
                // pasamos la lista al siguiente intent
                intent.putExtra("listaEntrevistas",listaEntrevistas);
                // llamamos a la nueva actividad
                startActivity(intent);
                // terminamos esta actividad para que no puedan volver a ella
                finish();
            }
            // el tiempo que se va a mostrar el splash screen
        },3000);
    }
}
