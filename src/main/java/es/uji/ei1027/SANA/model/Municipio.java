package es.uji.ei1027.SANA.model;

public class Municipio {
    String cif;
    String nombre;
    String direccion;
    String email;
    Integer telefono;

    public Municipio() {

    }

    public Municipio(String cif, String nombre, String direccion, String email, Integer telefono) {
        this.cif = cif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    public String getcif() {
        return cif;
    }

    public void setcif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Municipio{" +
                "cif='" + cif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}