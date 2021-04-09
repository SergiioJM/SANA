package es.uji.ei1027.SANA.model;

import java.sql.Date;
import java.sql.Time;

public class Reserva {
    String identificador;
    Time hora;
    Date fecha;
    int numeroPersonas;
    Estado estado;
    Zona zona;

    public Reserva(String identificador, Time hora, Date fecha, int numeroPersonas, Estado estado, Zona zona) {
        this.identificador = identificador;
        this.hora = hora;
        this.fecha = fecha;
        this.numeroPersonas = numeroPersonas;
        this.estado = estado;
        this.zona = zona;
    }

    public Reserva() {

    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "identificador='" + identificador + '\'' +
                ", hora=" + hora +
                ", fecha=" + fecha +
                ", numeroPersonas=" + numeroPersonas +
                ", estado='" + estado + '\'' +
                ", zona=" + zona +
                '}';
    }
}
