package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

@Repository
public class AreaDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addArea(Area area) {
        jdbcTemplate.update("INSERT INTO Area VALUES(?,?,?,?,?,?,?)",
                area.getIdArea(),area.getNombre(),area.getDescripcion(),area.getCaracteristicas(),area.getLocalizacion(),area.getTipoAcceso(),area.getMunicipio());
    }

    public void deleteArea(Area area) {
        jdbcTemplate.update("DELETE FROM Area WHERE idArea =?",
                area.getIdArea());
    }

    public void updateArea(Area area) {
        jdbcTemplate.update("UPDATE Area SET nombre =?, descripcion =?, caracteristicas =?, localizacion =?, tipoAcceso =?, municipio =? WHERE idArea =?",
                area.getNombre(),area.getDescripcion(),area.getCaracteristicas(),area.getLocalizacion(),area.getTipoAcceso(),area.getMunicipio(), area.getIdArea());
    }

    public Area getArea(String area) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Area WHERE idArea =?",
                    new AreaRowMapper(), area);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Area> getAreas(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Area",
                    new es.uji.ei1027.SANA.dao.AreaRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Area>();
        }
    }
}
