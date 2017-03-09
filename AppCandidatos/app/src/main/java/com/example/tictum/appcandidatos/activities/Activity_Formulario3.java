package com.example.tictum.appcandidatos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.tictum.appcandidatos.R;

public class Activity_Formulario3 extends AppCompatActivity {

    private RadioGroup radio_group_comunicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout__formulario3);


        radio_group_comunicacion = (RadioGroup) findViewById(R.id.radio_group_comunicacion);

}
}
