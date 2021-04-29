package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.model.Municipio;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MunicipioValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Municipio.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Municipio municipio = (Municipio) obj;
        if (municipio.getCp().trim().equals(""))
            errors.rejectValue("cp","obligatorio", "Tiene que introducir un CP");
    }
}
