package es.uji.ei1027.SANA.dao;

import es.uji.ei1027.SANA.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservaDAO {


    private JdbcTemplate jdbcTemplate;
    private UserDetails userDetails;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void addReserva(Reserva reserva) {
        int ide=obtenerR();
        jdbcTemplate.update("INSERT INTO Reserva VALUES(?,?,?,?,?,?)",
                ide,reserva.getHora(),reserva.getFecha(),reserva.getNumeroPersonas(),reserva.getEstado(),reserva.getCiudadano());
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
        jdbcTemplate.update("UPDATE Reserva SET hora =?, fecha =?, numeroPersonas =?, estado =?, ciudadano =? WHERE identificador =?",
                reserva.getHora(),reserva.getFecha(),reserva.getNumeroPersonas(),reserva.getEstado(),reserva.getCiudadano(),reserva.getIdentificador());
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
                List<String> lista=getZonasDeReserva(e.getIdentificador());
                e.setListreserva(lista);
            }
            return res;
        }catch(EmptyResultDataAccessException e) {
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
    public List<ReservaZona> getReservaZonasDeMiReserva(int reserva){
        try {
            List<ReservaZona> zonas = jdbcTemplate.query(
                    "SELECT * FROM ReservaZonas WHERE id_reserva=?",
                    new es.uji.ei1027.SANA.dao.ReservaZonaRowMapper(),reserva);
            return zonas;
        }catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<Reserva> getReservasporCiudadano(String nif){
        try{
            List<Reserva> ciudadanos= jdbcTemplate.query(
                    "SELECT * FROM Reserva WHERE ciudadano=?", new ReservaRowMapper(),nif);
            for (Reserva e : ciudadanos){
                List<String> lista=getZonasDeReserva(e.getIdentificador());
                if (lista.size()>0){
                    e.setListreserva(lista);
                    String zona=lista.get(0);
                    String municipio=jdbcTemplate.queryForObject(
                            "SELECT nombre FROM Municipio WHERE cp IN (SELECT municipio FROM Area WHERE idarea IN (SELECT idarea FROM Zona WHERE identificador=?))",String.class, Integer.parseInt(zona));
                    e.setMunicipio(municipio);
                    if (lista.size()!=0) {
                        String area = jdbcTemplate.queryForObject("SELECT nombre FROM AREA WHERE idArea IN (SELECT idArea FROM Zona WHERE identificador=?)", String.class, Integer.parseInt(lista.get(0)));
                        e.setArea(area);
                    }
                    else {
                        e.setArea(" ");
                    }

                }
            }
            return ciudadanos;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
    public List<String> getAreas(String municipio){
        try{
            List<String> area= jdbcTemplate.queryForList(
                    "SELECT nombre FROM Area WHERE municipio IN (SELECT cp FROM Municipio WHERE nombre=?)", String.class, municipio);
            return area;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
    public void eliminarZonasReservadas(LocalDate fecha, String franja, String zona){
        jdbcTemplate.update("DELETE FROM ZonaReservada WHERE fecha =? AND franja=? AND idzona=?",
                fecha,franja,zona);
    }
    public List<String> getMunicipios(){
        try {
            return jdbcTemplate.queryForList("SELECT nombre FROM Municipio", String.class);

        }catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
    public String getMunicipio(String nombre){
        try {
            return jdbcTemplate.queryForObject("SELECT cp FROM Municipios WHERE nombre=?", String.class,nombre);

        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    public int cantidadZonas(List<String> zonas){
        try {
            int cantidad = 0;
            for (String e : zonas) {
                int ee=Integer.parseInt(e);
                int c = jdbcTemplate.queryForObject("SELECT capacidad FROM Zona WHERE identificador=?", Integer.class, ee);
                cantidad += c;
            }
            return cantidad;
        }catch(EmptyResultDataAccessException e) {
            return 0;
        }
    }
    public List<Reserva> getReservasporControlador(Integer area){
        try{
            List<Reserva> reservas= jdbcTemplate.query(
                    "SELECT * FROM Reserva WHERE identificador IN (SELECT id_reserva FROM ReservaZonas WHERE id_zona IN (SELECT identificador FROM Zona WHERE idarea=?))", new ReservaRowMapper(),area);
            List<Reserva> reservaFinal= new ArrayList<>();
            if (reservas.size()>0) {
                System.out.println(reservas.size());
                for (Reserva e : reservas) {
                    if (e.getFecha().isAfter(LocalDate.now())) {
                        List<String> lista = getZonasDeReserva(e.getIdentificador());
                        if (lista.size() > 0) {
                            e.setListreserva(lista);
                            String zona = lista.get(0);
                            String municipio = jdbcTemplate.queryForObject(
                                    "SELECT nombre FROM Municipio WHERE cp IN (SELECT municipio FROM Area WHERE idarea IN (SELECT idarea FROM Zona WHERE identificador=?))", String.class, Integer.parseInt(zona));
                            e.setMunicipio(municipio);
                            if (lista.size() != 0) {
                                String areaa = jdbcTemplate.queryForObject("SELECT nombre FROM AREA WHERE idArea IN (SELECT idArea FROM Zona WHERE identificador=?)", String.class, Integer.parseInt(lista.get(0)));
                                e.setArea(areaa);
                            } else {
                                e.setArea(" ");
                            }
                        }
                    }
                    reservaFinal.add(e);
                }
            }
            return reservaFinal;
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
    public String dameArea(String zona){
            String area = jdbcTemplate.queryForObject("SELECT nombre FROM AREA WHERE idArea IN (SELECT idArea FROM Zona WHERE identificador=?)", String.class, Integer.parseInt(zona));
            return area;
    }

    public List<List> getZonasReservadasFecha(LocalDate fecha){
        try {
            return jdbcTemplate.queryForList("SELECT identificador FROM Reserva WHERE fecha=?",List.class ,fecha);

        }catch(EmptyResultDataAccessException e) {
            return null;
        }

    }

}
