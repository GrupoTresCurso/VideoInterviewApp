package com.example.tictum.appcandidatos.business;

import com.example.tictum.appcandidatos.beans.Archivo;
import com.example.tictum.appcandidatos.sqliteDAO.ArchivoDAO;

import java.util.ArrayList;

/**
 * Created by Usuario on 16/03/2017.
 */

public class ArchivoManager {
    private ArchivoDAO archivoDAO = new ArchivoDAO();

    public long insertArchivo(Archivo archivo){
        return archivoDAO.insertArchivo(archivo);
    }

    public Archivo getArchivo(int idArchivo){
        return archivoDAO.getArchivo(idArchivo);
    }

    public ArrayList<Archivo> getAllArchivos(){
        return archivoDAO.getAllArchivos();
    }




}
