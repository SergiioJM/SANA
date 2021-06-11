package es.uji.ei1027.SANA.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Reserva {
    int identificador;
    String hora;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fecha;
    int numeroPersonas;
    String estado;
    String ciudadano;
    List<String> listreserva;
    String area;
    String municipio;


    public Reserva(String hora, LocalDate fecha, int numeroPersonas, String estado, String zona, String ciudadano) {
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "Detalles de la Reserva: " + "\n" +
                "Identificador: " + identificador + "\n" +
                "Fecha: " + fecha + "\n" +
                "Hora: " + hora + "\n" +
                "Numero de Personas: " + numeroPersonas + "\n" +
                "Estado de la reserva: " + estado + "\n" +
                "DNI de la Reserva: " + ciudadano;
    }
}
