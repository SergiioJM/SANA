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

        if (periodoAsignado.getFechaInicio() == null)
            errors.rejectValue("fechaInicio", "obligatorio", "Tienes que introducir una fecha de inicio");

        if (periodoAsignado.getFechaFin() != null) {
            if (periodoAsignado.getFechaInicio().isAfter(periodoAsignado.getFechaFin()))
                errors.rejectValue("fechaFin", "obligatorio", "La fecha de fin tiene que ser posterior a la de inicio");
        }
    }
}
