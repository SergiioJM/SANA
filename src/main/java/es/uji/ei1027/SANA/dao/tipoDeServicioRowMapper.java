package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Zona;
import es.uji.ei1027.SANA.model.tipoServicio;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class tipoDeServicioRowMapper implements RowMapper<tipoServicio> {
    public tipoServicio mapRow(ResultSet rs, int rowNum) throws SQLException {
        tipoServicio tipoServicio= new tipoServicio();
        tipoServicio.setNombre(rs.getString("nombre"));
        return tipoServicio;
    }

}
