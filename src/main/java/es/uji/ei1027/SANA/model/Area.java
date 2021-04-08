package es.uji.ei1027.SANA.model;

public class Area {
    String idArea;
    String nombre;
    String descripcion;
    CaracteristicasFisicas caracteristicas;
    String localizacion;
    TipoAcceso tipoAcceso;
    Municipio municipio;

    public Area() {

    }

    public Area(String idArea, String nombre, String descripcion, CaracteristicasFisicas caracteristicas, String localizacion, TipoAcceso tipoAcceso, Municipio municipio) {
        this.idArea = idArea;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteristicas = caracteristicas;
        this.localizacion = localizacion;
        this.tipoAcceso = tipoAcceso;
        this.municipio = municipio;
    }

    public CaracteristicasFisicas getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(CaracteristicasFisicas caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public TipoAcceso getTipoAcceso() {
        return tipoAcceso;
    }

    public void setTipoAcceso(TipoAcceso tipoAcceso) {
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

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
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

