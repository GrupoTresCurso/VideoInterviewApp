package com.example.tictum.appcandidatos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.tictum.appcandidatos.R;

public class Activity_Formulario2 extends AppCompatActivity {

    private CheckBox tecnologia1;
    private CheckBox tecnologia2;
    private CheckBox tecnologia3;
    private CheckBox tecnologia4;

    private EditText edad;

    private Button btn_siguiente2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout__formulario2);

        tecnologia1 = (CheckBox) findViewById(R.id.tecnologia1);
        tecnologia2 = (CheckBox) findViewById(R.id.tecnologia2);
        tecnologia3 = (CheckBox) findViewById(R.id.tecnologia3);
        tecnologia4 = (CheckBox) findViewById(R.id.tecnologia4);

        edad = (EditText) findViewById(R.id.edad);

        btn_siguiente2 = (Button)findViewById(R.id.btn_siguiente2);

        btn_siguiente2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_Formulario2.this, Activity_Formulario3.class);

                String[] tecnologias = new String[4];
                tecnologias[0]="";
                tecnologias[1]="";
                tecnologias[2]="";
                tecnologias[3]="";
                if(tecnologia1.isChecked()){
                    tecnologias[0]=("AngularJS");
                }
                if(tecnologia2.isChecked()){
                    tecnologias[1]=("PHP");
                }
                if(tecnologia3.isChecked()){
                    tecnologias[2]=("Python");
                }
                if(tecnologia4.isChecked()){
                    tecnologias[3]=("Sql");
                }
                intent.putExtra("tecnologias",tecnologias);
                intent.putExtra("edad",String.valueOf(edad.getText()));
                startActivity(intent);
            }
        });


    }
}
