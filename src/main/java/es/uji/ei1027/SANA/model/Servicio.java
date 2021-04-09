package es.uji.ei1027.SANA.model;

public class Servicio {
    String nombre;
    String descricpcion;
    String estado;
    Area area;

    public Servicio(String nombre, String descricpcion, String estado, Area area) {
        this.nombre = nombre;
        this.descricpcion = descricpcion;
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

    public String getDescricpcion() {
        return descricpcion;
    }

    public void setDescricpcion(String descricpcion) {
        this.descricpcion = descricpcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "nombre='" + nombre + '\'' +
                ", descricpcion='" + descricpcion + '\'' +
                ", estado='" + estado + '\'' +
                ", area=" + area +
                '}';
    }
}

