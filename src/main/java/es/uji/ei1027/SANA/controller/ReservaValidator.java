package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.ZonaDAO;
import es.uji.ei1027.SANA.model.Reserva;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class ReservaValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return Reserva.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Reserva reserva = (Reserva) o;
        if (reserva.getNumeroPersonas() <= 0){
            errors.rejectValue("numeroPersonas", "obligatorio", "El numero de personas debe ser mayor que 0 ");
        }

        if (reserva.getFecha() == null){
            errors.rejectValue("fecha", "obligatorio", "Debe poner una fecha ");
        }
        else{
            if(reserva.getFecha().isBefore(LocalDate.now())) {
                errors.rejectValue("fecha", "obligatorio", "Debes introducir una fecha valida ");
            }
        }
    }
}