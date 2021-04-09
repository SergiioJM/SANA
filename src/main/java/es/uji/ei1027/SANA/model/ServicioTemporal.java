package es.uji.ei1027.SANA.model;

import java.sql.Time;
import java.util.Date;

public class ServicioTemporal {
    String nombre;
    Date fechaInicio;
    Date fechaFin;
    Time horaInicio;
    Time horaFin;
    Area area;

    public ServicioTemporal(String nombre, Date fechaInicio, Date fechaFin, Time horaInicio, Time horaFin, Area area) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.area = area;
    }

    public ServicioTemporal() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "ServicioTemporal{" +
                "nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", area=" + area +
                '}';
    }
}
