package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Zona;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class ZonaRowMapper implements RowMapper<Zona> {
    public Zona mapRow(ResultSet rs, int rowNum) throws SQLException{
        Zona zona = new Zona();
        zona.setIdentificador(String.valueOf(rs.getInt("identificador")));
        zona.setCapacidad(rs.getInt("capacidad"));
        zona.setIdArea(rs.getInt("idArea"));
        return zona;
    }
}
