package es.uji.ei1027.SANA.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Reserva {
    int identificador;
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime hora;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fecha;
    int numeroPersonas;
    String estado;
    String ciudadano;
    List<String> listreserva;

    public Reserva(LocalTime hora, LocalDate fecha, int numeroPersonas, String estado,String zona, String ciudadano) {
        this.hora = hora;
        this.fecha = fecha;
        this.numeroPersonas = numeroPersonas;
        this.estado = estado;
        this.ciudadano = ciudadano;
    }

    public Reserva() {
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
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

    public void setFecha(LocalDate fecha) { this.fecha = fecha;    }

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

    public String getCiudadano(){
        return ciudadano;
    }

    public void setCiudadano(String ciudadano){
        this.ciudadano = ciudadano;
    }

    public List<String> getListreserva() {
        return listreserva;
    }

    public void setListreserva(List<String> listreserva) {
        this.listreserva = listreserva;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "identificador='" + identificador + '\'' +
                ", hora=" + hora +
                ", fecha=" + fecha +
                ", numeroPersonas=" + numeroPersonas +
                ", estado='" + estado +
                ", ciudadano='" + ciudadano + '\'' +
                '}';
    }
}
