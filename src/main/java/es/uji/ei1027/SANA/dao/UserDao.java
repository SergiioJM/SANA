package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.UserDetails;
import java.util.Collection;

public interface UserDao {
    UserDetails loadUserByUsername(String nif, String password);
    Collection<UserDetails> listAllUsers();
}
