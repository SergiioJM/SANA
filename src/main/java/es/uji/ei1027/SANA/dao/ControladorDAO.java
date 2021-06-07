package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Controlador;
import org.jasypt.util.password.BasicPasswordEncryptor;
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

    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

    public void addControlador(Controlador controlador) {
        jdbcTemplate.update("INSERT INTO Controlador VALUES(?,?,?,?,?,?,?,?)",
            obtenerC(),controlador.getNombre(),controlador.getDireccion(),
                controlador.getEmail(),passwordEncryptor.encryptPassword(controlador.getPassword()),controlador.getTelefono(),controlador.getFecha(),controlador.getFechaFin());
    }
    public int obtenerC(){
        Integer consulta = jdbcTemplate.queryForObject("SELECT MAX(identificador) AS id FROM Controlador", Integer.class);
        if (consulta == null){
            return 1;
        }
        int c = consulta + 1;
        return  c;
    }


    public void updateControlador(Controlador controlador) {
        jdbcTemplate.update("UPDATE Controlador SET nombre =?,direccion =?,email =?,telefono =?,fechaInicio =?,fechaFin =? WHERE identificador =?",
                controlador.getNombre(),controlador.getDireccion(),controlador.getEmail(),controlador.getTelefono(),controlador.getFecha(),controlador.getFechaFin(),controlador.getIdentificador());
    }

    public void deleteControlador(int identificador) {
        jdbcTemplate.update("DELETE FROM Controlador WHERE identificador =?",
                identificador);
    }

    public Controlador getControlador(int identificador) {
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
            return new ArrayList<>();
        }
    }
    public Integer dameIdAreaPorEmail(String email){
        try{
            return jdbcTemplate.queryForObject(
                    "SELECT nombrearea FROM PeriodoAsignado WHERE nombrecontrolador IN (SELECT identificador FROM Controlador WHERE email=?)",
                    Integer.class,email);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
}
