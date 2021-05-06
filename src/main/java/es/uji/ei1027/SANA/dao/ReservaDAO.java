package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservaDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void addReserva(Reserva reserva) {
        int ide=obtenerR();
        jdbcTemplate.update("INSERT INTO Reserva VALUES(?,?,?,?,?,?,?)",
                ide,reserva.getHora(),reserva.getFecha(),reserva.getNumeroPersonas(),reserva.getEstado(),reserva.getZona() ,reserva.getCiudadano());
                reserva.setIdentificador(ide);
    }

    public int obtenerR(){
        String consulta = jdbcTemplate.queryForObject("SELECT MAX(identificador) AS id FROM Reserva", String.class);
        if (consulta == null){
            return 1;
        }

        int r = Integer.parseInt(consulta) + 1;
        return r;
    }

    public void updateReserva(Reserva reserva) {
        jdbcTemplate.update("UPDATE Reserva SET hora =?, fecha =?, numeroPersonas =?, estado =?,zona=?, ciudadano =? WHERE identificador =?",
                reserva.getHora(),reserva.getFecha(),reserva.getNumeroPersonas(),reserva.getEstado(),reserva.getZona(),reserva.getCiudadano(),reserva.getIdentificador());
    }

    public void deleteReserva(int identificador) {
        jdbcTemplate.update("DELETE FROM Reserva WHERE identificador =?",
                identificador);
    }

    public Reserva getReserva(int identificador) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Reserva WHERE identificador =?",
                    new ReservaRowMapper(), identificador);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Reserva> getReservas(){
        try {
            List<Reserva> res = jdbcTemplate.query(
                    "SELECT * FROM Reserva",
                    new es.uji.ei1027.SANA.dao.ReservaRowMapper());

            for (Reserva e : res){
                e.toString();
            }
            return res;
        }catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
