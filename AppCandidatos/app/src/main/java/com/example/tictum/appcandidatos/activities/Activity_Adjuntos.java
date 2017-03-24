package com.example.tictum.appcandidatos.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.adapter.AdaptadorAdjuntos;
import com.example.tictum.appcandidatos.beans.Archivo;
import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Pregunta;
import com.example.tictum.appcandidatos.beans.Respuesta;

import java.util.List;


public class Activity_Adjuntos extends AppCompatActivity {

    private Entrevista entrevista;
    private Respuesta respuesta;
    private Formulario formularioAdjunto;
    private List<Pregunta> listaPreguntasAdjunto;
    private int ordenAdjunto = -1;




    private static final int READ_REQUEST_CODE = 42;
    private Intent intent;

    private Button btnAdjuntarArchivo;
    private Button btnTerminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_adjuntos);

        //Se recoge del intent el bean Respuesta y el bean Entrevista
        entrevista = (Entrevista) getIntent().getSerializableExtra("entrevista");
        respuesta = (Respuesta) getIntent().getSerializableExtra("respuesta");

        //Se coge del bean Entrevista el formulario donde están los adjuntos (ultimo formulariode la lista de formularios) y se coge sus preguntas
        formularioAdjunto = entrevista.getFormularios().get(1);
        listaPreguntasAdjunto = formularioAdjunto.getPreguntas();

        //Se crea el adaptadorAdjuntos con la lista de preguntas
        AdaptadorAdjuntos adaptadorAdjuntos = new AdaptadorAdjuntos(this, listaPreguntasAdjunto);

        //Se recoge la vista que va a ser inflada con el adaptador y se le asigna el adaptador
        ListView listaAdjuntos = (ListView) findViewById(R.id.listaAdjuntos);
        listaAdjuntos.setAdapter(adaptadorAdjuntos);

        //se le añade un listener al item adjunto que se crea en el layout
        listaAdjuntos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(ordenAdjunto == -1){
                    ordenAdjunto = 0;
                }
                else{
                    ordenAdjunto += 1;
                }
                buscarFichero();
            }
        });

        //Boton para temrinar con la entrevista
        btnTerminar = (Button) findViewById(R.id.botonTerminar);
        btnTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Activity_Adjuntos.this, Activity_Despedida.class);
                intent.putExtra("entrevista", entrevista);
                intent.putExtra("respuesta", respuesta);

                startActivity(intent);

            }
        });



    }

    public void buscarFichero() {

        //  intent que lanza un selector que muestra todos los proveedores de documentos.
        intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // intent filtra los resultados para mostrar solo documentos que se pueden abrir.
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // intent filtra los documentos por el tipo seleccionado.
        intent.setType("application/pdf");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            Uri uri = null;

            if (resultData != null) {
                // Si tienen un resultado recoge la ubicación del objeto.
                uri = resultData.getData();
                guardarArchivoAdjunto(uri.toString());

            }
        }

    }

    public void guardarArchivoAdjunto(String rutaArchivo){
        Archivo archivoCreado = new Archivo();
        archivoCreado.setRutaArchivo(rutaArchivo);
        archivoCreado.setNombreArchivo("Adjunto_" + String.valueOf(ordenAdjunto));

        respuesta.addArchivo(archivoCreado);
    }
}


