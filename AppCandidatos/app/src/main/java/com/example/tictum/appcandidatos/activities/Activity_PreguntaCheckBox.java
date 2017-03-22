package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Pregunta;
import com.example.tictum.appcandidatos.beans.Respuesta;

import java.util.ArrayList;
import java.util.List;

public class Activity_PreguntaCheckBox extends AppCompatActivity {

    private TextView preguntaCheckBox;
    private Button btnEnvioCheckBox;
    private LinearLayout linearLayout;

    private Formulario formulario;
    private List<Pregunta> listaPreguntas;
    private Pregunta preguntaActual;
    private Pregunta preguntaSiguiente;
    private Entrevista entrevista;
    private Intent intent;
    private String[] opciones;
    private Respuesta respuesta = new Respuesta();
    private String respuestaSelected;
    List<String> checkedlist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pregunta_checkbox);

        entrevista = (Entrevista) getIntent().getSerializableExtra("entrevista");
        // recuperamos formulario para acceder a las preguntas
        formulario = (Formulario)getIntent().getSerializableExtra("formulario");
        // recuperamos la lista de preguntas nuevamente
        listaPreguntas = formulario.getPreguntas();
        // borramos la pregunta de la primera posicion que es la que vamos a contestar ahora
        listaPreguntas.remove(0);
        // obtenemos la pregunta actual para mostrarla en el layout
        preguntaActual = (Pregunta)getIntent().getSerializableExtra("preguntaActual");
        // obtenemos el array de opciones que tiene la pregunta para mostrarlos
        opciones = preguntaActual.getOpciones();

        // ponemos la pregunta en el layout
        preguntaCheckBox = (TextView) findViewById(R.id.pregunta_checkbox);
        preguntaCheckBox.setText(preguntaActual.getLabelPregunta());
        // recuperamos boton para añadir un listener para cambiar de activity
        btnEnvioCheckBox = (Button) findViewById(R.id.btn_envio_checkbox);
        // obtenemos el linearlayout del layout para añadir checkbox dinamicos
        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutCheckBox);

        View.OnClickListener checkBoxListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // obtenemos posicion del array que checkea o no el usuario
                int id = view.getId();
                // creamos boolean para preguntar si esta o no checkeado
                boolean checked = ((CheckBox) view).isChecked();
                // si esta checkeado, metemos el string de la posicion que nos diga el id a la List<String>
                if(checked){
                    checkedlist.add(opciones[id]);
                }else{
                 // si no entonces quitamos ese string de la lista
                    checkedlist.remove(opciones[id]);
                }
            }
        };

        // creamos el array que va a añadir tantos checkbox como tamaño tenga el array
        for (int i = 0; i < opciones.length; i++) {
            CheckBox cb = new CheckBox(this);
            cb.setId(i);
            // ponemos el texto del array de strings que viene
            cb.setText(opciones[i]);
            // añadimos al linearLayout cada checkbox creado
            linearLayout.addView(cb);
            // añadimos el listener para capturar los checkbox clicados
            cb.setOnClickListener(checkBoxListener);
        }

        // accion del boton SIGUIENTE

        btnEnvioCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listaPreguntas.isEmpty()){
                    // rellenar con actividad donde ir si acabamos formulario
                    intent = new Intent(Activity_PreguntaCheckBox.this,Activity_Video_Transicion.class);
                    intent.putExtra("entrevista", entrevista);
                    startActivity(intent);

                } else {

                    // obtenemos la siguiente pregunta
                    preguntaSiguiente = formulario.getPreguntas().get(0);

                    // elegimos siguiente intent segun el tipo de la pregunta que este en la lista

                    if (preguntaSiguiente.getTipoPregunta().equals("text")) {

                        intent = new Intent(Activity_PreguntaCheckBox.this, Activity_PreguntaText.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("textArea")) {

                        intent = new Intent(Activity_PreguntaCheckBox.this, Activity_PreguntaTextArea.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("checkBox")) {

                        intent = new Intent(Activity_PreguntaCheckBox.this, Activity_PreguntaCheckBox.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("select")) {

                        intent = new Intent(Activity_PreguntaCheckBox.this, Activity_PreguntaSelect.class);

                    } else if (preguntaSiguiente.getTipoPregunta().equals("radioButton")) {

                        intent = new Intent(Activity_PreguntaCheckBox.this, Activity_PreguntaRadioButton.class);
                    }

                    // pasamos la lista a un string separado por comas
                    String respuestaCB = checkedlist.toString();
                    // añadimos la respuesta del checkbox a la lista de respuestas
                    respuesta.addRespuesta(respuestaSelected);

                    intent.putExtra("formulario", formulario);
                    intent.putExtra("preguntaActual", preguntaSiguiente);
                    intent.putExtra("entrevista", entrevista);
                    startActivity(intent);
                }
            }

        });

    }
}
