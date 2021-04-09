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

    public List<Zona> getZonas(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Zona",
                    new es.uji.ei1027.SANA.dao.ZonaRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Zona>();
        }
    }
}
