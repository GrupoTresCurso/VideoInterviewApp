package com.example.tictum.appcandidatos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.tictum.appcandidatos.R;

public class Activity_Formulario extends AppCompatActivity {

    private RadioGroup radio_group_experiencia;
    private Spinner spinner_paises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout__formulario);

    }
}
