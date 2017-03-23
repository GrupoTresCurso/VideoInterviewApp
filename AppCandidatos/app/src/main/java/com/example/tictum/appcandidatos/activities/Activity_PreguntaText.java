package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class Activity_PreguntaText extends AppCompatActivity {

    private TextView preguntaText;
    private EditText respuestaText;
    private Button btnEnvioText;

    private Formulario formulario;
    private List<Pregunta> listaPreguntas;
    private Pregunta preguntaActual;
    private Pregunta preguntaSiguiente;
    private Entrevista entrevista;
    private Respuesta respuesta;
    private Intent intent;
    private int numeroPreguntasFormulario;
    private int numeroPregunta;
    private TextView numeroPreguntaTextView;
    private boolean isCuestionarioSatisfaccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pregunta_text);

        //Recuperamos el numero de preguntas del formulario y el orden de la presente pregunta par mostrarlo en la interfaz
        numeroPreguntasFormulario = (int)getIntent().getSerializableExtra("numeroPreguntasFormulario");
        numeroPregunta = (int) getIntent().getSerializableExtra("numeroPregunta");
        //Se modifica la interfaz
        numeroPreguntaTextView = (TextView) findViewById(R.id.numeroPregunta);
        numeroPreguntaTextView.setText("Pregunta" + numeroPregunta + "/" + numeroPreguntasFormulario);
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
        entrevista = (Entrevista) getIntent().getSerializableExtra("entrevista");
        respuesta = (Respuesta) getIntent().getSerializableExtra("respuesta");

        preguntaText = (TextView)findViewById(R.id.pregunta_text);
        preguntaText.setText(preguntaActual.getLabelPregunta());

        btnEnvioText = (Button)findViewById(R.id.btn_envio_text);

        respuestaText = (EditText)findViewById(R.id.respuesta_text);

        respuestaText.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(!s.equals("") ){
                    //Se activa el boton de enviar
                    btnEnvioText.setEnabled(true);
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {          }

            public void afterTextChanged(Editable s) {          }
        });



        btnEnvioText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listaPreguntas.isEmpty()){
                    //Modificar bean respuesta
                    respuesta.addRespuesta(respuestaText.getText().toString());
                    for(String respuestaString: respuesta.getRespuestas()) {
                        Log.d("RESPUESTA", respuestaString);
                    }
                    // rellenar con actividad donde ir si acabamos formulario
                    intent = new Intent(Activity_PreguntaText.this,Activity_Video_Transicion.class);
                    intent.putExtra("entrevista", entrevista);
                    intent.putExtra("respuesta", respuesta);
                    startActivity(intent);
                } else {

                    preguntaSiguiente = formulario.getPreguntas().get(0);

                    if (preguntaSiguiente.getTipoPregunta().equals("text")) {

                        intent = new Intent(Activity_PreguntaText.this, Activity_PreguntaText.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("textArea")) {

                        intent = new Intent(Activity_PreguntaText.this, Activity_PreguntaTextArea.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("checkBox")) {

                        intent = new Intent(Activity_PreguntaText.this, Activity_PreguntaCheckBox.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("select")) {

                        intent = new Intent(Activity_PreguntaText.this, Activity_PreguntaSelect.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("radioButton")) {

                        intent = new Intent(Activity_PreguntaText.this, Activity_PreguntaRadioButton.class);
                    }

                    //Modificar bean respuesta
                    respuesta.addRespuesta(respuestaText.getText().toString());
                    for(String respuestaString: respuesta.getRespuestas()) {
                        Log.d("RESPUESTA", respuestaString);
                    }

                    intent.putExtra("formulario", formulario);
                    intent.putExtra("preguntaActual", preguntaSiguiente);
                    intent.putExtra("entrevista", entrevista);
                    intent.putExtra("respuesta", respuesta);
                    intent.putExtra("numeroPreguntasFormulario", numeroPreguntasFormulario);
                    intent.putExtra("numeroPregunta", numeroPregunta + 1);
                    intent.putExtra("isCuestionarioSatisfaccion", isCuestionarioSatisfaccion);
                    startActivity(intent);
                }

            }
        });


    }
}
