package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Ciudadano;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class CiudadanoRowMapper implements RowMapper<Ciudadano> {
    public Ciudadano mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ciudadano ciudadano= new Ciudadano();
        ciudadano.setNombre(rs.getString("nombre"));
        ciudadano.setNif(rs.getString("nif"));
        ciudadano.setEmail(rs.getString("email"));
        ciudadano.setResidencia(rs.getString("residencia"));
        ciudadano.setFechaRegistro(rs.getObject("fechaRegistro", LocalDate.class));
        ciudadano.setReserva(rs.getString("reserva"));
        return ciudadano;
    }
}
