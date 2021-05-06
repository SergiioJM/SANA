package es.uji.ei1027.SANA.dao;


import es.uji.ei1027.SANA.model.Periodo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public final class PeriodoRowMapper implements RowMapper<Periodo> {
    public Periodo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Periodo periodo = new Periodo();
        periodo.setIdentificador(rs.getInt("identificador"));
        periodo.setfechaInicio(rs.getObject("fechaInicio", LocalDate.class));
        periodo.setfechaFin(rs.getObject("fechaFin", LocalDate.class));
        periodo.setHoraInicio(rs.getObject("horaInicio", LocalTime.class));
        periodo.setHoraFin(rs.getObject("horaFin", LocalTime.class));
        periodo.setIdArea(rs.getInt("idArea"));
        return periodo;
    }
}
