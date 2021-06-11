package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.MunicipioDAO;
import es.uji.ei1027.SANA.model.Municipio;
import es.uji.ei1027.SANA.model.ResponsableMunicipio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class ResponsableMunicipioValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return ResponsableMunicipio.class.equals(cls);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ResponsableMunicipio responsableMunicipio=(ResponsableMunicipio) o;

        if (responsableMunicipio.getMunicipio().trim().equals("")) errors.rejectValue("municipio", "obligatori",
                "El municipio es obligatorio");
        if (responsableMunicipio.getNombre().trim().equals("")) errors.rejectValue("nombre", "obligatori",
                "El nombre es obligatorio");

        if (responsableMunicipio.getfechaInicio() == null)
            errors.rejectValue("fechaInicio", "obligatorio", "Tienes que introducir una fecha de inicio");

        if (responsableMunicipio.getfechaFin() != null) {
            if (responsableMunicipio.getfechaInicio().isAfter(responsableMunicipio.getfechaFin()))
                errors.rejectValue("fechaFin", "obligatorio", "La fecha de fin tiene que ser posterior a la de inicio");
        }


    }
}
