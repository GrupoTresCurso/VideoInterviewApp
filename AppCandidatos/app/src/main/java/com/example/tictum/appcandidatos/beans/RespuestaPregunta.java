package com.example.tictum.appcandidatos.beans;


public class RespuestaPregunta {

    private int idRespuestaPregunta;
    private int idPregunta;
    private String respuestaString;

    public RespuestaPregunta(int idRespuestaPregunta, int idPregunta, String respuestaString) {
        this.idRespuestaPregunta = idRespuestaPregunta;
        this.idPregunta = idPregunta;
        this.respuestaString = respuestaString;
    }

    public RespuestaPregunta(int idPregunta, String respuestaString) {
        this.idPregunta = idPregunta;
        this.respuestaString = respuestaString;
    }

    public int getIdRespuestaPregunta() {
        return idRespuestaPregunta;
    }

    public void setIdRespuestaPregunta(int idRespuestaPregunta) {
        this.idRespuestaPregunta = idRespuestaPregunta;
    }
}
