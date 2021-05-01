package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.model.Reserva;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ReservaValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return Reserva.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Reserva reserva = (Reserva) obj;
        if (reserva.getIdentificador().trim().equals(""))
            errors.rejectValue("identificador","obligatorio", "Tiene que introducir un identificador");
    }
}