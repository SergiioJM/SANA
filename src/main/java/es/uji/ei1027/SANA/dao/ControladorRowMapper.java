package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Controlador;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class ControladorRowMapper implements RowMapper<Controlador> {
    public Controlador mapRow(ResultSet rs, int rowNum) throws SQLException {
        Controlador controlStaff = new Controlador();
        controlStaff.setIdentificador(rs.getInt("identificador"));
        controlStaff.setNombre(rs.getString("nombre"));
        controlStaff.setDireccion(rs.getString("direccion"));
        controlStaff.setEmail(rs.getString("email"));
        controlStaff.setPassword(rs.getString("password"));
        controlStaff.setTelefono(rs.getInt("telefono"));
        controlStaff.setFecha(rs.getObject("fechaInicio", LocalDate.class));
        controlStaff.setFechaFin(rs.getObject("fechaFin", LocalDate.class));
        return controlStaff;
    }
}
