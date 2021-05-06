package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Reserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public final class ReservaRowMapper implements RowMapper<Reserva> {

    public Reserva mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reserva reserva= new Reserva();
        reserva.setIdentificador(rs.getInt("identificador"));
        reserva.setHora(rs.getObject("hora", LocalTime.class));
        reserva.setFecha(rs.getObject("fecha", LocalDate.class));
        reserva.setNumeroPersonas(rs.getInt("numeroPersonas"));
        reserva.setEstado( rs.getString("estado"));
        reserva.setZona(rs.getString("zona"));
        reserva.setCiudadano(rs.getString("ciudadano"));
        return reserva;
    }
}
