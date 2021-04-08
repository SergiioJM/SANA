package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Municipality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MunicipalityDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Municipality> getMunicipios(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Municipio",
                    new es.uji.ei1027.SANA.dao.MunicipalityRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Municipality>();
        }
    }
}
