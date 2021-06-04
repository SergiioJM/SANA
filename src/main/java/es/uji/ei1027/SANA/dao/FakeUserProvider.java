package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.*;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class FakeUserProvider implements UserDao {
    final Map<String, UserDetails> knownUsers = new HashMap<String, UserDetails>();

    @Override
    public UserDetails loadUserByUsername(String nif, String password, CiudadanoDAO ciudadanoDAO) {
        List<Ciudadano> ciudadanos = ciudadanoDAO.getCiudadanos();
        for (int i = 0; i < ciudadanos.size(); i++){
            UserDetails usuario = new UserDetails();
            usuario.setNif(ciudadanos.get(i).getNif());
            usuario.setPassword(ciudadanos.get(i).getPassword());
            knownUsers.put(ciudadanos.get(i).getNif(),usuario);
            System.out.println(ciudadanos.get(i).getNif() + ciudadanos.get(i).getPassword());
        }
        UserDetails user = knownUsers.get(nif.trim());
        if (user == null)
            return null;
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if (passwordEncryptor.checkPassword(password, user.getPassword())) {
            return user;
        }
        else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername2(String email, String password, String cp, ResponsableMunicipioDAO responsableMunicipioDAO) {
        List<ResponsableMunicipio> responsables = responsableMunicipioDAO.getResponsablesMunicipios();
        for (int i = 0; i < responsables.size(); i++) {
            UserDetails usuario = new UserDetails();
            usuario.setEmail(responsables.get(i).getEmail());
            usuario.setPassword(responsables.get(i).getPassword());
            usuario.setMunicipio(responsables.get(i).getMunicipio());
            knownUsers.put(responsables.get(i).getEmail(), usuario);
        }
        UserDetails user = knownUsers.get(email.trim());
        if (user == null)
            return null;
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if (passwordEncryptor.checkPassword(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername3(String emailControlador, String password, ControladorDAO controladorDAO) {
        List<Controlador> coontroladores = controladorDAO.getControladores();
        for (int i = 0; i < coontroladores.size(); i++) {
            UserDetails usuario = new UserDetails();
            usuario.setEmail(coontroladores.get(i).getEmail());
            usuario.setPassword(coontroladores.get(i).getPassword());
            knownUsers.put(String.valueOf(coontroladores.get(i).getEmail()), usuario);
        }
        UserDetails user = knownUsers.get(emailControlador.trim());
        if (user == null)
            return null;
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if (passwordEncryptor.checkPassword(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername4(String nomUsuario, String password, ResponsableMedioAmbienteDAO responsableMedioAmbienteDAO) {
        List<ResponsableMedioAmbiente> medioAmbientes = responsableMedioAmbienteDAO.getResponsablesMedioAmbiente();
        for (int i = 0; i < medioAmbientes.size(); i++) {
            UserDetails usuario = new UserDetails();
            usuario.setEmail(medioAmbientes.get(i).getUsuario());
            usuario.setPassword(medioAmbientes.get(i).getPassword());
            knownUsers.put(medioAmbientes.get(i).getUsuario(), usuario);
        }
        UserDetails user = knownUsers.get(nomUsuario.trim());
        if (user == null)
            return null;
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        if (passwordEncryptor.checkPassword(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}
