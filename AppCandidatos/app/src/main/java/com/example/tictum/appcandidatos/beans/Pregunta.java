package com.example.tictum.appcandidatos.beans;

import java.io.Serializable;

public class Pregunta implements Serializable {

    private int idPregunta;
    private String labelPregunta;
    private String tipoPregunta;
    private String[] opciones;
    private int posicionEnFormulario;
    private boolean favorito;

    public Pregunta() {
    }

    public Pregunta(String labelPregunta, String tipoPregunta, String[] opciones, int posicionEnFormulario) {
        this.labelPregunta = labelPregunta;
        this.tipoPregunta = tipoPregunta;
        this.opciones = opciones;
        this.posicionEnFormulario = posicionEnFormulario;
    }

    public Pregunta(int idPregunta, String labelPregunta, String tipoPregunta, String[] opciones, int posicionEnFormulario) {
        this.idPregunta = idPregunta;
        this.labelPregunta = labelPregunta;
        this.tipoPregunta = tipoPregunta;
        this.opciones = opciones;
        this.posicionEnFormulario = posicionEnFormulario;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getLabelPregunta() {
        return labelPregunta;
    }

    public void setLabelPregunta(String labelPregunta) {
        this.labelPregunta = labelPregunta;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }

    public int getPosicionEnFormulario() {
        return posicionEnFormulario;
    }

    public void setPosicionEnFormulario(int posicionEnFormulario) {
        this.posicionEnFormulario = posicionEnFormulario;
    }
}
