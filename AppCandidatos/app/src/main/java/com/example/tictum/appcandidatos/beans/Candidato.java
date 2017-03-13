package com.example.tictum.appcandidatos.beans;


public class Candidato {

        private int idCandidato;
        private String nombre;
        private String apellidos;
        private String dni;
        private String email;
        private int edad;
        private boolean isHombre = false;
        private String numeroTelefono;
        private String rutaCurriculum;

        public Candidato() {
        }

        public Candidato(int idCandidato, String nombre, String apellidos, String dni, String email, int edad,
                         boolean isHombre, String numeroTelefono, String cv) {
            super();
            this.idCandidato = idCandidato;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.dni = dni;
            this.email = email;
            this.edad = edad;
            this.isHombre = isHombre;
            this.numeroTelefono = numeroTelefono;
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

        public void setHombre(boolean isHombre) {
            this.isHombre = isHombre;
        }

        public String getNumeroTelefono() {
            return numeroTelefono;
        }

        public void setNumeroTelefono(String numeroTelefono) {
            this.numeroTelefono = numeroTelefono;
        }

        public String getCv() {
            return cv;
        }

        public void setCv(String cv) {
            this.cv = cv;
        }


    }
