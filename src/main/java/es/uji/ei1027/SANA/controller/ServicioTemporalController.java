package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.MunicipioDAO;
import es.uji.ei1027.SANA.dao.ServicioTemporalDAO;
import es.uji.ei1027.SANA.model.Municipio;
import es.uji.ei1027.SANA.model.ServicioTemporal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/serviciotemporal")
public class ServicioTemporalController {

    private ServicioTemporalDAO servicioTemporalDAO;

    @Autowired
    public void setServicioTemporalDAO(ServicioTemporalDAO servicioTemporalDAO) {
        this.servicioTemporalDAO= servicioTemporalDAO;
    }

    @RequestMapping("/list")
    public String listaDeServiciosTemporales(Model model){
        model.addAttribute("serviciostemporales", servicioTemporalDAO.getServiciosTemporal());
        return "serviciotemporal/list";
    }

    @RequestMapping(value="/add")
    public String addserviciotemporal(Model model) {
        model.addAttribute("serviciotemporal", new ServicioTemporal());
        return "serviciotemporal/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("serviciotemporal") ServicioTemporal servicioTemporal,
                                   BindingResult bindingResult) {
        ServicioTemporalValidator servicioTemporalValidatorValidator= new ServicioTemporalValidator();
        servicioTemporalValidatorValidator.validate(servicioTemporal,bindingResult);
        if (bindingResult.hasErrors())
            return "serviciotemporal/add";
        try {
            servicioTemporalDAO.addServicioTemporal(servicioTemporal);
        }
        catch (DuplicateKeyException e ) {
            throw new ClaveDuplicadaException("Ya existe un servicio Temporal con ese Nombre: " + servicioTemporal.getNombre(), "CPduplicada");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/update/{nombre}", method = RequestMethod.GET)
    public String editserviciotemporal(Model model, @PathVariable String nombre) {
        model.addAttribute("serviciotemporal", servicioTemporalDAO.getServicioTemporal(nombre));
        return "serviciotemporal/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("serviciotemporal") ServicioTemporal servicioTemporal,
            BindingResult bindingResult) {
        ServicioTemporalValidator servicioTemporalValidatorValidator= new ServicioTemporalValidator();
        servicioTemporalValidatorValidator.validate(servicioTemporal,bindingResult);
        if (bindingResult.hasErrors())
            return "serviciotemporal/update";
        servicioTemporalDAO.updateServicioTemporal(servicioTemporal);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{nombre}")
    public String processDelete(@PathVariable String nombre) {
        servicioTemporalDAO.deleteServicioTemporal(nombre);
        return "redirect:../list";
    }
}
