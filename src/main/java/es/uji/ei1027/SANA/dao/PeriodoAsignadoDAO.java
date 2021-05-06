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

    public void addPeriodoAsignado(PeriodoAsignado periodoAsignado) {
        jdbcTemplate.update("INSERT INTO PeriodoAsignado VALUES(?,?,?,?,?)",
                obtenerPA(), periodoAsignado.getFechaInicio(), periodoAsignado.getFechaFin(), periodoAsignado.getControlador(), periodoAsignado.getArea());
    }

    public String obtenerPA(){
        String consulta = jdbcTemplate.queryForObject("SELECT MAX(identificador) AS id FROM PeriodoAsignado", String.class);
        if (consulta == null){
            return "PA1";
        }
        int pa = Integer.parseInt(consulta.replace("PA","")) + 1;
        return "PA" + pa;    }

    public void updatePeriodoAsignado(PeriodoAsignado periodoAsignado) {
        jdbcTemplate.update("UPDATE PeriodoAsignado SET  fechaInicio =?, fechaFin =?, nombreControlador =?, nombreArea =? WHERE identificador =?",
                periodoAsignado.getFechaInicio(), periodoAsignado.getFechaFin(), periodoAsignado.getControlador(), periodoAsignado.getArea(), periodoAsignado.getIdentificador());
    }

    public void deletePeriodoAsignado(String identificador) {
        jdbcTemplate.update("DELETE FROM PeriodoAsignado WHERE identificador =?",
                identificador);
    }

    public PeriodoAsignado getPeriodoAsignado(String area) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PeriodoAsignado WHERE identificador =?",
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
