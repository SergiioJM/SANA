package es.uji.ei1027.SANA.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServicioTemporal {
    String nombre;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaFin;
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime horaInicio;
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime horaFin;
    int idArea;
    String tipoServicio;
    String nomArea;


    public ServicioTemporal() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getNomArea() {
        return nomArea;
    }

    public void setNomArea(String nomArea) {
        this.nomArea = nomArea;
    }

    @Override
    public String toString() {
        return "ServicioTemporal{" +
                "nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", area=" + idArea +
                '}';
    }
}
