package com.example.tictum.appcandidatos.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.adapter.AdaptadorAdjuntos;
import com.example.tictum.appcandidatos.beans.Archivo;
import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Respuesta;


public class Activity_Adjuntos extends AppCompatActivity {

    private Entrevista entrevista;
    private Respuesta respuesta;
    private Formulario formularioAdjunto;


    private static final int READ_REQUEST_CODE = 42;
    private Intent intent;

    private Button btnAdjuntarArchivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_adjuntos);

        btnAdjuntarArchivo = (Button) findViewById(R.id.btn_adjuntar_adjunto);

        btnAdjuntarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarFichero();

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

                entrevista = (Entrevista) getIntent().getSerializableExtra("entrevista");

                respuesta = (Respuesta) getIntent().getSerializableExtra("respuesta");


              Adjunto[] listaAdjuntos = {};

                Adjunto adjunto1 = new Adjunto(0, "add cv", 0);

                AdaptadorAdjuntos adapter = new AdaptadorAdjuntos(this, listaAdjuntos);
                ListView lista = (ListView) findViewById(R.id.listaAdjuntos);
                lista.setAdapter(adapter);


            }
        }

    }
}


