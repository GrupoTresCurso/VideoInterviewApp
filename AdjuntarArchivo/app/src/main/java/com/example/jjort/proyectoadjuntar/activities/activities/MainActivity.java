package com.example.jjort.proyectoadjuntar.activities.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jjort.proyectoadjuntar.R;
import com.example.jjort.proyectoadjuntar.activities.adapters.AdaptadorAdjuntos;
import com.example.jjort.proyectoadjuntar.activities.beans.Archivo;
import com.example.jjort.proyectoadjuntar.activities.beans.Respuesta;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Archivo archivo;
    private Respuesta respuesta;

    private List<Archivo> listaArchivo;

    private Button btnAdjuntarArchivo;
    private Intent intent;


    private static final int READ_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void onActivityResult(int requestCode, int resultCode, Intent resulData) {

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            listaArchivo = new ArrayList<>();


            Uri uri = null;


            if (resulData != null) {
                uri = resulData.getData();
                String rutaArchivo = uri.toString();

               File fichero=new File(rutaArchivo);

                Archivo archivo =new Archivo(0, fichero.getName(), rutaArchivo);


                listaArchivo.add(archivo);
                archivo.getRutaArchivo();

                Archivo[] listaArchivo = {archivo};

                AdaptadorAdjuntos adaptador = new AdaptadorAdjuntos(this,listaArchivo);
                // Donde vamos a mostrar los objetos Archivo
                ListView listaArchivos = (ListView) findViewById(R.id.listaAdjuntos);
                // Adjudicamos el adaptador
                listaArchivos.setAdapter(adaptador);
                adaptador.notifyDataSetChanged();


                /*Toast.makeText(getApplicationContext(), rutaArchivo, Toast.LENGTH_LONG).show();*/


            }
        }

    }


}



