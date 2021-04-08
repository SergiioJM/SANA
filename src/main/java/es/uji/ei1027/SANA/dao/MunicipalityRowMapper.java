package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Municipality;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class MunicipalityRowMapper implements RowMapper<Municipality> {
    public Municipality mapRow(ResultSet rs, int rowNum) throws SQLException {
        Municipality municipality = new Municipality();
        municipality.setCIF(rs.getString("cif"));
        municipality.setNombre(rs.getString("nombre"));
        municipality.setDireccion(rs.getString("direccion"));
        municipality.setEmail(rs.getString("email"));
        municipality.setTelefono(rs.getInt("telefono"));
        return municipality;
    }
}
