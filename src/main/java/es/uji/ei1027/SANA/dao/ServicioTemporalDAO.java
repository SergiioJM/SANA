package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.ServicioTemporal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ServicioTemporalDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void addServicioTemporal(ServicioTemporal servicioTemporal) {
        jdbcTemplate.update("INSERT INTO ServicioTemporal VALUES(?,?,?,?,?,?,?)",
                servicioTemporal.getNombre(),servicioTemporal.getfechaInicio(),servicioTemporal.getfechaFin(),servicioTemporal.getHoraInicio(),servicioTemporal.getHoraFin(),servicioTemporal.getIdArea(),servicioTemporal.getTipoServicio());
    }

    public void updateServicioTemporal(ServicioTemporal servicioTemporal) {
        jdbcTemplate.update("UPDATE ServicioTemporal SET fechaInicio =?, fechaFin =?, horaInicio =?, horaFin =?, nombreArea =?, tipoServicio=? WHERE nombre =?",
                servicioTemporal.getfechaInicio(),servicioTemporal.getfechaFin(),servicioTemporal.getHoraInicio(),servicioTemporal.getHoraFin(),servicioTemporal.getIdArea(),servicioTemporal.getTipoServicio(),servicioTemporal.getNombre());
    }

    public void deleteServicioTemporal(String nombre) {
        jdbcTemplate.update("DELETE FROM ServicioTemporal WHERE nombre =?",
                nombre);
    }

    public ServicioTemporal getServicioTemporal(String nombre) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ServicioTemporal WHERE nombre =?",
                    new ServicioTemporalRowMapper(), nombre);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<ServicioTemporal> getServiciosTemporal(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM ServicioTemporal",
                    new ServicioTemporalRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
    public List<String> getTipoServicio(){
        try{
            return jdbcTemplate.queryForList(
                    "SELECT nombre FROM  tipoServicio", String.class);
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
