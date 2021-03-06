package com.example.tictum.appcandidatos.beans;


import java.io.Serializable;

public class Video implements Serializable{

    private int idVideo;
    private String nombreVideo;
    private String linkVideo;
    private int posicionEnEntrevista;
    private String tipoVideo;

    public Video() {
    }

    public Video(int idVideo, String nombreVideo, String linkVideo, int posicionEnEntrevista, String tipoVideo) {
        this.idVideo = idVideo;
        this.nombreVideo = nombreVideo;
        this.linkVideo = linkVideo;
        this.posicionEnEntrevista = posicionEnEntrevista;
        this.tipoVideo = tipoVideo;
    }

    public Video(String nombreVideo, String linkVideo, int posicionEnEntrevista, String tipoVideo) {
        this.nombreVideo = nombreVideo;
        this.linkVideo = linkVideo;
        this.posicionEnEntrevista = posicionEnEntrevista;
        this.tipoVideo = tipoVideo;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public String getNombreVideo() {
        return nombreVideo;
    }

    public void setNombreVideo(String nombreVideo) {
        this.nombreVideo = nombreVideo;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public int getPosicionEnEntrevista() {
        return posicionEnEntrevista;
    }

    public void setPosicionEnEntrevista(int posicionEnEntrevista) {
        this.posicionEnEntrevista = posicionEnEntrevista;
    }

    public String getTipoVideo() {
        return tipoVideo;
    }

    public void setTipoVideo(String tipoVideo) {
        this.tipoVideo = tipoVideo;
    }
}
