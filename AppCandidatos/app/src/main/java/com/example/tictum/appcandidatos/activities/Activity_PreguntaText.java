package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tictum.appcandidatos.R;

import org.w3c.dom.Text;

public class Activity_PreguntaText extends AppCompatActivity {

    private TextView preguntaText ;
    private EditText respuestaText;
    private Button btnEnvioText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pregunta_text);

        Activity_SplashScreen splashScreen=new Activity_SplashScreen();

        preguntaText = (TextView)findViewById(R.id.pregunta_text);

        String preguntaPrueba;
        /* preguntaText.setText(); */

        respuestaText = (EditText)findViewById(R.id.respuesta_text);

        btnEnvioText = (Button)findViewById(R.id.btn_envio_text);

        btnEnvioText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_PreguntaText.this,Activity_PreguntaTextArea.class);

                startActivity(intent);
            }
        });



    }
}
