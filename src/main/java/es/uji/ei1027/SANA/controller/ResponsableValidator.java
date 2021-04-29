package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.MunicipioDAO;
import es.uji.ei1027.SANA.model.Ciudadano;
import es.uji.ei1027.SANA.model.Municipio;
import es.uji.ei1027.SANA.model.ResponsableMunicipio;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;

public class ResponsableValidator implements Validator {

    public MunicipioDAO municipioDAO;

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
        if (responsableMunicipio.getIdentificador().trim().equals("")) errors.rejectValue("identificador", "obligatori",
                "El identificador es obligatorio");
        /*
        if (responsableMunicipio.getfechaInicio().isAfter(responsableMunicipio.getfechaFin()))
            errors.rejectValue("fechaFin", "obligatori", "La fecha de fin tiene que ser mayor que la fecha de inicio");


            ArrayList<String> lista = new ArrayList<>();
            for (Municipio e : municipioDAO.getMunicipios()) {
                lista.add(e.getCp());
                System.out.println(e);;
            }
        }
        if (!lista.contains(responsableMunicipio.getMunicipio().trim())) errors.rejectValue("municipiono", "obligatori",
                "El municipio no existe");
        */

    }
}
