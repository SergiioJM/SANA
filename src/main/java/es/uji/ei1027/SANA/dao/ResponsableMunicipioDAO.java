package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.Reserva;
import es.uji.ei1027.SANA.model.ReservaZona;
import es.uji.ei1027.SANA.model.ResponsableMunicipio;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResponsableMunicipioDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

    public void addResponsableMunicipio(ResponsableMunicipio responsableMunicipio) {
        jdbcTemplate.update("INSERT INTO ResponsableMunicipio VALUES(?,?,?,?,?,?,?,?)",
                responsableMunicipio.getNombre(),responsableMunicipio.getEmail(),passwordEncryptor.encryptPassword(responsableMunicipio.getPassword()),responsableMunicipio.getNumerotelefono(),responsableMunicipio.getfechaInicio(),responsableMunicipio.getfechaFin(),obtenerRM(),responsableMunicipio.getMunicipio());
    }

    public int obtenerRM(){
        String consulta = jdbcTemplate.queryForObject("SELECT MAX(identificador) AS id FROM ResponsableMunicipio", String.class);
        if (consulta == null){
            return 1;
        }

        int rm = Integer.parseInt(consulta) + 1;
        return rm;
    }

    public void updateResponsableMunicipio(ResponsableMunicipio responsableMunicipio) {
        jdbcTemplate.update("UPDATE ResponsableMunicipio SET nombre =?, email =?, numeroTelefono =?, fechaInicio =?, fechaFin =?, municipio =? WHERE identificador =?",
                responsableMunicipio.getNombre(),responsableMunicipio.getEmail(),responsableMunicipio.getNumerotelefono(),responsableMunicipio.getfechaInicio(),responsableMunicipio.getfechaFin(),responsableMunicipio.getMunicipio(), responsableMunicipio.getIdentificador());
    }

    public void deleteResponsableMunicipio(int identificador) {
        jdbcTemplate.update("DELETE FROM ResponsableMunicipio WHERE identificador =?",
                identificador);
    }

    public ResponsableMunicipio getResponsableMunicipio(int identificador) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ResponsableMunicipio WHERE identificador =?",
                    new ResponsableMunicipioRowMapper(), identificador);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<ResponsableMunicipio> getResponsablesMunicipios(){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM ResponsableMunicipio",
                    new ResponsableMunicipioRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<Integer> getAreaMunicipio(String municipio){
        try{
            List<Integer> zonas= jdbcTemplate.queryForList(
                    "SELECT idArea FROM Area WHERE municipio =?", Integer.class,municipio);
            return zonas;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
    public List<Integer> getZonasArea(int area){
        try{
            List<Integer> zonas= jdbcTemplate.queryForList(
                    "SELECT identificador FROM Zona WHERE idArea =?", Integer.class,area);
            return zonas;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
    public List<Integer> getReservasDeUnaZona(int zona){
        try{
            List<ReservaZona> reservas= jdbcTemplate.query(
                    "SELECT * FROM ReservaZonas WHERE id_zona=?", new ReservaZonaRowMapper(),zona);
            List<Integer> res=new ArrayList<>();
            for (ReservaZona e: reservas){
                if (!res.contains(e.getReserva())) {
                    res.add(e.getReserva());
                }
            }
            System.out.println(res);
            return res;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
    public List<String> getZonasDeReserva(int reserva){
        try {
            List<ReservaZona> zonas = jdbcTemplate.query(
                    "SELECT * FROM ReservaZonas WHERE id_reserva=?",
                    new es.uji.ei1027.SANA.dao.ReservaZonaRowMapper(),reserva);
            List<String> zonas2= new ArrayList<>();
            for (ReservaZona e : zonas){
                zonas2.add(e.getZona());
            }
            return zonas2;
        }catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
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

    public String dameMunicipioPorEmail(String email){
        try {
            return jdbcTemplate.queryForObject("SELECT municipio FROM ResponsableMunicipio WHERE email =?",
                    String.class, email);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
}
