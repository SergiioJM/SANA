package es.uji.ei1027.SANA.model;

public class Servicio {
    String nombre;
    String descripcion;
    String estado;
    int area;
    String tipoServicio;
    String nomArea;

    public Servicio(String nombre, String descripcion, String estado, int area) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.area = area;
    }

    public Servicio() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getNomArea() {
        return nomArea;
    }

    public void setNomArea(String nomArea) {
        this.nomArea = nomArea;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "nombre='" + nombre + '\'' +
                ", descricpcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", area=" + area +
                '}';
    }
}

