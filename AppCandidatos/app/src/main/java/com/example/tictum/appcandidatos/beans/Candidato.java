package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Candidato {

    @Id
    @GeneratedValue
    private int idCandidato;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private int edad;
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isHombre = false;
    private String numeroTelefono;
    @Column(columnDefinition = "TINYINT(1)")
    private boolean entrevistaRealizada = false;
    private Integer nota;
    private String cv;

    public Candidato() {
    }

    public Candidato(String nombre, String apellidos, String dni, String email, int edad, boolean isHombre, String numeroTelefono, boolean entrevistaRealizada, Integer nota, String cv) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.edad = edad;
        this.isHombre = isHombre;
        this.numeroTelefono = numeroTelefono;
        this.entrevistaRealizada = entrevistaRealizada;
        this.nota = nota;
        this.cv = cv;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isHombre() {
        return isHombre;
    }

    public void setHombre(boolean hombre) {
        isHombre = hombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public boolean isEntrevistaRealizada() {
        return entrevistaRealizada;
    }

    public void setEntrevistaRealizada(boolean entrevistaRealizada) {
        this.entrevistaRealizada = entrevistaRealizada;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
}
