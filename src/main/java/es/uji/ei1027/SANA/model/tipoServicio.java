package es.uji.ei1027.SANA.model;

public class tipoServicio {
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "tipoServicio{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
