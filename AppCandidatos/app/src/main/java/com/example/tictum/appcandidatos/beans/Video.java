package beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Video {

    @Id
    @GeneratedValue
    private int idVideo;
    private String nombreVideo;
    private String linkVideo;
    private int posicionEnEntrevista;
    private String tipoVideo;

    public Video() {
    }

    public Video(String nombreVideo, String linkVideo, int posicionEnEntrevista, String tipoVideo) {
        this.nombreVideo = nombreVideo;
        this.linkVideo = linkVideo;
        this.posicionEnEntrevista = posicionEnEntrevista;
        this.tipoVideo = tipoVideo;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public String getNombreVideo() {
        return nombreVideo;
    }

    public void setNombreVideo(String nombreVideo) {
        this.nombreVideo = nombreVideo;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public int getPosicionEnEntrevista() {
        return posicionEnEntrevista;
    }

    public void setPosicionEnEntrevista(int posicionEnEntrevista) {
        this.posicionEnEntrevista = posicionEnEntrevista;
    }

    public String getTipoVideo() {
        return tipoVideo;
    }

    public void setTipoVideo(String tipoVideo) {
        this.tipoVideo = tipoVideo;
    }
}
