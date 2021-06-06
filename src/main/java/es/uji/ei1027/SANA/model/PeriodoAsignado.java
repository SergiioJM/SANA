package es.uji.ei1027.SANA.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PeriodoAsignado {
    int identificador;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaFin;
    int nombreControlador;
    int nombreArea;

    public PeriodoAsignado() {

    }

    public int getIdentificador() {
        return identificador;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getControlador() {
        return nombreControlador;
    }

    public int getArea() {
        return nombreArea;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setControlador(int nombreControlador) {
        this.nombreControlador = nombreControlador;
    }

    public void setArea(int nombreArea) {
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
