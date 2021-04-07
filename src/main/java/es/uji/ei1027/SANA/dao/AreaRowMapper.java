package es.uji.ei1027.SANA.dao;


import es.uji.ei1027.SANA.model.Area;
import es.uji.ei1027.SANA.model.Municipality;
import es.uji.ei1027.SANA.model.PhysicalCharacteristics;
import es.uji.ei1027.SANA.model.TipoRestriccion;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class AreaRowMapper implements RowMapper<Area> {

    public Area mapRow(ResultSet rs, int rowNum) throws SQLException {
        Area area = new Area();
        area.setIdArea(rs.getString("idArea"));
        area.setNombre(rs.getString("nombre"));
        area.setDescripcion(rs.getString("descripcion"));
        area.setCaracteristicas((PhysicalCharacteristics) rs.getObject(rs.getString("caracteristicas")));
        area.setLocalizacion(rs.getString("localizacion"));
        area.setTipoAcceso((TipoRestriccion) rs.getObject(rs.getString("tipoAcceso")));
        area.setMunicipio((Municipality) rs.getObject(rs.getString("municipio")));
        return area;
    }
}