package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.tictum.appcandidatos.R;
import com.example.tictum.appcandidatos.adapter.AdaptadorPaises;
import com.example.tictum.appcandidatos.beans.Pais;


public class Activity_Formulario extends AppCompatActivity {

    private RadioGroup radioGroupExperiencia;

    private Spinner spinner;

    private Button btnSiguiente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout__formulario);


        radioGroupExperiencia = (RadioGroup) findViewById(R.id.radio_group_experiencia);


        Pais espania = new Pais("España");
        Pais uk = new Pais("Inglaterra");
        Pais francia = new Pais("Francia");
        Pais alemania = new Pais("Alemania");

        Pais[] paises = {espania, uk, francia, alemania};

        spinner = (Spinner) findViewById(R.id.pais_origen);

        AdaptadorPaises spinnerNacionalidad = new AdaptadorPaises(this, paises);

        spinnerNacionalidad.setDropDownViewResource(R.layout.item_paises);

        spinner.setAdapter(spinnerNacionalidad);


        btnSiguiente = (Button) findViewById(R.id.btn_siguiente);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Formulario.this, Activity_Formulario2.class);
                String checkedRadioButon = getCheckedButton();
                intent.putExtra("", checkedRadioButon);
                startActivity(intent);
            }
        });
    }
        private String getCheckedButton(){

        int checkedRadioButtonId = radioGroupExperiencia.getCheckedRadioButtonId();

        String returnChecked = "Experiencia";

        if(checkedRadioButtonId == R.id.opcion1){
            returnChecked = "Sin experiencia";
        } else if(checkedRadioButtonId == R.id.opcion2){
            returnChecked = "Entre 1 a 3 años.";
        } else if(checkedRadioButtonId == R.id.opcion3){
            returnChecked = "Entre 3 a 5 años.";
        } else if(checkedRadioButtonId == R.id.opcion4)
            returnChecked =" > 5 años.";
        return returnChecked;
    }
    }





