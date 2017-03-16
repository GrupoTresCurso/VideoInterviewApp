package com.example.tictum.appcandidatos.business;

import android.content.Context;

import com.example.tictum.appcandidatos.beans.Respuesta;
import com.example.tictum.appcandidatos.sqliteDAO.RespuestaDAO;

import java.util.ArrayList;

/**
 * Created by Usuario on 16/03/2017.
 */

public class RespuestaManager {

    private RespuestaDAO respuestaDAO;

    public RespuestaManager(Context context) {
        this.respuestaDAO = new RespuestaDAO(context);
    }

    public long insertRespuesta(Respuesta respuesta){
        return respuestaDAO.insertRespuesta(respuesta);
    }

    public Respuesta getRespuesta(int idRespuesta){
        return respuestaDAO.getRespuesta(idRespuesta);
    }

    public ArrayList<Respuesta> getAllRespuestas(){
        return respuestaDAO.getAllRespuestas();
    }

    public long insertRespuesta_Video(Respuesta respuesta){
        return respuestaDAO.insertRespuesta_Video(respuesta);
    }




}
