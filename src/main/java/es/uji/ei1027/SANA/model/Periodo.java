package es.uji.ei1027.SANA.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Periodo {
    String identificador;
    LocalDate fechaInicio;
    LocalDate fechaFin;
    LocalTime horaInicio;
    LocalTime horaFin;
    String idArea;

    public Periodo(String identificador, LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio, LocalTime horaFin, String idArea) {
        this.identificador = identificador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idArea = idArea;
    }

    public Periodo() {

    }

    public String getIdentificador(){return identificador;}

    public void setIdentificador(String identificador){this.identificador=identificador;}

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

    public String getIdArea() {
        return idArea;
    }

    public void setIdArea(String idArea) {
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
