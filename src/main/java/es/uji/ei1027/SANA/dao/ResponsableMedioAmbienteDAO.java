package es.uji.ei1027.SANA.dao;


import es.uji.ei1027.SANA.model.ResponsableMedioAmbiente;
import es.uji.ei1027.SANA.model.ResponsableMunicipio;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResponsableMedioAmbienteDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

    public void addResponsableMedioAmbiente(ResponsableMedioAmbiente responsableMedioAmbiente) {
        jdbcTemplate.update("INSERT INTO ResponsableMedioAmbiente VALUES(?,?)",
                responsableMedioAmbiente.getUsuario(),passwordEncryptor.encryptPassword(responsableMedioAmbiente.getPassword()));
    }

    public List<ResponsableMedioAmbiente> getResponsablesMedioAmbiente(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM ResponsableMedioAmbiente",
                    new ResponsableMedioAmbienteRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
