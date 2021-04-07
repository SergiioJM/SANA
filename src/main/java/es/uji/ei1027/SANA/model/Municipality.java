package es.uji.ei1027.SANA.model;

public class Municipality {
    String CIF;
    String nombre;
    String direccion;
    String email;
    Integer telefono;

    public Municipality(String CIF, String nombre, String direccion, String email, Integer telefono) {
        this.CIF = CIF;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    public Municipality() {

    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
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
        return "Municipality{" +
                "CIF='" + CIF + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}