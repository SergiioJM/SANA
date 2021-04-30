package es.uji.ei1027.SANA.controller;



import es.uji.ei1027.SANA.model.ServicioTemporal;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ServicioTemporalValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return ServicioTemporal.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ServicioTemporal servicioTemporal=(ServicioTemporal) o;
        if (servicioTemporal.getfechaInicio() == null)
            errors.rejectValue("fechaInicio", "obligatorio", "Tienes que introducir una fecha de inicio");

        if (servicioTemporal.getfechaFin() != null) {
            if (servicioTemporal.getfechaInicio().isAfter(servicioTemporal.getfechaFin()))
                errors.rejectValue("fechaFin", "obligatorio", "La fecha de fin tiene que ser posterior a la de inicio");
        }
        if (servicioTemporal.getHoraInicio() == null)
            errors.rejectValue("horaInicio", "obligatorio", "Tienes que introducir una hora de inicio");

        if (servicioTemporal.getHoraFin() != null) {
            if (servicioTemporal.getHoraInicio().isAfter(servicioTemporal.getHoraFin()))
                errors.rejectValue("horaFin", "obligatorio", "La hora de fin tiene que ser posterior a la de inicio");
        }

    }
}
