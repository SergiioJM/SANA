package es.uji.ei1027.SANA.dao;



import es.uji.ei1027.SANA.model.Servicio;
import es.uji.ei1027.SANA.model.ZonaReservada;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZonaReservadaRowMapper implements RowMapper<ZonaReservada> {
    public ZonaReservada mapRow(ResultSet rs, int rowNum) throws SQLException {
        ZonaReservada zonaReservada = new ZonaReservada();
        zonaReservada.setIdentificador(rs.getInt("identificador"));
        zonaReservada.setIdarea(rs.getInt("idarea"));
        zonaReservada.setIdzona(rs.getString("idzona"));
        zonaReservada.setFecha(rs.getDate("fecha"));
        zonaReservada.setFranja(rs.getString("franja"));
        return zonaReservada;
    }
}
