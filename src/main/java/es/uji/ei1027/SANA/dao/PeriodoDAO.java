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
                periodo.getIdentificador(),periodo.getfechaInicio(),periodo.getfechaFin(),periodo.getHoraInicio(),periodo.getHoraFin(),periodo.getIdArea());
    }

    public void deletePeriodo(String identificador) {
        jdbcTemplate.update("DELETE FROM Periodo WHERE identificador =?",
                identificador);
    }

    public void updatePeriodo(Periodo periodo) {
        jdbcTemplate.update("UPDATE Periodo SET identificador =?, fechaInicio =?, fechaFin =?, horaInicio =?, horaFin =?, idArea =?",
                periodo.getIdentificador(),periodo.getfechaInicio(),periodo.getfechaFin(),periodo.getHoraInicio(),periodo.getHoraFin(),periodo.getIdArea());
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
