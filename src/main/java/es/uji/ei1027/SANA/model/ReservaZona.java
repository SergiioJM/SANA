package es.uji.ei1027.SANA.model;

public class ReservaZona {
    String reserva;
    String zona;

    public ReservaZona(){}

    public String getReserva() {
        return reserva;
    }

    public void setReserva(String reserva) {
        this.reserva = reserva;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "ReservaZona{" +
                "reserva='" + reserva + '\'' +
                ", zona='" + zona + '\'' +
                '}';
    }
}
