package com.example.tictum.appcandidatos.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Archivo;


public class Activity_Adjuntos extends AppCompatActivity {

    private Archivo adjunto;

    private static final int READ_REQUEST_CODE = 42;
    private Intent intent;

    private Button btnAdjuntarArchivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_adjuntos);

        //Inflar lista de adjuntos
        //Adjunto[] listaAdjuntos = {};
        //Adjunto adjunto1 = new Adjunto(0, "add cv", 0);
        //Adjunto adjunto2 = new Adjunto(0, "add cv2", 0);

        // AdaptadorAdjuntos adapter = new AdaptadorAdjuntos(this, listaAdjuntos);
        //ListView lista = (ListView) findViewById(R.id.listaAdjuntos);
        //lista.setAdapter(adapter);

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
        intent.setType("pdf/*");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
            }
        }

    }
}


