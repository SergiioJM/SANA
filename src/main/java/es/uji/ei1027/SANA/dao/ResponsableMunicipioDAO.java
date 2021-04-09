package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.ResponsableMunicipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResponsableMunicipioDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void addResponsableMunicipio(ResponsableMunicipio responsableMunicipio) {
        jdbcTemplate.update("INSERT INTO ResponsableMunicipio VALUES(?,?,?,?,?,?,?)",
                responsableMunicipio.getNombre(),responsableMunicipio.getEmail(),responsableMunicipio.getNumeroTelefono(),responsableMunicipio.getfechaInicio(),responsableMunicipio.getfechaFin(),responsableMunicipio.getIdentificador(),responsableMunicipio.getMunicipio());
    }

    public void updateResponsableMunicipio(ResponsableMunicipio responsableMunicipio) {
        jdbcTemplate.update("UPDATE ResponsableMunicipio SET nombre =?, email =?, numeroTelefono =?, fechaInicio =?, fechaFin =?, municipio =? WHERE identificador =?",
                responsableMunicipio.getNombre(),responsableMunicipio.getEmail(),responsableMunicipio.getNumeroTelefono(),responsableMunicipio.getfechaInicio(),responsableMunicipio.getfechaFin(),responsableMunicipio.getMunicipio(), responsableMunicipio.getIdentificador());
    }

    public void deleteResponsableMunicipio(String identificador) {
        jdbcTemplate.update("DELETE FROM ResponsableMunicipio WHERE identificador =?",
                identificador);
    }

    public ResponsableMunicipio getResponsableMunicipio(String identificador) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ResponsableMunicipio WHERE identificador =?",
                    new ResponsableMunicipioRowMapper(), identificador);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<ResponsableMunicipio> getResponsableMunicipios(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM ResponsableMunicipio",
                    new ResponsableMunicipioRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<ResponsableMunicipio>();
        }
    }
}
