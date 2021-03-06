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
        if (reservaZona.getZona().length() > 3){
            errors.rejectValue("zona", "obligatorio", "Necesitas almenos 1 zona");
        }
    }

    public void validate(Object o, int capacidadTotalSeleccionada ,Errors errors) {
        ReservaZona reservaZona=(ReservaZona) o;

        if(0 ==  capacidadTotalSeleccionada){
            errors.rejectValue("zona", "obligatorio", "Debe seleccionar alguna zona");
        }else if(reservaZona.getPersonas() >  capacidadTotalSeleccionada){
            errors.rejectValue("zona", "obligatorio", "No hay espacio suficiente con esas areas");
        }


    }
}
