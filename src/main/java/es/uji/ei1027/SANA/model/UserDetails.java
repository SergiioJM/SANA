package es.uji.ei1027.SANA.model;

public class UserDetails {
    String nif;
    String contraseña;
    String email;
    String password;


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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "nif='" + nif + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}