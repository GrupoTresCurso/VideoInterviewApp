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
    private Formulario cuestionarioSatisfaccion;
    private String mensaje;
    private List<Candidato> listaCandidatos;

    public Entrevista() {
    }

    // para pruebas se puede borrar despues
    public Entrevista(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public Entrevista(int idEntrevista, String nombreEntrevista, String nombrePuesto, boolean tieneVideoIntro, List<Formulario> formularios, List<Video> videoTransicion, List<Video> preguntasVideo, Formulario cuestionarioSatifaccion, String mensaje, List<Candidato> listaCandidatos) {
        this.idEntrevista = idEntrevista;
        this.nombreEntrevista = nombreEntrevista;
        this.nombrePuesto = nombrePuesto;
        this.tieneVideoIntro = tieneVideoIntro;
        this.formularios = formularios;
        this.videoTransicion = videoTransicion;
        this.preguntasVideo = preguntasVideo;
        this.cuestionarioSatisfaccion = cuestionarioSatifaccion;
        this.mensaje = mensaje;
        this.listaCandidatos = listaCandidatos;
    }

    public Entrevista(String nombreEntrevista, String nombrePuesto, boolean tieneVideoIntro, List<Formulario> formularios, List<Video> videoTransicion, List<Video> preguntasVideo, Formulario cuestionarioSatifaccion, String mensaje, List<Candidato> listaCandidatos) {
        this.nombreEntrevista = nombreEntrevista;
        this.nombrePuesto = nombrePuesto;
        this.tieneVideoIntro = tieneVideoIntro;
        this.formularios = formularios;
        this.videoTransicion = videoTransicion;
        this.preguntasVideo = preguntasVideo;
        this.cuestionarioSatisfaccion = cuestionarioSatifaccion;
        this.mensaje = mensaje;
        this.listaCandidatos = listaCandidatos;
    }

    public int getIdEntrevista() {
        return idEntrevista;
    }

    public void setIdEntrevista(int idEntrevista) {
        this.idEntrevista = idEntrevista;
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

    public boolean TieneVideoIntro() {
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

    public List<Video> getPreguntasVideo() {
        return preguntasVideo;
    }

    public void setPreguntasVideo(List<Video> preguntasVideo) {
        this.preguntasVideo = preguntasVideo;
    }

    public Formulario getCuestionarioSatifaccion() {
        return cuestionarioSatisfaccion;
    }

    public void setCuestionarioSatifaccion(Formulario cuestionarioSatifaccion) {
        this.cuestionarioSatisfaccion = cuestionarioSatifaccion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Candidato> getListaCandidatos() {
        return listaCandidatos;
    }

    public void setListaCandidatos(List<Candidato> listaCandidatos) {
        this.listaCandidatos = listaCandidatos;
    }
}
