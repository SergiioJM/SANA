package es.uji.ei1027.SANA.model;

import java.util.Date;

public class Ciudadano {
    String nombre;
    String NIF;
    String email;
    String residencia;
    Date fechaRegistro;
    Reserva reserva;

    public Ciudadano(String nombre, String NIF, String email, String residencia, Date fechaRegistro, Reserva reserva) {
        this.nombre = nombre;
        this.NIF = NIF;
        this.email = email;
        this.residencia = residencia;
        this.fechaRegistro = fechaRegistro;
        this.reserva = reserva;
    }

    public Ciudadano() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public String toString() {
        return "Ciudadano{" +
                "nombre='" + nombre + '\'' +
                ", NIF='" + NIF + '\'' +
                ", email='" + email + '\'' +
                ", residencia='" + residencia + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", reserva=" + reserva +
                '}';
    }
}
