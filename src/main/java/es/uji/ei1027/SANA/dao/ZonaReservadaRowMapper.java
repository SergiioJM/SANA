package es.uji.ei1027.SANA.dao;



import es.uji.ei1027.SANA.model.ZonaReservada;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZonaReservadaRowMapper {
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
