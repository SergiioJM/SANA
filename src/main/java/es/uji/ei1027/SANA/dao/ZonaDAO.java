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

    public int obtenerZ(){
        Integer consulta = jdbcTemplate.queryForObject("SELECT MAX(identificador) AS id FROM Zona", Integer.class);
        if (consulta == null){
            return 1;
        }

        int z = consulta + 1;
        return z;
    }

    public void updateZona(Zona zona) {
        jdbcTemplate.update("UPDATE Zona SET capacidad =?, idArea=? WHERE identificador =?",
                zona.getCapacidad(),zona.getIdArea(),Integer.parseInt(zona.getIdentificador()));
    }

    public void deleteZona(String identificador) {
        jdbcTemplate.update("DELETE FROM Zona WHERE identificador =?",
                Integer.parseInt(identificador));
    }

    public Zona getZona(String identificador) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Zona WHERE identificador =?",
                    new es.uji.ei1027.SANA.dao.ZonaRowMapper(), Integer.parseInt(identificador));
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

    public void setZona(String identificador , int capacidad) {
        jdbcTemplate.update("UPDATE Zona SET capacidad =? WHERE identificador =?",
                capacidad,Integer.parseInt(identificador));

    }

}
