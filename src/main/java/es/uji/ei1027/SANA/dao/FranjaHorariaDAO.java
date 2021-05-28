package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.FranjaHoraria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FranjaHorariaDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addFranjaHoraria(FranjaHoraria franjaHoraria) {
        jdbcTemplate.update("INSERT INTO FranjaHorario VALUES(?,?,?,?,?,?)",
                obtenerP(), franjaHoraria.getfechaInicio(), franjaHoraria.getfechaFin(), franjaHoraria.getHoraInicio(), franjaHoraria.getHoraFin(), franjaHoraria.getidArea());
    }

    public int obtenerP(){
        String consulta = jdbcTemplate.queryForObject("SELECT MAX(identificador) AS id FROM FranjaHorario", String.class);
        if (consulta == null){
            return 1;
        }
        int p = Integer.parseInt(consulta) + 1;
        return p;
    }

    public void deleteFranjaHoraria(int identificador) {
        jdbcTemplate.update("DELETE FROM FranjaHorario WHERE identificador =?",
                identificador);
    }

    public void updateFranjaHoraria(FranjaHoraria franjaHoraria) {
        jdbcTemplate.update("UPDATE FranjaHorario SET fechaInicio =?, fechaFin =?, horaInicio =?, horaFin =?, idArea =? WHERE identificador=?" ,
                franjaHoraria.getfechaInicio(), franjaHoraria.getfechaFin(), franjaHoraria.getHoraInicio(), franjaHoraria.getHoraFin(), franjaHoraria.getidArea(), franjaHoraria.getIdentificador());
    }

    public FranjaHoraria getFranjaHoraria(int identificador) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM FranjaHorario WHERE identificador =?",
                    new FranjaHorariaRowMapper(), identificador);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<FranjaHoraria> getFranjasHorarias(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM FranjaHorario",
                    new FranjaHorariaRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
