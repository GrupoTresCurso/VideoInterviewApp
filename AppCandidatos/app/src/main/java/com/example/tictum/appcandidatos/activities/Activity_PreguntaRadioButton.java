package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Pregunta;

import java.util.List;

public class Activity_PreguntaRadioButton extends AppCompatActivity {

    private TextView preguntaRadioButton;
    private RadioGroup radioGroupPregunta;
    private Button btnEnvioRadioButton;

    private Formulario formulario;
    private List<Pregunta> listaPreguntas;
    private Pregunta preguntaActual;
    private Pregunta preguntaSiguiente;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pregunta_radio_button);

        formulario = (Formulario)getIntent().getSerializableExtra("formulario");
        listaPreguntas = formulario.getPreguntas();
        listaPreguntas.remove(0);
        preguntaActual = (Pregunta)getIntent().getSerializableExtra("preguntaActual");
        String[] opciones = preguntaActual.getOpciones();

        preguntaRadioButton = (TextView)findViewById(R.id.pregunta_radio);
        radioGroupPregunta = (RadioGroup)findViewById(R.id.radio_group_pregunta);
        btnEnvioRadioButton = (Button)findViewById(R.id.btn_envio_radiobutton);

        for (int i = 0; i < opciones.length; i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i);
            rdbtn.setText(opciones[i]);
            radioGroupPregunta.addView(rdbtn);
        }

        preguntaRadioButton = (TextView)findViewById(R.id.pregunta_radio_button);
        preguntaRadioButton.setText(preguntaActual.getLabelPregunta());

        btnEnvioRadioButton = (Button)findViewById(R.id.btn_envio_radiobutton);

        btnEnvioRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listaPreguntas.isEmpty()){
                    // rellenar con actividad donde ir si acabamos formulario
                }

                preguntaSiguiente = formulario.getPreguntas().get(0);

                if (preguntaSiguiente.getTipoPregunta().equals("text")){

                    intent = new Intent(Activity_PreguntaRadioButton.this, Activity_PreguntaText.class);

                } else if (preguntaSiguiente.getTipoPregunta().equals("textArea")){

                    intent = new Intent(Activity_PreguntaRadioButton.this, Activity_PreguntaTextArea.class);

                } else if (preguntaSiguiente.getTipoPregunta().equals("checkBox")){

                    intent = new Intent(Activity_PreguntaRadioButton.this, Activity_PreguntaCheckBox.class);

                } else if (preguntaSiguiente.getTipoPregunta().equals("select")){

                    intent = new Intent(Activity_PreguntaRadioButton.this, Activity_PreguntaSelect.class);

                } else if (preguntaSiguiente.getTipoPregunta().equals("radioButton")){

                    intent = new Intent(Activity_PreguntaRadioButton.this, Activity_PreguntaRadioButton.class);
                }

                intent.putExtra("formulario", formulario);
                intent.putExtra("preguntaActual", preguntaSiguiente);
                startActivity(intent);
            }
        });

            }
    }

    /*
    private String getCheckedButton() {

        int checkedRadioButtonId = radioGroupPregunta.getCheckedRadioButtonId();

        String returnChecked = " ";

        if (checkedRadioButtonId == R.id.radio_opcion1) {
            returnChecked = "Sin experiencia.";
        } else if (checkedRadioButtonId == R.id.radio_opcion2) {
            returnChecked = "entre 1 a 3 años.";
        } else if (checkedRadioButtonId == R.id.radio_opcion3) {
            returnChecked = "entre 3 a 5 años.";
        } else if (checkedRadioButtonId == R.id.radio_opcion4) {
            returnChecked = "> 5 años.";
        }
        return returnChecked;
    }

    */
