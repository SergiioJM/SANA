package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Ciudadano;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CiudadanoDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

    public void addCiudadano(Ciudadano ciudadano) {
        jdbcTemplate.update("INSERT INTO Ciudadano VALUES(?,?,?,?,?,?)",
                ciudadano.getNombre(),ciudadano.getNif(),ciudadano.getEmail(),ciudadano.getResidencia(),ciudadano.getFechaRegistro(),passwordEncryptor.encryptPassword(ciudadano.getPassword()));
    }

    public void updateCiudadano(Ciudadano ciudadano) {
        jdbcTemplate.update("UPDATE Ciudadano SET nombre =?, email =?, residencia =?, fechaRegistro =?, password =? WHERE nif =?",
                ciudadano.getNombre(),ciudadano.getEmail(),ciudadano.getResidencia(),ciudadano.getFechaRegistro(),ciudadano.getPassword(),ciudadano.getNif());
    }

    public void deleteCiudadano(String Nif) {
        jdbcTemplate.update("DELETE FROM Ciudadano WHERE nif =?", Nif);
    }

    public Ciudadano getCiudadano(String Nif) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Ciudadano WHERE nif =?",
                    new CiudadanoRowMapper(), Nif);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Ciudadano> getCiudadanos(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Ciudadano",
                    new es.uji.ei1027.SANA.dao.CiudadanoRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
