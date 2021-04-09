package es.uji.ei1027.SANA.model;

import java.util.Date;

public class PeriodoAsignado {
    String identificador;
    Date fechaInicio;
    Date fechaFin;
    Controlador controlador;
    Area area;

    public PeriodoAsignado(String identificador, Date fechaInicio, Date fechaFin, Controlador controlador, Area area) {
        this.identificador = identificador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.controlador = controlador;
        this.area = area;
    }

    public PeriodoAsignado() {

    }

    public String getIdentificador() {
        return identificador;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Controlador getControlador() {
        return controlador;
    }

    public Area getArea() {
        return area;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "PeriodoAsignado{" +
                "identificador='" + identificador + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", controlador=" + controlador +
                ", area=" + area +
                '}';
    }
}
