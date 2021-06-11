package es.uji.ei1027.SANA.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ResponsableMunicipio {
    private String nombre;
    private String email;
    String password;
    private int numerotelefono;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaFin;
    private int identificador;
    private String municipio;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumerotelefono() {
        return numerotelefono;
    }

    public void setNumerotelefono(int numerotelefono) {
        this.numerotelefono = numerotelefono;
    }

    public LocalDate getfechaInicio() {
        return fechaInicio;
    }

    public void setfechaInicio(java.time.LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getfechaFin() {
        return fechaFin;
    }

    public void setfechaFin(java.time.LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
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
                ", numeroTelefono=" + numerotelefono +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", identificador='" + identificador + '\'' +
                ", municipio=" + municipio +
                '}';
    }
}
