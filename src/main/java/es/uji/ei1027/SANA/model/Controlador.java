package es.uji.ei1027.SANA.model;

import java.util.Date;

public class Controlador {
    String identificador;
    String nombre;
    String direccion;
    String email;
    int telefono;
    Date fechaInicio;
    Date fechaFin;

    public Controlador(String identificador, String nombre, String direccion, String email, int telefono, Date fechaInicio, Date fechaFin) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Controlador() {

    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public Date getDataInicio() {
        return fechaInicio;
    }

    public Date getDateFin() {
        return fechaFin;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "ControlStaff{" +
                "identificador='" + identificador + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                ", dataInicio=" + fechaInicio +
                ", dateFin=" + fechaFin +
                '}';
    }

    public void setDataInicio(Date dataInicio) {
        this.fechaInicio = dataInicio;
    }

    public void setDateFin(Date dateFin) {
        this.fechaFin = dateFin;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}

