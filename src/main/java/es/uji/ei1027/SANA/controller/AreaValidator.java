package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.model.Area;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AreaValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return Area.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Area area = (Area) obj;
        if (area.getMunicipio().trim().equals(""))
            errors.rejectValue("municipio","obligatorio", "Tiene que introducir un municipio");

    }
}
