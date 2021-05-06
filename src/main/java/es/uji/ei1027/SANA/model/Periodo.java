package es.uji.ei1027.SANA.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Periodo {
    int identificador;
    LocalDate fechaInicio;
    LocalDate fechaFin;
    LocalTime horaInicio;
    LocalTime horaFin;
    int idArea;

    public Periodo() {

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

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
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
                '}';
    }
}
