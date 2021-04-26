package es.uji.ei1027.SANA.model;

public class Area {
    String idArea;
    String nombre;
    String descripcion;
    String caracteristicas; //Creo que el fallo es esto
    String localizacion;
    String tipoAcceso;  //Creo que el fallo es esto
    String municipio;

    public Area() {

    }

    public Area(String idArea, String nombre, String descripcion, String caracteristicas, String localizacion, String tipoAcceso, String municipio) {
        this.idArea = idArea;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteristicas = caracteristicas;
        this.localizacion = localizacion;
        this.tipoAcceso = tipoAcceso;
        this.municipio = municipio;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getTipoAcceso() {
        return tipoAcceso;
    }

    public void setTipoAcceso(String tipoAcceso) {
        this.tipoAcceso = tipoAcceso;
    }


    public String getIdArea() {
        return idArea;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
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

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }



    @Override
    public String toString() {
        return "Area{" +
                "idArea ='" + idArea + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", caracteristicas=" + caracteristicas +
                ", localizacion='" + localizacion + '\'' +
                ", tipoAcceso=" + tipoAcceso +
                ", municipio=" + municipio +
                '}';
    }
}

