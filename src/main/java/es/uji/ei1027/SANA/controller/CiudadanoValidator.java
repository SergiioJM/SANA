package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.model.Ciudadano;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CiudadanoValidator implements Validator {
        @Override
        public boolean supports(Class<?> cls) {
            return Ciudadano.class.equals(cls);
        }

        @Override
        public void validate(Object o, Errors errors) {
                Ciudadano ciudadano=(Ciudadano) o;
                if (ciudadano.getNif().trim().equals("")) errors.rejectValue("nif", "obligatori",
                        "El nif es obligatorio");
                if (ciudadano.getNombre().trim().equals("")) errors.rejectValue("nombre", "obligatori",
                    "El nombre es obligatorio");
                if (ciudadano.getPassword().trim().equals("")) errors.rejectValue("password", "obligatori",
                        "Tienes que poner una contraseña");
        }
}
