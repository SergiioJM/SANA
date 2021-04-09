package es.uji.ei1027.SANA.model;

public class Zona {
        String identificador;
        int capacidad;
        Area idArea;

        public Zona(String identificador, int capacidad, Area idArea) {
            this.identificador = identificador;
            this.capacidad = capacidad;
            this.idArea = idArea;
        }

        public Zona() {

        }

        public String getIdentificador() {
            return identificador;
        }

        public void setIdentificador(String identificador) {
            this.identificador = identificador;
        }

        public int getCapacidad() {
            return capacidad;
        }

        public void setCapacidad(int capacidad) {
            this.capacidad = capacidad;
        }

        public Area getIdArea() {
            return idArea;
        }

        public void setIdArea(Area idArea) {
            this.idArea = idArea;
        }

        @Override
        public String toString() {
            return "Zone{" +
                    "identificador='" + identificador + '\'' +
                    ", capacidad=" + capacidad +
                    ", idArea=" + idArea +
                    '}';
        }
}
