package com.example.tictum.appcandidatos.beans;



public class Candidato {

    private int idCandidato;
    private String nombre;
    private String apellidos;
    private String dni;
    private String numeroTelefono;
    private boolean entrevistaRealizada;
    private Integer nota;
    private String cv;

    public Candidato() {
    }

    public Candidato(int idCandidato, String nombre, String apellidos, String dni, String numeroTelefono, boolean entrevistaRealizada, Integer nota, String cv) {
        this.idCandidato = idCandidato;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.numeroTelefono = numeroTelefono;
        this.entrevistaRealizada = entrevistaRealizada;
        this.nota = null;
        this.cv = cv;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public void setEntrevistaRealizada(boolean entrevistaRealizada) {
        this.entrevistaRealizada = entrevistaRealizada;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public boolean isEntrevistaRealizada() {
        return entrevistaRealizada;
    }

    public Integer getNota() {
        return nota;
    }

    public String getCv() {
        return cv;
    }
}
