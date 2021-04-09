package es.uji.ei1027.SANA.model;

import java.sql.Time;
import java.util.Date;

public class Periodo {
    Date fechaInicio;
    Date fechaFin;
    Time horaInicio;
    Time horaFin;
    Area idArea;

    public Periodo(Date fechaInicio, Date fechaFin, Time horaInicio, Time horaFin, Area idArea) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idArea = idArea;
    }

    public Periodo() {

    }

    public Date getfechaInicio() {
        return fechaInicio;
    }

    public void setfechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getfechaFin() {
        return fechaFin;
    }

    public void setfechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    @Override
    public String toString() {
        return "Periodo{" +
                "fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", idArea=" + idArea +
                '}';
    }
}
