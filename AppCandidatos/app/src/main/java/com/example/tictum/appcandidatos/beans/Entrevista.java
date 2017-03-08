package com.example.tictum.appcandidatos.beans;


import java.io.Serializable;
import java.util.ArrayList;



public class Entrevista implements Serializable {

    private int idEntrevista;
    private String nombreEntrevista;
    private String nombrePuesto;
    private boolean tieneVideoIntro;
    private ArrayList<Formulario> formularios;
    private ArrayList<Video> videoTransicion;
    private ArrayList<Video> preguntaVideo;
    private Formulario cuestionarioSatifaccion;
    private ArrayList<Adjunto> adjunto;
    private String mensaje;

    public Entrevista() {
    }

    public Entrevista(int idEntrevista, boolean tieneVideoIntro, ArrayList<Formulario> formularios, ArrayList<Video> videoTransicion, ArrayList<Video> preguntaVideo, Formulario cuestionarioSatifaccion, ArrayList<Adjunto> adjunto, String mensaje) {
        this.idEntrevista = idEntrevista;
        this.tieneVideoIntro = tieneVideoIntro;
        this.formularios = formularios;
        this.videoTransicion = videoTransicion;
        this.preguntaVideo = preguntaVideo;
        this.cuestionarioSatifaccion = cuestionarioSatifaccion;
        this.adjunto = adjunto;
        this.mensaje = mensaje;
    }

    public int getIdEntrevista() {
        return idEntrevista;
    }

    public void setIdEntrevista(int idEntrevista) {
        this.idEntrevista = idEntrevista;
    }

    public boolean isTieneVideoIntro() {
        return tieneVideoIntro;
    }

    public void setTieneVideoIntro(boolean tieneVideoIntro) {
        this.tieneVideoIntro = tieneVideoIntro;
    }

    public ArrayList<Formulario> getFormularios() {
        return formularios;
    }

    public void setFormularios(ArrayList<Formulario> formularios) {
        this.formularios = formularios;
    }

    public ArrayList<Video> getVideoTransicion() {
        return videoTransicion;
    }

    public void setVideoTransicion(ArrayList<Video> videoTransicion) {
        this.videoTransicion = videoTransicion;
    }

    public ArrayList<Video> getPreguntaVideo() {
        return preguntaVideo;
    }

    public void setPreguntaVideo(ArrayList<Video> preguntaVideo) {
        this.preguntaVideo = preguntaVideo;
    }

    public Formulario getCuestionarioSatifaccion() {
        return cuestionarioSatifaccion;
    }

    public void setCuestionarioSatifaccion(Formulario cuestionarioSatifaccion) {
        this.cuestionarioSatifaccion = cuestionarioSatifaccion;
    }

    public ArrayList<Adjunto> getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(ArrayList<Adjunto> adjunto) {
        this.adjunto = adjunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombreEntrevista() {
        return nombreEntrevista;
    }

    public void setNombreEntrevista(String nombreEntrevista) {
        this.nombreEntrevista = nombreEntrevista;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }
}
