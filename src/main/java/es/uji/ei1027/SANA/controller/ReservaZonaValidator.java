package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.model.ReservaZona;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ReservaZonaValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return ReservaZona.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ReservaZona reservaZona=(ReservaZona) o;
        if (reservaZona.getReserva().trim().equals(""))
            errors.rejectValue("reserva", "obligatorio", "Tienes que introducir una Reserva");

        if (reservaZona.getZona().trim().equals("")) {
            errors.rejectValue("zona", "obligatorio", "Tienes que introducir una Zona");
        }
    }
}
