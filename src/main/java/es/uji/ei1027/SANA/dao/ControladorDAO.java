package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ControladorDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    public void addControlador(Controlador controlador) {
        jdbcTemplate.update("INSERT INTO Controlador VALUES(?,?,?,?,?,?,?)",
            controlador.getIdentificador(),controlador.getNombre(),controlador.getDireccion(),controlador.getEmail(),controlador.getTelefono(),controlador.getFechaInicio(),controlador.getFechaFin());
    }

    public void updateControlador(Controlador controlador) {
        jdbcTemplate.update("UPDATE Controlador SET nombre =?,direccion =?,email =?,telefono =?,fechaInicio =?,fechaFin =? WHERE identificador =?)",
                controlador.getNombre(),controlador.getDireccion(),controlador.getEmail(),controlador.getTelefono(),controlador.getFechaInicio(),controlador.getFechaFin(),controlador.getIdentificador());
    }

    public void deleteControlador(String identificador) {
        jdbcTemplate.update("DELETE FROM Controlador WHERE identificador =?",
                identificador);
    }

    public Controlador getControlador(String identificador) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Controlador WHERE identificador =?",
                    new ControladorRowMapper(), identificador);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Controlador> getControladores(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Controlador",
                    new es.uji.ei1027.SANA.dao.ControladorRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Controlador>();
        }
    }
}
