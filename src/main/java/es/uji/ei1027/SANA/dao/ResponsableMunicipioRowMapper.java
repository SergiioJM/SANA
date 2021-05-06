package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.ResponsableMunicipio;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class ResponsableMunicipioRowMapper implements RowMapper<ResponsableMunicipio> {
    public ResponsableMunicipio mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResponsableMunicipio responsableMunicipio = new ResponsableMunicipio();
        responsableMunicipio.setNombre(rs.getString("nombre"));
        responsableMunicipio.setEmail(rs.getString("email"));
        responsableMunicipio.setNumerotelefono(rs.getInt("numerotelefono"));
        responsableMunicipio.setfechaInicio(rs.getObject("fechaInicio", LocalDate.class));
        responsableMunicipio.setfechaFin(rs.getObject("fechaFin", LocalDate.class));
        responsableMunicipio.setIdentificador(rs.getInt("identificador"));
        responsableMunicipio.setMunicipio(rs.getString("municipio"));
        return responsableMunicipio;
    }
}
