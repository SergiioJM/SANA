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
        jdbcTemplate.update("INSERT INTO Periodo VALUES(?,?,?,?,?)",
                periodo.getfechaInicio(),periodo.getfechaFin(),periodo.getHoraInicio(),periodo.getHoraFin(),periodo.getIdArea());
    }

    public void deletePeriodo(String idArea) {
        jdbcTemplate.update("DELETE FROM Periodo WHERE idArea =?",
                idArea);
    }

    public void updatePeriodo(Periodo periodo) {
        jdbcTemplate.update("UPDATE Periodo SET fechaInicio =?, fechaFin =?, horaInicio =?, horaFin =?, idArea =?",
                periodo.getfechaInicio(),periodo.getfechaFin(),periodo.getHoraInicio(),periodo.getHoraFin(),periodo.getIdArea());
    }

    public Periodo getPeriodo(String periodo) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Periodo WHERE idArea =?",
                    new PeriodoRowMapper(), periodo);
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
