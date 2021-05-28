package es.uji.ei1027.SANA.dao;


import es.uji.ei1027.SANA.model.FranjaHoraria;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public final class FranjaHorariaRowMapper implements RowMapper<FranjaHoraria> {
    public FranjaHoraria mapRow(ResultSet rs, int rowNum) throws SQLException {
        FranjaHoraria franjaHoraria = new FranjaHoraria();
        franjaHoraria.setIdentificador(rs.getInt("identificador"));
        franjaHoraria.setfechaInicio(rs.getObject("fechaInicio", LocalDate.class));
        franjaHoraria.setfechaFin(rs.getObject("fechaFin", LocalDate.class));
        franjaHoraria.setHoraInicio(rs.getObject("horaInicio", LocalTime.class));
        franjaHoraria.setHoraFin(rs.getObject("horaFin", LocalTime.class));
        franjaHoraria.setidArea(rs.getInt("idArea"));
        return franjaHoraria;
    }
}
