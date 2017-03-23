package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Pregunta;
import com.example.tictum.appcandidatos.beans.Respuesta;

import java.util.List;

public class Activity_PreguntaTextArea extends AppCompatActivity {

    private TextView preguntaTextArea;
    private EditText respuestaTextArea;
    private Button BtnEnvioTextArea;

    private Formulario formulario;
    private List<Pregunta> listaPreguntas;
    private Pregunta preguntaActual;
    private Pregunta preguntaSiguiente;
    private Intent intent;
    private Entrevista entrevista;
    private Respuesta respuesta;
    private int numeroPreguntasFormulario;
    private int numeroPregunta;
    private TextView numeroPreguntaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pregunta_textarea);

        //Recuperamos el numero de preguntas del formulario y el orden de la presente pregunta par mostrarlo en la interfaz
        numeroPreguntasFormulario = (int)getIntent().getSerializableExtra("numeroPreguntasFormulario");
        numeroPregunta = (int) getIntent().getSerializableExtra("numeroPregunta");
        //Se modifica la interfaz
        numeroPreguntaTextView = (TextView) findViewById(R.id.numeroPregunta);
        numeroPreguntaTextView.setText("Pregunta" + numeroPregunta + "/" + numeroPreguntasFormulario);

        preguntaTextArea = (TextView) findViewById(R.id.pregunta_textarea);
        respuestaTextArea = (EditText) findViewById(R.id.respuesta_textarea);
        BtnEnvioTextArea = (Button) findViewById(R.id.btn_envio_textarea);

        formulario = (Formulario)getIntent().getSerializableExtra("formulario");
        listaPreguntas = formulario.getPreguntas();
        listaPreguntas.remove(0);
        preguntaActual = (Pregunta)getIntent().getSerializableExtra("preguntaActual");
        entrevista = (Entrevista) getIntent().getSerializableExtra("entrevista");
        respuesta = (Respuesta) getIntent().getSerializableExtra("respuesta");

        BtnEnvioTextArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (listaPreguntas.isEmpty()){
                    //Modificar bean respuesta
                    respuesta.addRespuesta(respuestaTextArea.getText().toString());
                    for(String respuestaString: respuesta.getRespuestas()) {
                        Log.d("RESPUESTA", respuestaString);
                    }
                    // rellenar con actividad donde ir si acabamos formulario
                    intent = new Intent(Activity_PreguntaTextArea.this,Activity_Video_Transicion.class);
                    intent.putExtra("entrevista", entrevista);
                    intent.putExtra("respuesta", respuesta);
                    startActivity(intent);

                } else {

                    preguntaSiguiente = formulario.getPreguntas().get(0);

                    if (preguntaSiguiente.getTipoPregunta().equals("text")) {

                        intent = new Intent(Activity_PreguntaTextArea.this, Activity_PreguntaText.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("textArea")) {

                        intent = new Intent(Activity_PreguntaTextArea.this, Activity_PreguntaTextArea.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("checkBox")) {

                        intent = new Intent(Activity_PreguntaTextArea.this, Activity_PreguntaCheckBox.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("select")) {

                        intent = new Intent(Activity_PreguntaTextArea.this, Activity_PreguntaSelect.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("radioButton")) {

                        intent = new Intent(Activity_PreguntaTextArea.this, Activity_PreguntaRadioButton.class);
                    }

                    //Modificar bean respuesta
                    respuesta.addRespuesta(respuestaTextArea.getText().toString());
                    for(String respuestaString: respuesta.getRespuestas()) {
                        Log.d("RESPUESTA", respuestaString);
                    }

                    intent.putExtra("formulario", formulario);
                    intent.putExtra("preguntaActual", preguntaSiguiente);
                    intent.putExtra("entrevista", entrevista);
                    intent.putExtra("respuesta", respuesta);
                    intent.putExtra("numeroPreguntasFormulario", numeroPreguntasFormulario);
                    intent.putExtra("numeroPregunta", numeroPregunta + 1);

                    startActivity(intent);
                }
                
            }
        });

    }
}
