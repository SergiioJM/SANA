package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Ciudadano;
import es.uji.ei1027.SANA.model.Reserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class CiudadanoRowMapper implements RowMapper<Ciudadano> {
    public Ciudadano mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ciudadano ciudadano= new Ciudadano();
        ciudadano.setNombre(rs.getString("nombre"));
        ciudadano.setNIF(rs.getString("NIF"));
        ciudadano.setEmail(rs.getString("email"));
        ciudadano.setResidencia(rs.getString("residencia"));
        ciudadano.setFechaRegistro(rs.getObject("fechaRegistro", Date.class));
        ciudadano.setReserva(rs.getObject("reserva", Reserva.class));
        return ciudadano;
    }
}
