package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Periodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PeriodoDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addPeriodo(Periodo periodo) {
        jdbcTemplate.update("INSERT INTO Periodo VALUES(?,?,?,?,?,?)",
                obtenerP(),periodo.getfechaInicio(),periodo.getfechaFin(),periodo.getHoraInicio(),periodo.getHoraFin(),periodo.getIdArea());
    }

    public String obtenerP(){
        String consulta = jdbcTemplate.queryForObject("SELECT MAX(identificador) AS id FROM Periodo", String.class);
        if (consulta == null){
            return "P1";
        }
        int p = Integer.parseInt(consulta.replace("P","")) + 1;
        return "P" + p;
    }

    public void deletePeriodo(String identificador) {
        jdbcTemplate.update("DELETE FROM Periodo WHERE identificador =?",
                identificador);
    }

    public void updatePeriodo(Periodo periodo) {
        jdbcTemplate.update("UPDATE Periodo SET fechaInicio =?, fechaFin =?, horaInicio =?, horaFin =?, idArea =? WHERE identificador=?" ,
                periodo.getfechaInicio(),periodo.getfechaFin(),periodo.getHoraInicio(),periodo.getHoraFin(),periodo.getIdArea(),periodo.getIdentificador());
    }

    public Periodo getPeriodo(String identificador) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Periodo WHERE identificador =?",
                    new PeriodoRowMapper(), identificador);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Periodo> getPeriodos(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Periodo",
                    new es.uji.ei1027.SANA.dao.PeriodoRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
