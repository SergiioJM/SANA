package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Area;
import es.uji.ei1027.SANA.model.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MunicipioDAO {

    private JdbcTemplate jdbcTemplate;
    public MunicipioDAO(){
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void addMunicipio(Municipio municipio) {
        jdbcTemplate.update("INSERT INTO Municipio VALUES(?,?,?,?,?)",
                municipio.getCp(), municipio.getNombre(), municipio.getDireccion(), municipio.getEmail(), municipio.getTelefono());
    }

    public void updateMunicipio(Municipio municipio) {
        jdbcTemplate.update("UPDATE Municipio SET nombre =?, direccion =?, email =?, telefono =? WHERE cp =?",
                municipio.getNombre(),municipio.getDireccion(),municipio.getEmail(),municipio.getTelefono(),municipio.getCp());
    }

    public void deleteMunicipio(String cp) {
        jdbcTemplate.update("DELETE FROM Municipio WHERE cp =?",
                cp);
    }

    public Municipio getMunicipio(String cp) {
        try {


            return jdbcTemplate.queryForObject("SELECT * FROM Municipio WHERE cp =?",
                    new MunicipioRowMapper(), cp);



        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Municipio> getMunicipios(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Municipio",
                    new es.uji.ei1027.SANA.dao.MunicipioRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<Area> getAreasMunicipio(String cp){
        try{

            return jdbcTemplate.query(
                    "SELECT * FROM Area WHERE municipio =?",
                    new es.uji.ei1027.SANA.dao.AreaRowMapper(),cp);
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}