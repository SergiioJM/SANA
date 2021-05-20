package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Servicio;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ServicioRowMapper implements RowMapper<Servicio> {
    public Servicio mapRow(ResultSet rs, int rowNum) throws SQLException {
        Servicio servicio= new Servicio();
        servicio.setNombre(rs.getString("nombre"));
        servicio.setDescripcion(rs.getString("descripcion"));
        servicio.setEstado(rs.getString("estado"));
        servicio.setArea(rs.getInt("nombreArea"));
        servicio.setTipoServicio(rs.getString("tipoServicio"));
        return servicio;
    }
}
