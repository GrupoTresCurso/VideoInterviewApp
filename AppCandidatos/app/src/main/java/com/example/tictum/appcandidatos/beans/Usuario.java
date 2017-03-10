package com.example.tictum.appcandidatos.beans;




public class Usuario {

    
    private int idUsuario;
    private String nombreUsuario;
    private String password;
    private boolean isAdministrador = false;
    private boolean isReclutador = false;
    private boolean isResponsableContratacion = false;
    private boolean isCandidato = false;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String password, boolean isAdministrador, boolean isReclutador, boolean isResponsableContratacion, boolean isCandidato) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.isAdministrador = isAdministrador;
        this.isReclutador = isReclutador;
        this.isResponsableContratacion = isResponsableContratacion;
        this.isCandidato = isCandidato;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrador() {
        return isAdministrador;
    }

    public void setAdministrador(boolean administrador) {
        isAdministrador = administrador;
    }

    public boolean isReclutador() {
        return isReclutador;
    }

    public void setReclutador(boolean reclutador) {
        isReclutador = reclutador;
    }

    public boolean isResponsableContratacion() {
        return isResponsableContratacion;
    }

    public void setResponsableContratacion(boolean responsableContratacion) {
        isResponsableContratacion = responsableContratacion;
    }

    public boolean isCandidato() {
        return isCandidato;
    }

    public void setCandidato(boolean candidato) {
        isCandidato = candidato;
    }
}
