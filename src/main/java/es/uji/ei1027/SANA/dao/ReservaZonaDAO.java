package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Area;
import es.uji.ei1027.SANA.model.ReservaZona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservaZonaDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addReservaZona(ReservaZona reservaZona) {
        jdbcTemplate.update("INSERT INTO ReservaZonas VALUES(?,?)",
                reservaZona.getReserva(),reservaZona.getZona());
    }

    public void deleteReservaZona(String reserva,String zona) {
        jdbcTemplate.update("DELETE FROM ReservaZonas WHERE id_reserva =? AND id_zona=?",reserva,zona );
    }


    public List<String> getReservaZona(String reserva) {
        try {
            return jdbcTemplate.queryForList("SELECT id_zona FROM ReservaZonas WHERE id_reserva=?",
                    String.class, reserva);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<ReservaZona> getReservaZonas(){
        try{
            List<ReservaZona> reservaZonas= jdbcTemplate.query(
                    "SELECT * FROM ReservaZonas",
                    new es.uji.ei1027.SANA.dao.ReservaZonaRowMapper());
            return reservaZonas;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
