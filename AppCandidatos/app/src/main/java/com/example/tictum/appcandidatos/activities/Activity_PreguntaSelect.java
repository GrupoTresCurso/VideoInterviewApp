package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.adapter.AdaptadorSpinner;
import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Pregunta;
import com.example.tictum.appcandidatos.beans.Respuesta;

import java.util.List;

public class Activity_PreguntaSelect extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    private TextView preguntaSelect;
    private Spinner spinnerSelect;
    private Button btnEnvioSelect;

    private Formulario formulario;
    private List<Pregunta> listaPreguntas;
    private Pregunta preguntaActual;
    private Pregunta preguntaSiguiente;
    private Intent intent;

    String[] opciones;
    private String opcionSelected;
    private Entrevista entrevista;
    private Respuesta respuesta = new Respuesta();
    private int posicionOpcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pregunta_select);

        entrevista = (Entrevista) getIntent().getSerializableExtra("entrevista");
        respuesta = (Respuesta) getIntent().getSerializableExtra("respuesta");

        formulario = (Formulario)getIntent().getSerializableExtra("formulario");
        listaPreguntas = formulario.getPreguntas();
        listaPreguntas.remove(0);
        preguntaActual = (Pregunta)getIntent().getSerializableExtra("preguntaActual");
        opciones = preguntaActual.getOpciones();

        preguntaSelect = (TextView)findViewById(R.id.pregunta_select);
        preguntaSelect.setText(preguntaActual.getLabelPregunta());

        spinnerSelect = (Spinner)findViewById(R.id.spinner_select);
        spinnerSelect.setOnItemSelectedListener(this);

        AdaptadorSpinner adaptadorSpinner = new AdaptadorSpinner(getApplicationContext(), opciones);

        spinnerSelect.setAdapter(adaptadorSpinner);

        spinnerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                opcionSelected = (String) adapterView.getItemAtPosition(position);
                respuesta.getRespuestas().add(opcionSelected);
                posicionOpcion = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnEnvioSelect = (Button)findViewById(R.id.btn_envio_select);

        btnEnvioSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listaPreguntas.isEmpty()){
                    // rellenar con actividad donde ir si acabamos formulario
                    intent = new Intent(Activity_PreguntaSelect.this,Activity_Video_Transicion.class);
                    intent.putExtra("entrevista",entrevista);
                    startActivity(intent);

                } else {

                    preguntaSiguiente = formulario.getPreguntas().get(0);

                    if (preguntaSiguiente.getTipoPregunta().equals("text")) {

                        intent = new Intent(Activity_PreguntaSelect.this, Activity_PreguntaText.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("textArea")) {

                        intent = new Intent(Activity_PreguntaSelect.this, Activity_PreguntaTextArea.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("checkBox")) {

                        intent = new Intent(Activity_PreguntaSelect.this, Activity_PreguntaCheckBox.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("select")) {

                        intent = new Intent(Activity_PreguntaSelect.this, Activity_PreguntaSelect.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("radioButton")) {

                        intent = new Intent(Activity_PreguntaSelect.this, Activity_PreguntaRadioButton.class);
                    }

                    intent.putExtra("formulario", formulario);
                    intent.putExtra("preguntaActual", preguntaSiguiente);
                    startActivity(intent);
                }
            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), opciones[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
