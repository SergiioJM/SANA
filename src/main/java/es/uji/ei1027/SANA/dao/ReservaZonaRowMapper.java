package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.ReservaZona;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservaZonaRowMapper implements RowMapper<ReservaZona> {
    public ReservaZona mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReservaZona reservaZona= new ReservaZona();
        reservaZona.setReserva(rs.getInt("id_reserva"));
        reservaZona.setZona(rs.getString("id_zona"));
        return reservaZona;

    }
}
