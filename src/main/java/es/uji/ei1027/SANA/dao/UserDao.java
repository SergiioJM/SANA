package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.UserDetails;

public interface UserDao {
    UserDetails loadUserByUsername(String nif, String password, CiudadanoDAO ciudadanoDAO);
}
