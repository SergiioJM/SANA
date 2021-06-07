package es.uji.ei1027.SANA.model;

public class UserDetails {
    String nif;
    String email;
    String password;
    String municipio;
    String usuario;
    Integer area;


    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getIdentificador() {
        return usuario;
    }

    public void setIdentificador(String usuario) {
        this.usuario = usuario;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "nif='" + nif + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}