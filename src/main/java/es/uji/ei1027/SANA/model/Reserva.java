package es.uji.ei1027.SANA.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    String identificador;
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime hora;
    LocalDate fecha;
    int numeroPersonas;
    Estado estado; //Creo que el fallo es esto
    String zona;

    public Reserva(String identificador, LocalTime hora, LocalDate fecha, int numeroPersonas, Estado estado, String zona) {
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

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
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
