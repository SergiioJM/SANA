package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServicioDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void addServicio(Servicio servicio) {
        jdbcTemplate.update("INSERT INTO  Servicio VALUES(?,?,?,?,?)",
                servicio.getNombre(),servicio.getDescripcion(),servicio.getEstado(),servicio.getArea(),servicio.getTipoServicio());
    }

    public void updateServicio(Servicio servicio) {
        jdbcTemplate.update("UPDATE  Servicio SET descripcion =?, estado =?, nombreArea =?, tipoServicio=?  WHERE nombre =?",
                servicio.getDescripcion(),servicio.getEstado(),servicio.getArea(),servicio.getTipoServicio(),servicio.getNombre());
    }

    public void deleteServicio(String nombre) {
        jdbcTemplate.update("DELETE FROM  Servicio WHERE nombre =?",
                nombre);
    }

    public Servicio getServicio(String nombre) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM  Servicio WHERE nombre =?",
                    new ServicioRowMapper(), nombre);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    public int getIdentificadorArea(String area){
        try{
            return jdbcTemplate.queryForObject(
                    "SELECT idArea FROM Area WHERE nombre=?",Integer.class,area);
        }catch(Exception e) {
            return 0;
        }
    }

    public List<Servicio> getServicios(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM  Servicio",
                    new es.uji.ei1027.SANA.dao.ServicioRowMapper());
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
