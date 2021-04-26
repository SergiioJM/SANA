package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Estado;
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
        jdbcTemplate.update("INSERT INTO Reserva VALUES(?,?,?,?,?,?)",
                reserva.getIdentificador(),reserva.getHora(),reserva.getFecha(),reserva.getNumeroPersonas(),Estado.valueOf(reserva.getEstado()),reserva.getZona());
    }

    public void updateReserva(Reserva reserva) {
        jdbcTemplate.update("UPDATE Reserva SET hora =?, fecha =?, numeroPersonas =?, estado =?, zona=? WHERE identificador =?",
                reserva.getHora(),reserva.getFecha(),reserva.getNumeroPersonas()," " ,reserva.getZona(),reserva.getIdentificador());
    }

    public void deleteReserva(String identificador) {
        jdbcTemplate.update("DELETE FROM Reserva WHERE identificador =?",
                identificador);
    }

    public Reserva getReserva(String identificador) {
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
