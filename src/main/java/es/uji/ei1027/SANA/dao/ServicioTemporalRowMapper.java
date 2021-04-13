package es.uji.ei1027.SANA.dao;


import es.uji.ei1027.SANA.model.ServicioTemporal;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public final class ServicioTemporalRowMapper implements RowMapper<ServicioTemporal> {
    public ServicioTemporal mapRow(ResultSet rs, int rowNum) throws SQLException {
        ServicioTemporal servicioTemporal= new ServicioTemporal();
        servicioTemporal.setNombre(rs.getString("nombre"));
        servicioTemporal.setfechaInicio(rs.getObject("fechaInicio", LocalDate.class));
        servicioTemporal.setfechaFin(rs.getObject("fechaFin", LocalDate.class));
        Time t1 = rs.getTime("horaInicio");
        servicioTemporal.setHoraInicio(t1.toLocalTime());
        Time t12 = rs.getTime("horaFin");
        servicioTemporal.setHoraFin(t12.toLocalTime());
        servicioTemporal.setNombreArea(rs.getString("nombreArea"));
        return servicioTemporal;
    }
}
