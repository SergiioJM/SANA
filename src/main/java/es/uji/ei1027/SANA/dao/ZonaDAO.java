package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ZonaDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }
    public void addZona(Zona zona) {
        jdbcTemplate.update("INSERT INTO Zona VALUES(?,?,?)",
                obtenerZ(),zona.getCapacidad(),zona.getIdArea());
    }

    public String obtenerZ(){
        return "Z" + jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Zona", String.class);
    }

    public void updateZona(Zona zona) {
        jdbcTemplate.update("UPDATE Zona SET capacidad =?, idArea=? WHERE identificador =?",
                zona.getCapacidad(),zona.getIdArea(),zona.getIdentificador());
    }

    public void deleteZona(String identificador) {
        jdbcTemplate.update("DELETE FROM Zona WHERE identificador =?",
                identificador);
    }

    public Zona getZona(String identificador) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Zona WHERE identificador =?",
                    new es.uji.ei1027.SANA.dao.ZonaRowMapper(), identificador);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Zona> getZonas(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Zona",
                    new es.uji.ei1027.SANA.dao.ZonaRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
