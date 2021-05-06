package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.model.Zona;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ZonaValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Zona.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Zona zona=(Zona) o;

        if (zona.getCapacidad() < 1) {
            errors.rejectValue("capacidad", "obligatorio", "La capacidad tiene que ser mayor que 0");
        }


    }
}
