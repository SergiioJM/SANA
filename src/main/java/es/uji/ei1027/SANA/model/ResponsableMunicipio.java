package es.uji.ei1027.SANA.model;

import java.time.LocalDate;

public class ResponsableMunicipio {
    private String nombre;
    private String email;
    private int numeroTelefono;
    private LocalDate fechainicio;
    private LocalDate fechafin;
    private String identificador;
    private String municipio;

    public ResponsableMunicipio(String nombre, String email, int numeroTelefono, LocalDate fechaInicio, LocalDate fechafin, String identificador, String municipio) {
        this.nombre = nombre;
        this.email = email;
        this.numeroTelefono = numeroTelefono;
        this.fechainicio = fechaInicio;
        this.fechafin = fechafin;
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
        return fechainicio;
    }

    public void setfechaInicio(LocalDate fechaInicio) {
        this.fechainicio = fechaInicio;
    }

    public LocalDate getfechaFin() {
        return fechafin;
    }

    public void setfechaFin(LocalDate fechaFin) {
        this.fechafin = fechaFin;
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
                ", fechaInicio=" + fechainicio +
                ", fechaFin=" + fechafin +
                ", identificador='" + identificador + '\'' +
                ", municipio=" + municipio +
                '}';
    }
}
