package es.uji.ei1027.SANA.dao;


import es.uji.ei1027.SANA.model.ServicioTemporal;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public final class ServicioTemporalRowMapper implements RowMapper<ServicioTemporal> {
    public ServicioTemporal mapRow(ResultSet rs, int rowNum) throws SQLException {
        ServicioTemporal servicioTemporal= new ServicioTemporal();
        servicioTemporal.setNombre(rs.getString("nombre"));
        servicioTemporal.setfechaInicio(rs.getObject("fechaInicio", LocalDate.class));
        servicioTemporal.setfechaFin(rs.getObject("fechaFin", LocalDate.class));
        servicioTemporal.setHoraInicio(rs.getObject("horaInicio", LocalTime.class));
        servicioTemporal.setHoraFin(rs.getObject("horaFin", LocalTime.class));
        servicioTemporal.setArea(rs.getString("nombreArea"));
        return servicioTemporal;
    }
}
