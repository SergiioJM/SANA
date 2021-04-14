package es.uji.ei1027.SANA.model;

import java.time.LocalDate;

public class PeriodoAsignado {
    String identificador;
    LocalDate fechaInicio;
    LocalDate fechaFin;
    String nombreControlador;
    String nombreArea;

    public PeriodoAsignado(String identificador, LocalDate fechaInicio, LocalDate fechaFin, String nombreControlador, String nombreArea) {
        this.identificador = identificador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombreControlador = nombreControlador;
        this.nombreArea = nombreArea;
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
        return nombreControlador;
    }

    public String getArea() {
        return nombreArea;
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

    public void setControlador(String nombreControlador) {
        this.nombreControlador = nombreControlador;
    }

    public void setArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    @Override
    public String toString() {
        return "PeriodoAsignado{" +
                "identificador='" + identificador + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", codigoControlador='" + nombreControlador + '\'' +
                ", codigoArea='" + nombreArea + '\'' +
                '}';
    }
}
