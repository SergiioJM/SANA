package es.uji.ei1027.SANA.dao;



import es.uji.ei1027.SANA.model.ZonaReservada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ZonaReservadaDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }
    public void addZonaReservada(ZonaReservada zonaReservada) {
        int ide=obtenerR();
        jdbcTemplate.update("INSERT INTO ZonaReservada VALUES(?,?,?,?,?)",
                ide,zonaReservada.getIdarea(),zonaReservada.getIdzona(),zonaReservada.getFecha(),zonaReservada.getFranja());
        zonaReservada.setIdentificador(ide);
    }
    public int obtenerR(){
        String consulta = jdbcTemplate.queryForObject("SELECT MAX(identificador) AS id FROM ZonaReservada", String.class);
        if (consulta == null){
            return 1;
        }

        int r = Integer.parseInt(consulta) + 1;
        return r;
    }

    public void updateZonaReservada(ZonaReservada zonaReservada) {
        jdbcTemplate.update("UPDATE ZonaReservada SET identificador =?, idarea=?, idzona =?, fecha =? " +
                        ",franja =? WHERE identificador =?",
                zonaReservada.getIdentificador(),zonaReservada.getIdarea(),zonaReservada.getIdzona()
                ,zonaReservada.getFecha(),zonaReservada.getFranja());
    }

    public void deleteZonaReservada(int identificador) {
        jdbcTemplate.update("DELETE FROM ZonaReservada WHERE identificador =?",
                identificador);
    }

    public ZonaReservada getZonaReservada(int identificador) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ZonaReservada WHERE identificador =?",
                    new ZonaReservadaRowMapper(), identificador);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    public List<ZonaReservada> getZonasReservadas() {
        try {
            return jdbcTemplate.query("SELECT * FROM ZonaReservada",
                    new ZonaReservadaRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
    public ZonaReservada getZonaReservada2(int zona, String frnaja, LocalDate fecha) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ZonaReservada WHERE idzona =? AND fecha=? AND franja=?",
                    new ZonaReservadaRowMapper(), zona,frnaja, fecha);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    public List<ZonaReservada> getZonaReservada3(int area, LocalDate fecha) {
        try {
            return jdbcTemplate.query("SELECT * FROM ZonaReservada WHERE idarea =? AND fecha=?",
                    new ZonaReservadaRowMapper(), area, fecha);
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Integer getArea(String nombre){
        try {
            return jdbcTemplate.queryForObject("SELECT idArea FROM Area WHERE nombre =?",
                    Integer.class, nombre);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

}
