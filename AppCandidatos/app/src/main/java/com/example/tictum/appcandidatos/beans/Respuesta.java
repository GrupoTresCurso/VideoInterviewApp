package beans;

import java.util.List;

public class Respuesta {
	
	private int idRespuesta;
	private int idEntrevista;
	private int idCandidato;
	private List<Video> videosRespuestas;
	private List<String> respuestas;
	private List<Archivo> adjuntos;
	
	
	
	public Respuesta() {
		super();
	}



	public Respuesta(int idRespuesta, int idEntrevista, int idCandidato, List<Video> videosRespuestas,
			List<String> respuestas, List<Archivo> adjuntos) {
		super();
		this.idRespuesta = idRespuesta;
		this.idEntrevista = idEntrevista;
		this.idCandidato = idCandidato;
		this.videosRespuestas = videosRespuestas;
		this.respuestas = respuestas;
		this.adjuntos = adjuntos;
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



	public List<Archivo> getAdjuntos() {
		return adjuntos;
	}



	public void setAdjuntos(List<Archivo> adjuntos) {
		this.adjuntos = adjuntos;
	}
	
	
	
	
	
	
	

}
