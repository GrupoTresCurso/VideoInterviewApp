package com.example.tictum.appcandidatos.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Respuesta implements Serializable {

	private int idRespuesta;
	private int idEntrevista;
	private int idCandidato;
	private List<Video> videosRespuestas;
	private String respuestasString;
	private List<String> respuestas;
	private List<Archivo> archivosAdjuntos;
	private float notaCandidato;

	public Respuesta() {}


	//Constructores con idRespuesta
	public Respuesta(int idRespuesta, int idEntrevista, int idCandidato, List<Video> videosRespuestas, List<String> respuestas, List<Archivo> archivosAdjuntos, float notaCandidato) {

		this.idRespuesta = idRespuesta;
		this.idEntrevista = idEntrevista;
		this.idCandidato = idCandidato;
		this.videosRespuestas = videosRespuestas;
		this.respuestas = respuestas;
		this.respuestasString = HelperBeans.getStringFromArray(respuestas);
		this.archivosAdjuntos = archivosAdjuntos;
		this.notaCandidato = notaCandidato;
	}


	public Respuesta(int idRespuesta, int idEntrevista, int idCandidato, List<Video> videosRespuestas,
					 String respuestasString, List<Archivo> archivosAdjuntos, float notaCandidato) {
		this.idRespuesta = idRespuesta;
		this.idEntrevista = idEntrevista;
		this.idCandidato = idCandidato;
		this.videosRespuestas = videosRespuestas;
		this.respuestasString = respuestasString;
		this.respuestas = HelperBeans.getArrayFromString(respuestasString);
		this.archivosAdjuntos = archivosAdjuntos;
		this.notaCandidato = notaCandidato;
	}

	//Constructores sin idRespuesta

	public Respuesta(int idEntrevista, int idCandidato, List<Video> videosRespuestas, List<String> respuestas, List<Archivo> archivosAdjuntos, float notaCandidato) {

		this.idEntrevista = idEntrevista;
		this.idCandidato = idCandidato;
		this.videosRespuestas = videosRespuestas;
		this.respuestas = respuestas;
		this.respuestasString = HelperBeans.getStringFromArray(respuestas);
		this.archivosAdjuntos = archivosAdjuntos;
		this.notaCandidato = notaCandidato;
	}

	public Respuesta(int idEntrevista, int idCandidato, List<Video> videosRespuestas,
					 String respuestasString, List<Archivo> archivosAdjuntos, float notaCandidato) {
		this.idEntrevista = idEntrevista;
		this.idCandidato = idCandidato;
		this.videosRespuestas = videosRespuestas;
		this.respuestasString = respuestasString;
		this.respuestas = HelperBeans.getArrayFromString(respuestasString);
		this.archivosAdjuntos = archivosAdjuntos;
		this.notaCandidato = notaCandidato;
	}

	public int getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public int getIdEntrevista() {
		return idEntrevista;
	}

	public void setIdEntrevista(int idEntrevista) {
		this.idEntrevista = idEntrevista;
	}

	public int getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(int idCandidato) {
		this.idCandidato = idCandidato;
	}

	public List<Video> getVideosRespuestas() {
		return videosRespuestas;
	}

	public void setVideosRespuestas(List<Video> videosRespuestas) {
		this.videosRespuestas = videosRespuestas;
	}

	public List<String> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<String> respuestas) {
		this.respuestas = respuestas;
	}

	public List<Archivo> getArchivosAdjuntos() {
		return archivosAdjuntos;
	}

	public void setArchivosAdjuntos(List<Archivo> archivosAdjuntos) {
		this.archivosAdjuntos = archivosAdjuntos;
	}

	public float getNotaCandidato() {
		return notaCandidato;
	}

	public void setNotaCandidato(float notaCandidato) {
		this.notaCandidato = notaCandidato;
	}

	public void addRespuesta (String respuestaString){
		List<String> nuevaListaRespuestas = new ArrayList<>();
		nuevaListaRespuestas.add(respuestaString);
		this.setRespuestas(nuevaListaRespuestas);
	}
}
