package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MunicipioDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Municipio> getMunicipios(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Municipio",
                    new MunicipioRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Municipio>();
        }
    }
}
