package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Estado;
import es.uji.ei1027.SANA.model.Municipio;
import es.uji.ei1027.SANA.model.Reserva;
import es.uji.ei1027.SANA.model.Zona;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;

public class ReservaRowMapper implements RowMapper<Reserva> {
    public Reserva mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reserva reserva= new Reserva();
        reserva.setIdentificador(rs.getString("identificador"));
        reserva.setHora(rs.getObject("hora", Time.class));
        reserva.setFecha(rs.getObject("fecha", Date.class));
        reserva.setNumeroPersonas(rs.getInt("numeroPersonas"));
        reserva.setEstado(rs.getObject("estado", Estado.class));
        reserva.setZona(rs.getObject("zona", Zona.class));
        return  reserva;
    }
}
