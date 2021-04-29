package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.model.PeriodoAsignado;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PeriodoAsignadoValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return PeriodoAsignado.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PeriodoAsignado periodoAsignado = (PeriodoAsignado) obj;
        if (periodoAsignado.getIdentificador().trim().equals(""))
            errors.rejectValue("identificador","obligatorio", "El periodo asignado tiene que tener un identificador");

        if (periodoAsignado.getControlador().trim().equals(""))
            errors.rejectValue("controlador", "obligatorio","El periodo asignado debe tener un controlador");

        if (periodoAsignado.getArea().trim().equals(""))
            errors.rejectValue("area","obligatorio", "El periodo asignado debe tener un area");

    }

}
