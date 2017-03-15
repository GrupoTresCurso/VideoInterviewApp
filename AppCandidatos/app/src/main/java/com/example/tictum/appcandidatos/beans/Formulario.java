package com.example.tictum.appcandidatos.beans;


import java.util.List;


public class Formulario {

    
    private int idFormulario;
    private String nombreFormulario;
    private List<Pregunta> preguntas;
    private int posicionEnEntrevista;

    public Formulario() {
    }

    public Formulario(int idFormulario, String nombreFormulario, List<Pregunta> preguntas, int posicionEnEntrevista) {
        this.idFormulario = idFormulario;
        this.nombreFormulario = nombreFormulario;
        this.preguntas = preguntas;
        this.posicionEnEntrevista = posicionEnEntrevista;
    }

    public Formulario(String nombreFormulario, List<Pregunta> preguntas, int posicionEnEntrevista) {
        this.nombreFormulario = nombreFormulario;
        this.preguntas = preguntas;
        this.posicionEnEntrevista = posicionEnEntrevista;
    }

    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public String getNombreFormulario() {
        return nombreFormulario;
    }

    public void setNombreFormulario(String nombreFormulario) {
        this.nombreFormulario = nombreFormulario;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public int getPosicionEnEntrevista() {
        return posicionEnEntrevista;
    }

    public void setPosicionEnEntrevista(int posicionEnEntrevista) {
        this.posicionEnEntrevista = posicionEnEntrevista;
    }
}
