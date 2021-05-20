package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.AreaDAO;
import es.uji.ei1027.SANA.dao.ServicioTemporalDAO;
import es.uji.ei1027.SANA.model.Area;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/serviciotemporal")
public class ServicioTemporalController {

    private ServicioTemporalDAO servicioTemporalDAO;
    private AreaDAO areaDAO;

    @Autowired
    public void setServicioTemporalDAO(ServicioTemporalDAO servicioTemporalDAO) {
        this.servicioTemporalDAO= servicioTemporalDAO;
    }

    @Autowired
    public void setAreaDAO(AreaDAO areaDAO) {
        this.areaDAO=areaDAO;
    }

    @RequestMapping("/list")
    public String listaDeServiciosTemporales(Model model){
        model.addAttribute("serviciostemporales", servicioTemporalDAO.getServiciosTemporal());
        return "serviciotemporal/list";
    }

    @RequestMapping(value="/add")
    public String addserviciotemporal(Model model) {
        model.addAttribute("serviciotemporal", new ServicioTemporal());
        model.addAttribute("tipoServicio",servicioTemporalDAO.getTipoServicio());
        List<Area> lista2 = areaDAO.getAreas();
        ArrayList<Integer> lista = new ArrayList<>();
        for (Area e : lista2)
            lista.add(e.getIdArea());
        model.addAttribute("arealista",lista);
        return "serviciotemporal/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("serviciotemporal") ServicioTemporal servicioTemporal,
                                   BindingResult bindingResult, Model model) {
        ServicioTemporalValidator servicioTemporalValidatorValidator= new ServicioTemporalValidator();
        servicioTemporalValidatorValidator.validate(servicioTemporal,bindingResult);
        if (bindingResult.hasErrors()) {
            List<Area> lista2 = areaDAO.getAreas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Area e : lista2)
                lista.add(e.getIdArea());
            model.addAttribute("arealista", lista);
            model.addAttribute("tipoServicio",servicioTemporalDAO.getTipoServicio());
            return "serviciotemporal/add";
        }
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
        List<Area> lista2 = areaDAO.getAreas();
        ArrayList<Integer> lista = new ArrayList<>();
        for (Area e : lista2)
            lista.add(e.getIdArea());
        model.addAttribute("arealista",lista);
        model.addAttribute("tipoServicio",servicioTemporalDAO.getTipoServicio());
        return "serviciotemporal/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("serviciotemporal") ServicioTemporal servicioTemporal,
            BindingResult bindingResult, Model model) {
        ServicioTemporalValidator servicioTemporalValidatorValidator= new ServicioTemporalValidator();
        servicioTemporalValidatorValidator.validate(servicioTemporal,bindingResult);
        if (bindingResult.hasErrors()) {
            List<Area> lista2 = areaDAO.getAreas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Area e : lista2)
                lista.add(e.getIdArea());
            model.addAttribute("arealista", lista);
            model.addAttribute("tipoServicio",servicioTemporalDAO.getTipoServicio());
            return "serviciotemporal/update";
        }
        servicioTemporalDAO.updateServicioTemporal(servicioTemporal);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{nombre}")
    public String processDelete(@PathVariable String nombre) {
        servicioTemporalDAO.deleteServicioTemporal(nombre);
        return "redirect:../list";
    }
}
