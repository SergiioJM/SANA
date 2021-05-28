package es.uji.ei1027.SANA.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class FranjaHoraria {
    int identificador;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaFin;
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime horaInicio;
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime horaFin;
    int idArea;
    String nombrearea;

    public FranjaHoraria() {
    }

    public int getIdentificador(){return identificador;}

    public void setIdentificador(int identificador){this.identificador=identificador;}

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

    public int getidArea() {
        return idArea;
    }

    public void setidArea(int idArea) {
        this.idArea = idArea;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombrearea() {
        return nombrearea;
    }

    public void setNombrearea(String nombrearea) {
        this.nombrearea = nombrearea;
    }

    @Override
    public String toString() {
        return "Periodo{" +
                "identificador='" + identificador + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", idArea='" + idArea + '\'' +
                ", nombreArea= " + nombrearea +
                '}';
    }
}
