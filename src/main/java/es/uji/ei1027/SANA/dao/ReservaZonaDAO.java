package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.ReservaZona;
import es.uji.ei1027.SANA.model.Zona;
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
                reservaZona.getReserva(),Integer.parseInt(reservaZona.getZona()));
    }

    public void deleteReservaZona(int reserva,String zona) {
        jdbcTemplate.update("DELETE FROM ReservaZonas WHERE id_reserva =? AND id_zona=?",reserva,Integer.parseInt(zona));
    }


    public List<String> getReservaZona(int reserva) {
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
    public List<String> getZonasArea(String nomarea){
        try{
            List<Zona> zonas= jdbcTemplate.query(
                    "SELECT * FROM Zona WHERE idArea IN (SELECT idarea FROM AREA WHERE nombre=?)", new ZonaRowMapper(), nomarea);
            List<String> zonasfinal=new ArrayList<>();
            /*
            for (Zona e : zonas){
                List<Periodo> zonas1= jdbcTemplate.query(
                        "SELECT * FROM PERIODO WHERE idZona=? AND fechaInicio=? AND horaInicio=?", new PeriodoRowMapper(),e.getIdentificador(), fecha, hora);
                if (zonas1.size()>0){
                    zonasfinal.add(e.getIdentificador() + "#Capacidad:" + e.getCapacidad());
                }
            }
            */
            for (Zona e: zonas){
                    zonasfinal.add(e.getIdentificador() + "#Capacidad:" + e.getCapacidad());
            }
            return zonasfinal;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
