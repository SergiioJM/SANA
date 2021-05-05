package es.uji.ei1027.SANA.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Reserva {
    private static AtomicInteger cod = new AtomicInteger(0);
    String identificador = "R"+ cod;
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime hora;
    LocalDate fecha;
    int numeroPersonas;
    String estado;
    String zona;
    String ciudadano;

    public Reserva(LocalTime hora, LocalDate fecha, int numeroPersonas, String estado,String zona, String ciudadano) {
        cod.getAndIncrement();
        this.hora = hora;
        this.fecha = fecha;
        this.numeroPersonas = numeroPersonas;
        this.estado = estado;
        this.zona = zona;
        this.ciudadano = ciudadano;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getZona() { return zona;}

    public void setZona(String zona) { this.zona = zona;}

    public String getCiudadano(){
        return ciudadano;
    }

    public void setCiudadano(String ciudadano){
        this.ciudadano = ciudadano;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "identificador='" + identificador + '\'' +
                ", hora=" + hora +
                ", fecha=" + fecha +
                ", numeroPersonas=" + numeroPersonas +
                ", estado='" + estado +
                ", zona='" + zona +
                ", ciudadano='" + ciudadano + '\'' +
                '}';
    }
}
