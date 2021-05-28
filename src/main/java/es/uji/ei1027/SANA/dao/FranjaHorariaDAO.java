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
        jdbcTemplate.update("INSERT INTO FranjaHoraria VALUES(?,?,?,?,?,?)",
                obtenerP(), franjaHoraria.getfechaInicio(), franjaHoraria.getfechaFin(), franjaHoraria.getHoraInicio(), franjaHoraria.getHoraFin(), idarea(franjaHoraria.getNombrearea()));
    }

    public int obtenerP(){
        String consulta = jdbcTemplate.queryForObject("SELECT MAX(identificador) AS id FROM FranjaHoraria", String.class);
        if (consulta == null){
            return 1;
        }
        int p = Integer.parseInt(consulta) + 1;
        return p;
    }

    public int idarea(String nombre){
        int consulta = jdbcTemplate.queryForObject("SELECT idArea FROM Area WHERE nombre=?", Integer.class,nombre);
        return consulta;
    }
    public String idnombrearea(int nombre){
        String consulta = jdbcTemplate.queryForObject("SELECT nombre FROM Area WHERE idArea=?", String.class,nombre);
        return consulta;
    }

    public void deleteFranjaHoraria(int identificador) {
        jdbcTemplate.update("DELETE FROM FranjaHoraria WHERE identificador =?",
                identificador);
    }

    public void updateFranjaHoraria(FranjaHoraria franjaHoraria) {
        franjaHoraria.setidArea(idarea(franjaHoraria.getNombrearea()));
        jdbcTemplate.update("UPDATE FranjaHoraria SET fechaInicio =?, fechaFin =?, horaInicio =?, horaFin =?, idArea =? WHERE identificador=?" ,
                franjaHoraria.getfechaInicio(), franjaHoraria.getfechaFin(), franjaHoraria.getHoraInicio(), franjaHoraria.getHoraFin(), franjaHoraria.getidArea(), franjaHoraria.getIdentificador());
    }

    public FranjaHoraria getFranjaHoraria(int identificador) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM FranjaHoraria WHERE identificador =?",
                    new FranjaHorariaRowMapper(), identificador);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<FranjaHoraria> getFranjasHorarias(){
        try{
            List<FranjaHoraria> frna= jdbcTemplate.query(
                    "SELECT * FROM FranjaHoraria",
                    new FranjaHorariaRowMapper());
            for(FranjaHoraria e: frna){
                e.setNombrearea(idnombrearea(e.getidArea()));
            }
            return frna;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
