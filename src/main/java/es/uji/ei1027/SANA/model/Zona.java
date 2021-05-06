package es.uji.ei1027.SANA.model;

public class Zona {
        int identificador;
        int capacidad;
        int idArea;

        public Zona(int identificador, int capacidad, int idArea) {
            this.identificador = identificador;
            this.capacidad = capacidad;
            this.idArea = idArea;
        }

        public Zona() {

        }

        public int getIdentificador() {
            return identificador;
        }

        public void setIdentificador(int identificador) {
            this.identificador = identificador;
        }

        public int getCapacidad() {
            return capacidad;
        }

        public void setCapacidad(int capacidad) {
            this.capacidad = capacidad;
        }

        public int getIdArea() {
            return idArea;
        }

        public void setIdArea(int idArea) {
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
