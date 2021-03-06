package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.model.Controlador;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ControladorValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return Controlador.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Controlador con =(Controlador) o;

        if (con.getFecha() == null)
            errors.rejectValue("fecha", "obligatorio", "Tienes que introducir una fecha de inicio");
        if (con.getFechaFin() != null) {
            if (con.getFecha().isAfter(con.getFechaFin()))
                errors.rejectValue("fechaFin", "obligatorio", "La fecha de fin tiene que ser posterior a la de inicio");
        }
    }
}

