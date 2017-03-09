package com.example.tictum.appcandidatos.beans;


import java.io.Serializable;
import java.util.List;


public class Entrevista implements Serializable {

    private int idEntrevista;
    private String nombreEntrevista;
    private String nombrePuesto;
    private boolean tieneVideoIntro;
    private List<Formulario> formularios;
    private List<Video> videoTransicion;
    private List<Video> preguntaVideo;
    private Formulario cuestionarioSatifaccion;
    private List<Adjunto> adjunto;
    private String mensaje;

    public Entrevista() {
    }

    public Entrevista(int idEntrevista, boolean tieneVideoIntro, List<Formulario> formularios, List<Video> videoTransicion, List<Video> preguntaVideo, Formulario cuestionarioSatifaccion, List<Adjunto> adjunto, String mensaje) {
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

    public List<Formulario> getFormularios() {
        return formularios;
    }

    public void setFormularios(List<Formulario> formularios) {
        this.formularios = formularios;
    }

    public List<Video> getVideoTransicion() {
        return videoTransicion;
    }

    public void setVideoTransicion(List<Video> videoTransicion) {
        this.videoTransicion = videoTransicion;
    }

    public List<Video> getPreguntaVideo() {
        return preguntaVideo;
    }

    public void setPreguntaVideo(List<Video> preguntaVideo) {
        this.preguntaVideo = preguntaVideo;
    }

    public Formulario getCuestionarioSatifaccion() {
        return cuestionarioSatifaccion;
    }

    public void setCuestionarioSatifaccion(Formulario cuestionarioSatifaccion) {
        this.cuestionarioSatifaccion = cuestionarioSatifaccion;
    }

    public List<Adjunto> getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(List<Adjunto> adjunto) {
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
