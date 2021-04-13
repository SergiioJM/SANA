package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.PeriodoAsignado;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class PeriodoAsignadoRowMapper implements RowMapper<PeriodoAsignado> {
    public PeriodoAsignado mapRow(ResultSet rs, int rowNum) throws SQLException {
        PeriodoAsignado periodoAsignado = new PeriodoAsignado();
        periodoAsignado.setIdentificador(rs.getString("identificador"));
        periodoAsignado.setFechaInicio(rs.getObject("fechaInicio", LocalDate.class));
        periodoAsignado.setFechaFin(rs.getObject("fechaFin", LocalDate.class));
        periodoAsignado.setControlador(rs.getString("controlador"));
        periodoAsignado.setArea(rs.getString("area"));
        return periodoAsignado;
    }
}
