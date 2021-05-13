package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Ciudadano;
import es.uji.ei1027.SANA.model.UserDetails;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class FakeUserProvider implements UserDao {
    final Map<String, UserDetails> knownUsers = new HashMap<String, UserDetails>();

    public FakeUserProvider() {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        UserDetails userAlice = new UserDetails();
        userAlice.setNif("alice");
        userAlice.setPassword(passwordEncryptor.encryptPassword("alice"));
        knownUsers.put("alice", userAlice);

        UserDetails userBob = new UserDetails();
        userBob.setNif("bob");
        userBob.setPassword(passwordEncryptor.encryptPassword("bob"));
        knownUsers.put("bob", userBob);

    }

    @Override
    public UserDetails loadUserByUsername(String nif, String password) {
        UserDetails user = new UserDetails();
        user.setNif(nif);
        user.setPassword(password);
        if (user.getPassword().equals(password)) {
            return user;
        }
        else {
            return null;
        }
    }
}
