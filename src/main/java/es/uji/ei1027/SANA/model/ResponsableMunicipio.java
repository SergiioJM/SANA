package es.uji.ei1027.SANA.model;

import java.time.LocalDate;

public class ResponsableMunicipio {
    String nombre;
    String email;
    int numeroTelefono;
    LocalDate fechaInicio;
    LocalDate fechaFin;
    String identificador;
    String municipio;

    public ResponsableMunicipio(String nombre, String email, int numeroTelefono, LocalDate fechaInicio, LocalDate fechaFin, String identificador, String municipio) {
        this.nombre = nombre;
        this.email = email;
        this.numeroTelefono = numeroTelefono;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.identificador = identificador;
        this.municipio = municipio;
    }

    public ResponsableMunicipio() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public LocalDate getfechaInicio() {
        return fechaInicio;
    }

    public void setfechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getfechaFin() {
        return fechaFin;
    }

    public void setfechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "ResponsableMunicipio{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelefono=" + numeroTelefono +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", identificador='" + identificador + '\'' +
                ", municipio=" + municipio +
                '}';
    }
}
