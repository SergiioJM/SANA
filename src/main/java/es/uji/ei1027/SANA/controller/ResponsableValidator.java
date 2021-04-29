package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.model.Ciudadano;
import es.uji.ei1027.SANA.model.ResponsableMunicipio;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ResponsableValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return ResponsableMunicipio.class.equals(cls);
        // o, si volguérem tractar també les subclasses:
        // return Nadador.class.isAssignableFrom(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ResponsableMunicipio responsableMunicipio=(ResponsableMunicipio) o;
        if (responsableMunicipio.getMunicipio().trim().equals("")) errors.rejectValue("municipio", "obligatori",
                "El municipio es obligatorio");
        if (responsableMunicipio.getNombre().trim().equals("")) errors.rejectValue("nombre", "obligatori",
                "El nombre es obligatorio");
        if (responsableMunicipio.getfechaInicio().isAfter(responsableMunicipio.getfechaFin()))
            errors.rejectValue("fecho", "obligatori", "La fecha de fin tiene que ser mayor que la fecha de inicio");

    }
}
