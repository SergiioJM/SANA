package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addArea(Area area) {
        jdbcTemplate.update("INSERT INTO Area VALUES(?,?,?,?,?,?,?)",
                obtenerA(),area.getNombre(),area.getDescripcion(),area.getCaracteristicas(),area.getLocalizacion(),area.getTipoAcceso(),area.getMunicipio());
    }

    public int obtenerA(){
        String consulta = jdbcTemplate.queryForObject("SELECT MAX(idArea) AS id FROM Area", String.class);
        if (consulta == null){
            return 1;
        }
        int a = Integer.parseInt(consulta) + 1;
        return  a;
    }

    public void deleteArea(int area) {
        jdbcTemplate.update("DELETE FROM Area WHERE idArea =?", area);
    }

    public void updateArea(Area area) {
        jdbcTemplate.update("UPDATE Area SET nombre =?, descripcion =?, caracteristicasfisicas =?, localizacion =?, tipoAcceso =?, municipio =? WHERE idArea =?",
                area.getNombre(),area.getDescripcion(),area.getCaracteristicas(),area.getLocalizacion(),area.getTipoAcceso(),area.getMunicipio(), area.getIdArea());
    }

    public Area getArea(int area) {
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
            List<Area> areas= jdbcTemplate.query(
                    "SELECT * FROM Area",
                    new es.uji.ei1027.SANA.dao.AreaRowMapper());
            for (Area a: areas){
                a.toString();
            }
            return areas;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
