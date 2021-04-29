package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.model.Periodo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PeriodoValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Periodo.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Periodo periodo = (Periodo) obj;
        if (periodo.getIdentificador().trim().equals(""))
            errors.rejectValue("identificador","obligatorio", "Tiene que introducir un identificador para el periodo");

        if (periodo.getIdArea().trim().equals(""))
            errors.rejectValue("idArea","obligatorio","El periodo tiene que ir relacionado con un area");

        if (periodo.getfechaInicio() == null)
            errors.rejectValue("fechaInicio", "obligatorio", "Tienes que introducir una fecha de inicio");

        if (periodo.getfechaFin() != null) {
            if (periodo.getfechaInicio().isAfter(periodo.getfechaFin()))
                errors.rejectValue("fechaFin", "obligatorio", "La fecha de fin tiene que ser posterior a la de inicio");
        }
    }
}