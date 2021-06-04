package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.ResponsableMedioAmbiente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ResponsableMedioAmbienteRowMapper implements RowMapper<ResponsableMedioAmbiente> {
    public ResponsableMedioAmbiente mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResponsableMedioAmbiente responsableMedioAmbiente = new ResponsableMedioAmbiente();
        responsableMedioAmbiente.setUsuario(rs.getString("usuario"));
        responsableMedioAmbiente.setPassword(rs.getString("password"));
        return responsableMedioAmbiente;
    }
}
