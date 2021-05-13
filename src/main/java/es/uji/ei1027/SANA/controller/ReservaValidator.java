package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.ZonaDAO;
import es.uji.ei1027.SANA.model.Reserva;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ReservaValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return Reserva.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Reserva reserva = (Reserva) o;
        if (reserva.getNumeroPersonas() > 0){
            errors.rejectValue("numeroPersonas", "obligatorio", "La capacidad seleccionada debe ser mayor que 0 ");
        }
        if (reserva.getFecha() == null){
            errors.rejectValue("fecha", "obligatorio", "Debe poner una fecha");

        }
    }

    public void validate(Object obj, ZonaDAO zonaDAO , Errors errors) {
        Reserva reserva = (Reserva) obj;
        int capacidad = zonaDAO.getZona(reserva.getZona()).getCapacidad();
        if (reserva.getNumeroPersonas() >= capacidad){
            errors.rejectValue("numeroPersonas", "obligatorio", "La capacidad seleccionada es mayor que la capacidad de esta, maxima de : " + capacidad );

        }
    }
}