package com.example.tictum.appcandidatos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tictum.appcandidatos.R;

public class Activity_Formulario4 extends AppCompatActivity {

    private Button btnEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout__formulario4);


        btnEnviar = (Button) findViewById(R.id.btn_siguiente);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
