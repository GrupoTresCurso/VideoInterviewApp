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
    private List<Video> listaVideos;
    private Formulario cuestionarioSatisfaccion;
    private String mensaje;
    private List<Candidato> listaCandidatos;

    public Entrevista() {
    }

    public Entrevista(int idEntrevista, String nombreEntrevista, String nombrePuesto, boolean tieneVideoIntro, List<Formulario> formularios, List<Video> listaVideos, Formulario cuestionarioSatisfaccion, String mensaje, List<Candidato> listaCandidatos) {
        this.idEntrevista = idEntrevista;
        this.nombreEntrevista = nombreEntrevista;
        this.nombrePuesto = nombrePuesto;
        this.tieneVideoIntro = tieneVideoIntro;
        this.formularios = formularios;
        this.listaVideos = listaVideos;
        this.cuestionarioSatisfaccion = cuestionarioSatisfaccion;
        this.mensaje = mensaje;
        this.listaCandidatos = listaCandidatos;
    }

    public Entrevista(String nombreEntrevista, String nombrePuesto, boolean tieneVideoIntro, List<Formulario> formularios, List<Video> listaVideos, Formulario cuestionarioSatisfaccion, String mensaje, List<Candidato> listaCandidatos) {
        this.nombreEntrevista = nombreEntrevista;
        this.nombrePuesto = nombrePuesto;
        this.tieneVideoIntro = tieneVideoIntro;
        this.formularios = formularios;
        this.listaVideos = listaVideos;
        this.cuestionarioSatisfaccion = cuestionarioSatisfaccion;
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

    public List<Video> getListaVideos() {
        return listaVideos;
    }

    public void setListaVideos(List<Video> listaVideos) {
        this.listaVideos = listaVideos;
    }

    public Formulario getCuestionarioSatisfaccion() {
        return cuestionarioSatisfaccion;
    }

    public void setCuestionarioSatisfaccion(Formulario cuestionarioSatisfaccion) {
        this.cuestionarioSatisfaccion = cuestionarioSatisfaccion;
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
