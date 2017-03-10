package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.tictum.appcandidatos.R;

public class Activity_Formulario3 extends AppCompatActivity {

    private Spinner spinner;

    private RadioGroup radioGroupComunicacion;

    private Button btnSiguiente3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout__formulario3);


        radioGroupComunicacion = (RadioGroup) findViewById(R.id.radio_group_comunicacion);

        btnSiguiente3=(Button)findViewById(R.id.btn_siguiente3);

        btnSiguiente3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Activity_Formulario3.this,Activity_Formulario4.class);




                startActivity(intent);
            }
        });

}
}
