package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.model.Ciudadano;
import es.uji.ei1027.SANA.model.Servicio;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ServicioValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Servicio.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Servicio servicio=(Servicio) o;
        if (servicio.getNombre().trim().equals("")) errors.rejectValue("nombre", "obligatori",
                "El nombre es obligatorio");
        /*
        if (servicio.getArea().trim().equals("")) errors.rejectValue("area", "obligatori",
                "El area es obligatorio");
*/
    }
}
