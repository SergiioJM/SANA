package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Area;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class AreaRowMapper implements RowMapper<Area> {

    public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
        Area area = new Area();
        area.setIdArea(rs.getInt("idArea"));
        area.setNombre(rs.getString("nombre"));
        area.setDescripcion(rs.getString("descripcion"));
        area.setCaracteristicas(rs.getString("caracteristicasfisicas"));
        area.setLocalizacion(rs.getString("localizacion"));
        area.setTipoAcceso(rs.getString("tipoAcceso"));
        area.setMunicipio(rs.getString("municipio"));
        return area;
    }
}