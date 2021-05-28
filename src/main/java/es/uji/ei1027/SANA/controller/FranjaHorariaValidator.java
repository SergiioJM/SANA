package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.model.FranjaHoraria;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FranjaHorariaValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return FranjaHoraria.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        FranjaHoraria franjaHoraria = (FranjaHoraria) obj;
        if (franjaHoraria.getfechaInicio() == null)
            errors.rejectValue("fechaInicio", "obligatorio", "Tienes que introducir una fecha de inicio");

        if (franjaHoraria.getfechaFin() != null) {
            if (franjaHoraria.getfechaInicio().isAfter(franjaHoraria.getfechaFin()))
                errors.rejectValue("fechaFin", "obligatorio", "La fecha de fin tiene que ser posterior a la de inicio");
        }
        if (franjaHoraria.getHoraInicio() == null)
            errors.rejectValue("horaInicio", "obligatorio", "Tienes que introducir una hora de inicio");

        if (franjaHoraria.getHoraFin() != null) {
            if (franjaHoraria.getHoraInicio().isAfter(franjaHoraria.getHoraFin()))
                errors.rejectValue("horaFin", "obligatorio", "La hora de fin tiene que ser posterior a la de inicio");
        }

    }
}