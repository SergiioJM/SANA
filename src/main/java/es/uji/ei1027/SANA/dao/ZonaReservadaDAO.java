package es.uji.ei1027.SANA.dao;


import es.uji.ei1027.SANA.model.ZonaReservada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ZonaReservadaDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }
    public void addZonaReservada(ZonaReservada zonaReservada) {
        jdbcTemplate.update("INSERT INTO ZonaReservada VALUES(?,?,?,?,?)",
                zonaReservada.getIdentificador(),zonaReservada.getIdarea(),zonaReservada.getIdzona()
                ,zonaReservada.getFecha(),zonaReservada.getFranja());
    }


    public void updateZonaReservada(ZonaReservada zonaReservada) {
        jdbcTemplate.update("UPDATE Zona SET identificador =?, idarea=?, idzona =?, fecha =? " +
                        ",franja =? WHERE identificador =?",
                zonaReservada.getIdentificador(),zonaReservada.getIdarea(),zonaReservada.getIdzona()
                ,zonaReservada.getFecha(),zonaReservada.getFranja());
    }

    public void deleteZonaReservada(int identificador) {
        jdbcTemplate.update("DELETE FROM Zona WHERE identificador =?",
                identificador);
    }

    public ZonaReservada getZonaReservada(int identificador) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ZonaReservada WHERE identificador =?",
                    new es.uji.ei1027.SANA.dao.ZonaReservadaRowMapper(), identificador);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
}
