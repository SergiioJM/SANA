package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.UserDetails;
import java.util.Collection;

public interface UserDao {
    UserDetails loadUserByUsername(String username, String password);
    Collection<UserDetails> listAllUsers();
}
