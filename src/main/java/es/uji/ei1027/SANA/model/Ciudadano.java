package es.uji.ei1027.SANA.model;

import java.time.LocalDate;

public class Ciudadano {
    String nombre;
    String nif;
    String email;
    String residencia;
    LocalDate fechaRegistro;
    String password;


    public Ciudadano(String nombre, String nif, String email, String residencia, LocalDate fechaRegistro, String password) {
        this.nombre = nombre;
        this.nif = nif;
        this.email = email;
        this.residencia = residencia;
        this.fechaRegistro = fechaRegistro;
        this.password = password;

    }

    public Ciudadano() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Ciudadano{" +
                "nombre='" + nombre + '\'' +
                ", NIF='" + nif + '\'' +
                ", email='" + email + '\'' +
                ", residencia='" + residencia + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
