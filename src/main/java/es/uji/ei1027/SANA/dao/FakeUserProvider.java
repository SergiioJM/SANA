package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Ciudadano;
import es.uji.ei1027.SANA.model.ResponsableMunicipio;
import es.uji.ei1027.SANA.model.UserDetails;
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
}
