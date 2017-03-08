package com.example.tictum.appcandidatos.beans;



/**
 * Created by TictumManaña on 08/03/2017.
 */


public class Adjunto {

    private int idAdjunto;
    private String etiquetaAdjunto;
    private int posicionEnEntrevista;

    public Adjunto() {
    }

    public Adjunto(int idAdjunto, String etiquetaAdjunto, int posicionEnEntrevista) {
        this.idAdjunto = idAdjunto;
        this.etiquetaAdjunto = etiquetaAdjunto;
        this.posicionEnEntrevista = posicionEnEntrevista;
    }

    public void setIdAdjunto(int idAdjunto) {
        this.idAdjunto = idAdjunto;
    }

    public void setEtiquetaAdjunto(String etiquetaAdjunto) {
        this.etiquetaAdjunto = etiquetaAdjunto;
    }

    public void setPosicionEnEntrevista(int posicionEnEntrevista) {
        this.posicionEnEntrevista = posicionEnEntrevista;
    }

    public int getIdAdjunto() {
        return idAdjunto;
    }

    public String getEtiquetaAdjunto() {
        return etiquetaAdjunto;
    }

    public int getPosicionEnEntrevista() {
        return posicionEnEntrevista;
    }
}
