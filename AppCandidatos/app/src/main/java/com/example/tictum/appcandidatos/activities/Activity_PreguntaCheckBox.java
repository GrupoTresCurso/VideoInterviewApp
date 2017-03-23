package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private Respuesta respuesta;
    private String respuestaSelected;
    private List<String> checkedlist = new ArrayList<String>();
    private int numeroPreguntasFormulario;
    private int numeroPregunta;
    private TextView numeroPreguntaTextView;
    private boolean isCuestionarioSatisfaccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pregunta_checkbox);

        //Recuperamos el numero de preguntas del formulario y el orden de la presente pregunta par mostrarlo en la interfaz
        numeroPreguntasFormulario = (int)getIntent().getSerializableExtra("numeroPreguntasFormulario");
        numeroPregunta = (int) getIntent().getSerializableExtra("numeroPregunta");
        //Se modifica la interfaz
        numeroPreguntaTextView = (TextView) findViewById(R.id.numeroPregunta);
        numeroPreguntaTextView.setText("Pregunta" + numeroPregunta + "/" + numeroPreguntasFormulario);

        entrevista = (Entrevista) getIntent().getSerializableExtra("entrevista");

        isCuestionarioSatisfaccion = (boolean)getIntent().getSerializableExtra("isCuestionarioSatisfaccion");

        if (isCuestionarioSatisfaccion) {
            // recibimos el formulario de satisfaccion para mostrarlo
            formulario = entrevista.getCuestionarioSatisfaccion();
        } else {
            // recibimos el formulario a mostrar que no es el de satisfaccion
            formulario = (Formulario) getIntent().getSerializableExtra("formulario");
        }

        // recuperamos la lista de preguntas nuevamente
        listaPreguntas = formulario.getPreguntas();
        // borramos la pregunta de la primera posicion que es la que vamos a contestar ahora
        listaPreguntas.remove(0);
        // obtenemos la pregunta actual para mostrarla en el layout
        preguntaActual = (Pregunta)getIntent().getSerializableExtra("preguntaActual");
        // obtenemos el array de opciones que tiene la pregunta para mostrarlos
        opciones = preguntaActual.getOpciones();
        // recuperamos respuesta
        respuesta = (Respuesta) getIntent().getSerializableExtra("respuesta");

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
                //Se activa el boton de enviar
                btnEnvioCheckBox.setEnabled(true);
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
        int i;
        for (i = 0; i < opciones.length; i++) {
            CheckBox cb = new CheckBox(this);
            cb.setId(i);
            // ponemos el texto del array de strings que viene
            cb.setText(opciones[i]);
            // el tamaño de la letra de cada opcion
            cb.setTextSize(20);
            // añadimos al linearLayout cada checkbox creado
            linearLayout.addView(cb);
            // añadimos el listener para capturar los checkbox clicados
            cb.setOnClickListener(checkBoxListener);
        }
        CheckBox cbOtros = new CheckBox(this);
        cbOtros.setId(i + 1);
        cbOtros.setTextSize(20);
        linearLayout.addView(cbOtros);


        // accion del boton SIGUIENTE

        btnEnvioCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listaPreguntas.isEmpty()){
                    // Modificar bean Respuesta
                    Log.d("NUMERO OPCIONES", String.valueOf(checkedlist.size()));
                    for(String respuesta: checkedlist){
                        Log.d("OPCION SELECTED CB", respuesta);
                    }
                    respuestaSelected = checkedlist.toString();
                    Log.d("OPCIONES SELE STRING", respuestaSelected);
                    // añadimos la respuesta del checkbox a la lista de respuestas
                    respuesta.addRespuesta(respuestaSelected);
                    for(String respuestaString: respuesta.getRespuestas()) {
                        Log.d("RESPUESTA", respuestaString);
                    }

                    if (isCuestionarioSatisfaccion){
                        // si es el cuestionario de satisfaccion vamos a la subida de un archivo adjunto, curriculum,etc
                        intent = new Intent(Activity_PreguntaCheckBox.this,Activity_Adjuntos.class);
                        intent.putExtra("entrevista", entrevista);
                        intent.putExtra("respuesta", respuesta);
                        startActivity(intent);
                    } else {
                        // rellenar con actividad donde ir si acabamos formulario
                        intent = new Intent(Activity_PreguntaCheckBox.this, Activity_Video_Transicion.class);
                        intent.putExtra("entrevista", entrevista);
                        intent.putExtra("respuesta", respuesta);
                        startActivity(intent);
                    }

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
                     respuestaSelected = checkedlist.toString();

                    Log.d("NUMERO OPCIONES", String.valueOf(checkedlist.size()));
                    for(String respuesta: checkedlist){
                        Log.d("OPCION SELECTED CB", respuesta);
                    }

                    Log.d("OPCIONES SELE STRING", respuestaSelected);
                    // añadimos la respuesta del checkbox a la lista de respuestas
                    respuesta.addRespuesta(respuestaSelected);
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
