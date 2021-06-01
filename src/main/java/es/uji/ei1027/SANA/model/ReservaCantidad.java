package es.uji.ei1027.SANA.model;

public class ReservaCantidad {
    Reserva reserva;
    int cantidad;

    public ReservaCantidad(Reserva reserva, int cantidad) {
        this.reserva = reserva;
        this.cantidad = cantidad;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
