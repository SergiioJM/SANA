package es.uji.ei1027.SANA.model;

public class ResponsableMunicipio {
    private String nombre;
    private String email;
    private int numerotelefono;
    java.time.LocalDate fechaInicioo;
    java.time.LocalDate fechaFin;
    private String identificador;
    private String municipio;


    public ResponsableMunicipio() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumerotelefono() {
        return numerotelefono;
    }

    public void setNumerotelefono(int numerotelefono) {
        this.numerotelefono = numerotelefono;
    }

    public java.time.LocalDate getfechaInicio() {
        return fechaInicioo;
    }

    public void setfechaInicio(java.time.LocalDate fechaInicio) {
        this.fechaInicioo = fechaInicio;
    }

    public java.time.LocalDate getfechaFin() {
        return fechaFin;
    }

    public void setfechaFin(java.time.LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "ResponsableMunicipio{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelefono=" + numerotelefono +
                ", fechaInicio=" + fechaInicioo +
                ", fechaFin=" + fechaFin +
                ", identificador='" + identificador + '\'' +
                ", municipio=" + municipio +
                '}';
    }
}
