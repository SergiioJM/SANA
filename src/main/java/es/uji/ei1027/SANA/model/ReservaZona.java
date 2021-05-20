package es.uji.ei1027.SANA.model;

public class ReservaZona {
    int reserva;
    String zona;
    int personas;




    public ReservaZona(){}

    public int getReserva() {
        return reserva;
    }

    public void setReserva(int reserva) {
        this.reserva = reserva;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    @Override
    public String toString() {
        return "ReservaZona{" +
                "reserva='" + reserva + '\'' +
                ", zona='" + zona + '\'' +
                '}';
    }
}
