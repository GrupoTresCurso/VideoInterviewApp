package beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Formulario {

    @Id
    @GeneratedValue
    private int idFormulario;
    private String nombreFormulario;
    @ManyToMany
    private List<Pregunta> preguntas;

    public Formulario() {
    }

    public Formulario(String nombreFormulario, ArrayList<Pregunta> preguntas) {
        this.nombreFormulario = nombreFormulario;
        this.preguntas = preguntas;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public void setNombreFormulario(String nombreFormulario) {
        this.nombreFormulario = nombreFormulario;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public int getIdFormulario() {
        return idFormulario;
    }

    public String getNombreFormulario() {
        return nombreFormulario;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }
}
