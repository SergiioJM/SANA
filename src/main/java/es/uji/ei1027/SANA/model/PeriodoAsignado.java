package es.uji.ei1027.SANA.model;

import java.time.LocalDate;

public class PeriodoAsignado {
    String identificador;
    LocalDate fechaInicio;
    LocalDate fechaFin;
    String controlador;
    String area;

    public PeriodoAsignado(String identificador, LocalDate fechaInicio, LocalDate fechaFin, String controlador, String area) {
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public String getControlador() {
        return controlador;
    }

    public String getArea() {
        return area;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
    }

    public void setArea(String area) {
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
