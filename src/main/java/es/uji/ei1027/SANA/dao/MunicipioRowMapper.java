package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Municipio;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class MunicipioRowMapper implements RowMapper<Municipio> {

    public Municipio mapRow(ResultSet rs, int rowNum) throws SQLException {
        Municipio municipality = new Municipio();
        municipality.setCp(rs.getString("cif"));
        municipality.setNombre(rs.getString("nombre"));
        municipality.setDireccion(rs.getString("direccion"));
        municipality.setEmail(rs.getString("email"));
        municipality.setTelefono(rs.getInt("telefono"));
        return municipality;
    }
}
