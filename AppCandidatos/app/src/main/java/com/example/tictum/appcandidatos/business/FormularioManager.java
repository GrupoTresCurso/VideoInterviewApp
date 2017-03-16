package com.example.tictum.appcandidatos.business;

import android.content.Context;

import com.example.tictum.appcandidatos.beans.Formulario;
import com.example.tictum.appcandidatos.beans.Pregunta;
import com.example.tictum.appcandidatos.sqliteDAO.FormularioDAO;

import java.util.ArrayList;

/**
 * Created by Usuario on 16/03/2017.
 */

public class FormularioManager {

    private FormularioDAO formularioDAO;

    public FormularioManager(Context context) {
        this.formularioDAO = new FormularioDAO(context);
    }

    public long insertFormulario(Formulario formulario){
        return formularioDAO.insertFormulario(formulario);
    }

    public Formulario getFormulario(int idFormulario){
        return formularioDAO.getFormulario(idFormulario);
    }

    public ArrayList<Formulario> getAllFormularios(){
        return formularioDAO.getAllFormularios();
    }

    public long insertFormulario_Pregunta(Formulario formulario){
        return formularioDAO.insertFormulario_Pregunta(formulario);
    }

    public ArrayList<Pregunta> getPreguntas_Formulario(int idFormulario){
        return formularioDAO.getPreguntas_Formulario(idFormulario);
    }
}
