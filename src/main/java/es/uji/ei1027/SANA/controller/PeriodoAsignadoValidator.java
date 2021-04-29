package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.ControladorDAO;
import es.uji.ei1027.SANA.model.Controlador;
import es.uji.ei1027.SANA.model.PeriodoAsignado;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class PeriodoAsignadoValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return PeriodoAsignado.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PeriodoAsignado periodoAsignado = (PeriodoAsignado) obj;
        ControladorDAO controladorDAO = new ControladorDAO();
        if (periodoAsignado.getIdentificador().trim().equals(""))
            errors.rejectValue("identificador","obligatorio", "El periodo asignado tiene que tener un identificador");

        if (periodoAsignado.getControlador().trim().equals(""))
            errors.rejectValue("controlador", "obligatorio","El periodo asignado debe tener un controlador");

        if (periodoAsignado.getArea().trim().equals(""))
            errors.rejectValue("area","obligatorio", "El periodo asignado debe tener un area");

       List<String> controladores = new ArrayList<>();
        for (Controlador c: controladorDAO.getControladores()){
            System.out.println(c);
            controladores.add(c.getIdentificador());
        }
        if (!controladores.contains(periodoAsignado.getControlador()))
            errors.rejectValue("controlador","obligatorio","El controlador debe de existir");

    }
}
