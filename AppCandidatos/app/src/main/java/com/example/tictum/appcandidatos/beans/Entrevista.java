package com.example.tictum.appcandidatos.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Entrevista implements Serializable{

    private int idEntrevista;
    private String nombreEntrevista;
    private String nombrePuesto;
    private boolean tieneVideoIntro = false;
    private List<Formulario> formularios;
    private List<Video> videoTransicion;
    private List<Video> preguntasVideo;
    private Formulario cuestionarioSatifaccion;
    private String mensaje;

    public Entrevista() {
    }

    public Entrevista(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public Entrevista(String nombreEntrevista, String nombrePuesto, boolean tieneVideoIntro, ArrayList<Formulario> formularios, ArrayList<Video> videoTransicion, ArrayList<Video> preguntaVideo, Formulario cuestionarioSatifaccion, String mensaje) {
        this.nombreEntrevista = nombreEntrevista;
        this.nombrePuesto = nombrePuesto;
        this.tieneVideoIntro = tieneVideoIntro;
        this.formularios = formularios;
        this.videoTransicion = videoTransicion;
        this.preguntasVideo = preguntaVideo;
        this.cuestionarioSatifaccion = cuestionarioSatifaccion;
        this.mensaje = mensaje;
    }

    public int getIdEntrevista() {
        return idEntrevista;
    }

    public boolean isTieneVideoIntro() {
        return tieneVideoIntro;
    }

    public List<Formulario> getFormularios() {
        return formularios;
    }

    public List<Video> getVideoTransicion() {
        return videoTransicion;
    }

    public List<Video> getPreguntaVideo() {
        return preguntasVideo;
    }

    public Formulario getCuestionarioSatifaccion() {
        return cuestionarioSatifaccion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setIdEntrevista(int idEntrevista) {
        this.idEntrevista = idEntrevista;
    }

    public void setTieneVideoIntro(boolean tieneVideoIntro) {
        this.tieneVideoIntro = tieneVideoIntro;
    }

    public void setFormularios(ArrayList<Formulario> formularios) {
        this.formularios = formularios;
    }

    public void setVideoTransicion(ArrayList<Video> videoTransicion) {
        this.videoTransicion = videoTransicion;
    }

    public void setPreguntaVideo(ArrayList<Video> preguntaVideo) {
        this.preguntasVideo = preguntaVideo;
    }

    public void setCuestionarioSatifaccion(Formulario cuestionarioSatifaccion) {
        this.cuestionarioSatifaccion = cuestionarioSatifaccion;
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

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
