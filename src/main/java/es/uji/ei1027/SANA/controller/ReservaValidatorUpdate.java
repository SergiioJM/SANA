package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.ZonaDAO;
import es.uji.ei1027.SANA.model.Reserva;
import es.uji.ei1027.SANA.model.ReservaCantidad;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class ReservaValidatorUpdate implements Validator {


    @Override
    public boolean supports(Class<?> cls) {
        return Reserva.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ReservaCantidad reservaCantidad = (ReservaCantidad) o;
        int cantidad= reservaCantidad.getCantidad();
        Reserva reserva = reservaCantidad.getReserva();
        System.out.println(cantidad);

        if (reserva.getFecha() == null){
            errors.rejectValue("fecha", "obligatorio", "Debe poner una fecha ");
        }
        else{
            if(reserva.getFecha().isBefore(LocalDate.now())) {
                errors.rejectValue("fecha", "obligatorio", "Debes introducir una fecha valida ");
            }
        }

        if (reserva.getEstado() == null || reserva.getEstado().equals("No seleccionado")){
            errors.rejectValue("estado", "obligatorio", "Debe seleccionar un estado ");
        }

        if (reserva.getNumeroPersonas() > cantidad || reserva.getNumeroPersonas() <= 0){
            errors.rejectValue("numeroPersonas", "obligatorio", "La cantidad de personas tiene que ser menos a la capacidad de tus zonas y mayor  que 0");
        }

    }
}