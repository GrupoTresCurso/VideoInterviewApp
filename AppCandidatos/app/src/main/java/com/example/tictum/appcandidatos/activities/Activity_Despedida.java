package com.example.tictum.appcandidatos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Respuesta;

public class Activity_Despedida extends AppCompatActivity {


    private Entrevista entrevista;
    private Respuesta respuesta;
    private TextView mensajeDespedida;
    private String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_despedida);

        entrevista = (Entrevista) getIntent().getSerializableExtra("entrevista");
        respuesta = (Respuesta) getIntent().getSerializableExtra("respuesta");

        mensaje = entrevista.getMensaje();

        mensajeDespedida = (TextView) findViewById(R.id.mensaje_despedida);
        mensajeDespedida.setText(mensaje);


    }
}
