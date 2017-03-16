package com.example.tictum.appcandidatos.business;

import android.content.Context;

import com.example.tictum.appcandidatos.beans.Candidato;
import com.example.tictum.appcandidatos.sqliteDAO.CandidatoDAO;

import java.util.ArrayList;

/**
 * Created by Usuario on 16/03/2017.
 */

public class CandidatoManager {
    private CandidatoDAO candidatoDAO ;

    public CandidatoManager(Context context) {
        this.candidatoDAO = new CandidatoDAO(context);
    }

    public long insertCandidato(Candidato candidato){
        return candidatoDAO.insertCandidato(candidato);
    }

    public Candidato getCandidato(int idCandidato){
        return candidatoDAO.getCandidato(idCandidato);
    }

    public ArrayList<Candidato> getAllCandidatos(){
        return getAllCandidatos();
    }
}
