package es.uji.ei1027.SANA.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class ZonaReservada {
    int identificador;
    int idarea;
    String idzona;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate fecha;
    String franja;

    public ZonaReservada( int idarea, String idzona, LocalDate fecha, String franja) {
        this.idarea = idarea;
        this.idzona = idzona;
        this.fecha = fecha;
        this.franja = franja;
    }

    public ZonaReservada() {
    }


    @Override
    public String toString(){
        return "ZonaReservada{" +
                "identificador='" + identificador + '\'' +
                ", idarea=" + idarea +
                ", direccion =" + idzona +
                ", fecha =" + fecha +
                ", franja =" + franja +
                '}';
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getIdarea() {
        return idarea;
    }

    public void setIdarea(int idarea) {
        this.idarea = idarea;
    }

    public String getIdzona() {
        return idzona;
    }

    public void setIdzona(String idzona) {
        this.idzona = idzona;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getFranja() {
        return franja;
    }

    public void setFranja(String franja) {
        this.franja = franja;
    }
}
