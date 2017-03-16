package com.example.tictum.appcandidatos.business;

import android.content.Context;

import com.example.tictum.appcandidatos.beans.Pregunta;
import com.example.tictum.appcandidatos.sqliteDAO.PreguntaDAO;

import java.util.ArrayList;

/**
 * Created by Usuario on 16/03/2017.
 */

public class PreguntaManager {

    private PreguntaDAO preguntaDAO;

    public PreguntaManager(Context context) {
        this.preguntaDAO = new PreguntaDAO(context);
    }

    public long insertPregunta(Pregunta pregunta){
        return preguntaDAO.insertPregunta(pregunta);
    }

    public Pregunta getPregunta(int idPregunta){
        return preguntaDAO.getPregunta(idPregunta);
    }

    public ArrayList<Pregunta> getAllPreguntas(){
        return preguntaDAO.getAllPreguntas();
    }
}
