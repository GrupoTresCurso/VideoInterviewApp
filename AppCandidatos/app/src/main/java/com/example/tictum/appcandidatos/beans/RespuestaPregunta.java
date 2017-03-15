package com.example.tictum.appcandidatos.beans;

/**
 * Created by Usuario on 15/03/2017.
 */

public class RespuestaPregunta {

    private int idRespuestaPregunta;
    private int idPreunta;
    private String respuestaString;

    public RespuestaPregunta(int idRespuestaPregunta, int idPreunta, String respuestaString) {
        this.idRespuestaPregunta = idRespuestaPregunta;
        this.idPreunta = idPreunta;
        this.respuestaString = respuestaString;
    }

    public RespuestaPregunta(int idPreunta, String respuestaString) {
        this.idPreunta = idPreunta;
        this.respuestaString = respuestaString;
    }

    public int getIdRespuestaPregunta() {
        return idRespuestaPregunta;
    }

    public void setIdRespuestaPregunta(int idRespuestaPregunta) {
        this.idRespuestaPregunta = idRespuestaPregunta;
    }
}
