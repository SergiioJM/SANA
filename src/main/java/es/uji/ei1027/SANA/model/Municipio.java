package es.uji.ei1027.SANA.model;

public class Municipio {
    String cp;
    String nombre;
    String direccion;
    String email;
    int telefono;

    public Municipio() {

    }

    public Municipio(String cp, String nombre, String direccion, String email, Integer telefono) {
        this.cp = Municipio.this.cp;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    public String getcp() {
        return cp;
    }

    public void setcp(String cp) {
        this.cp = Municipio.this.cp;
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
                "cp='" + cp + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}