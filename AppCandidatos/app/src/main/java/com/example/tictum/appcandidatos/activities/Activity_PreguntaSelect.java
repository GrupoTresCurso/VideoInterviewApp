package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class Activity_PreguntaSelect extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


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
    private Respuesta respuesta;
    private int posicionOpcion;

    private int numeroPreguntasFormulario;
    private int numeroPregunta;
    private TextView numeroPreguntaTextView;
    private boolean isCuestionarioSatisfaccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pregunta_select);

        //Recuperamos el numero de preguntas del formulario y el orden de la presente pregunta par mostrarlo en la interfaz
        numeroPreguntasFormulario = (int)getIntent().getSerializableExtra("numeroPreguntasFormulario");
        numeroPregunta = (int) getIntent().getSerializableExtra("numeroPregunta");
        //Se modifica la interfaz
        numeroPreguntaTextView = (TextView) findViewById(R.id.numeroPregunta);
        numeroPreguntaTextView.setText("Pregunta" + numeroPregunta + "/" + numeroPreguntasFormulario);

        entrevista = (Entrevista) getIntent().getSerializableExtra("entrevista");
        respuesta = (Respuesta) getIntent().getSerializableExtra("respuesta");
        isCuestionarioSatisfaccion = (boolean)getIntent().getSerializableExtra("isCuestionarioSatisfaccion");

        if (isCuestionarioSatisfaccion) {
            // recibimos el formulario de satisfaccion para mostrarlo
            formulario = entrevista.getCuestionarioSatisfaccion();
        } else {
            // recibimos el formulario a mostrar que no es el de satisfaccion
            formulario = (Formulario) getIntent().getSerializableExtra("formulario");
        }

        listaPreguntas = formulario.getPreguntas();
        listaPreguntas.remove(0);
        preguntaActual = (Pregunta)getIntent().getSerializableExtra("preguntaActual");
        opciones = preguntaActual.getOpciones();
        for(String opcion: opciones){
            Log.d("OPCION", opcion);
        }

        preguntaSelect = (TextView)findViewById(R.id.pregunta_select);
        preguntaSelect.setText(preguntaActual.getLabelPregunta());

        spinnerSelect = (Spinner)findViewById(R.id.SpinnerOpciones);
        spinnerSelect.setOnItemSelectedListener(this);

        AdaptadorSpinner adaptadorSpinner = new AdaptadorSpinner(getApplicationContext(), opciones);

        spinnerSelect.setAdapter(adaptadorSpinner);

        btnEnvioSelect = (Button)findViewById(R.id.btn_envio_select);

        btnEnvioSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listaPreguntas.isEmpty()){
                    //Modificar bean respuesta
                    respuesta.addRespuesta(opcionSelected);
                    Log.d("Orden", "opcion añadida: " + opcionSelected);
                    for(String respuestaString: respuesta.getRespuestas()) {
                        Log.d("RESPUESTA", respuestaString);
                    }

                    if (isCuestionarioSatisfaccion){
                        // si es el cuestionario de satisfaccion vamos a la subida de un archivo adjunto, curriculum,etc
                        intent = new Intent(Activity_PreguntaSelect.this,Activity_Adjuntos.class);
                        intent.putExtra("entrevista", entrevista);
                        intent.putExtra("respuesta", respuesta);
                        startActivity(intent);
                    } else {
                        // rellenar con actividad donde ir si acabamos formulario
                        intent = new Intent(Activity_PreguntaSelect.this, Activity_Video_Transicion.class);
                        intent.putExtra("entrevista", entrevista);
                        intent.putExtra("respuesta", respuesta);
                        startActivity(intent);
                    }

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

                    //Modificar bean respuesta

                    respuesta.addRespuesta(opcionSelected);
                    Log.d("Orden", "opcion añadida: " + opcionSelected);
                    for(String respuestaString: respuesta.getRespuestas()) {
                        Log.d("RESPUESTA", respuestaString);
                    }

                    intent.putExtra("formulario", formulario);
                    intent.putExtra("respuesta", respuesta);
                    intent.putExtra("preguntaActual", preguntaSiguiente);
                    intent.putExtra("numeroPreguntasFormulario", numeroPreguntasFormulario);
                    intent.putExtra("numeroPregunta", numeroPregunta + 1);
                    intent.putExtra("isCuestionarioSatisfaccion", isCuestionarioSatisfaccion);
                    startActivity(intent);
                }
            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        //Se activa el boton de enviar
        btnEnvioSelect.setEnabled(true);

        Toast.makeText(getApplicationContext(), opciones[position], Toast.LENGTH_LONG).show();
        opcionSelected = opciones[position];
        Log.d("OPCION SELECTED S", opcionSelected);
        Log.d("Orden", "opcion seleccionada: " + opcionSelected);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }


}
