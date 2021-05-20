package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.tipoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class tipoDeServicioDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }
    public void addTipoServicio(tipoServicio tiposervicio){
        jdbcTemplate.update("INSERT INTO tipoServicio VALUES(?)",
                tiposervicio.getNombre());

    }
    public void deleteTipoServicio(String nombre){
        jdbcTemplate.update("DELETE FROM tipoServicio WHERE nombre =?", nombre);

    }
    public List<tipoServicio> getTipoServicios(){
        try{
            List<tipoServicio> lista= jdbcTemplate.query("SELECT * FROM tipoServicio",new tipoDeServicioRowMapper());
            return lista;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
