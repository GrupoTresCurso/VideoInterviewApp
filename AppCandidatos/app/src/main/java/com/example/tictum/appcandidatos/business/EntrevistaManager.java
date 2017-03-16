package com.example.tictum.appcandidatos.business;

import android.content.Context;

import com.example.tictum.appcandidatos.beans.Entrevista;
import com.example.tictum.appcandidatos.sqliteDAO.EntrevistaDAO;

import java.util.ArrayList;

/**
 * Created by Usuario on 16/03/2017.
 */

public class EntrevistaManager {

    private EntrevistaDAO entrevistaDAO;

    public EntrevistaManager(Context context) {
        this.entrevistaDAO = new EntrevistaDAO(context);
    }

    public long insertEntrevista(Entrevista entrevista){
        return entrevistaDAO.insertEntrevista(entrevista);
    }

    public Entrevista getEntrevista(int idEntrevista){
        return entrevistaDAO.getEntrevista(idEntrevista);
    }

    public ArrayList<Entrevista> getAllEntrevistas(){
        return entrevistaDAO.getAllEntrevistas();
    }

    public long insertEntrevista_Formulario(Entrevista entrevista){
        return entrevistaDAO.insertEntrevista_Formulario(entrevista);
    }

    public  long insertEntrevista_Video(Entrevista entrevista){
        return entrevistaDAO.insertEntrevista_Video(entrevista);
    }

    public long insertEntrevistaCandidato(Entrevista entrevista){
        return entrevistaDAO.insertEntrevistaCandidato(entrevista);
    }


}
