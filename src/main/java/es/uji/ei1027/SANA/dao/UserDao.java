package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.ResponsableMedioAmbiente;
import es.uji.ei1027.SANA.model.UserDetails;

public interface UserDao {
    UserDetails loadUserByUsername(String nif, String contraseña, CiudadanoDAO ciudadanoDAO);
    UserDetails loadUserByUsername2(String email, String password,String cp, ResponsableMunicipioDAO responsableMunicipioDAO);
    UserDetails loadUserByUsername3(String emailControlador, String password,ControladorDAO controladorDAO);
    UserDetails loadUserByUsername4(String usuario, String password, ResponsableMedioAmbienteDAO responsableMedioAmbienteDAO);

}
