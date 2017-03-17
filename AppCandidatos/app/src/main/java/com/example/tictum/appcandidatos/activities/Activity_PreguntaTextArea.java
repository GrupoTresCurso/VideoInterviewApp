package com.example.tictum.appcandidatos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tictum.appcandidatos.R;

public class Activity_PreguntaTextArea extends AppCompatActivity {

    private TextView preguntaTextArea;
    private EditText respuestaTextArea;
    private Button BtnEnvioTextArea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pregunta_textarea);

        preguntaTextArea = (TextView) findViewById(R.id.pregunta_textarea);
        respuestaTextArea = (EditText) findViewById(R.id.respuesta_textarea);
        BtnEnvioTextArea = (Button) findViewById(R.id.btn_envio_textarea);

    }
}
