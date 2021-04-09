package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Municipio;
import es.uji.ei1027.SANA.model.ResponsableMunicipio;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ResponsableMunicipioRowMapper implements RowMapper<ResponsableMunicipio> {
    public ResponsableMunicipio mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResponsableMunicipio responsableMunicipio = new ResponsableMunicipio();
        responsableMunicipio.setNombre(rs.getString("nombre"));
        responsableMunicipio.setEmail(rs.getString("email"));
        responsableMunicipio.setNumeroTelefono(rs.getInt("numeroTelefono"));
        responsableMunicipio.setfechaInicio(rs.getObject("fechaInicio", Date.class));
        responsableMunicipio.setfechaFin(rs.getObject("fechaFin", Date.class));
        responsableMunicipio.setIdentificador(rs.getString("identificador"));
        responsableMunicipio.setMunicipio(rs.getObject("municipio", Municipio.class));
        return responsableMunicipio;
    }
}
