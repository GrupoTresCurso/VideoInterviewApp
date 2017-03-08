package com.example.tictum.appcandidatos.beans;


import java.util.ArrayList;



public class Formulario {

    private int idFormulario;
    private String nombreFormulario;
    private ArrayList<Pregunta> preguntas;

    public Formulario() {
    }

    public Formulario(int idFormulario, String nombreFormulario, ArrayList<Pregunta> preguntas) {
        this.idFormulario = idFormulario;
        this.nombreFormulario = nombreFormulario;
        this.preguntas = preguntas;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public void setNombreFormulario(String nombreFormulario) {
        this.nombreFormulario = nombreFormulario;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public int getIdFormulario() {
        return idFormulario;
    }

    public String getNombreFormulario() {
        return nombreFormulario;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }
}
