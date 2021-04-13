package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.PeriodoAsignado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PeriodoAsignadoDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PeriodoAsignado getPeriodoAsignado(String area) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PeridoAsignado WHERE area =?",
                    new PeriodoAsignadoRowMapper(), area);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<PeriodoAsignado> getPeriodosAsignados(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM PeriodoAsignado",
                    new es.uji.ei1027.SANA.dao.PeriodoAsignadoRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<PeriodoAsignado>();
        }
    }
}