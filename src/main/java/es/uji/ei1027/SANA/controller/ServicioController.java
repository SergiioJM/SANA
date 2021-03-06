package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.AreaDAO;
import es.uji.ei1027.SANA.dao.ServicioDAO;
import es.uji.ei1027.SANA.model.Area;
import es.uji.ei1027.SANA.model.Servicio;
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
@RequestMapping("/servicio")
public class ServicioController {
    private ServicioDAO servicioDAO;
    private AreaDAO areaDAO;

    @Autowired
    public void setServicioDAO(ServicioDAO servicioDAO) {
        this.servicioDAO = servicioDAO;
    }
    @Autowired
    public void setAreaDAO(AreaDAO areaDAO) {
        this.areaDAO=areaDAO;
    }

    @RequestMapping("/list")
    public String listaDeServicios(Model model){
        model.addAttribute("servicios", servicioDAO.getServicios());
        return "servicio/list";
    }

    @RequestMapping(value="/add")
    public String addServicios(Model model) {
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("tipoServicio",servicioDAO.getTipoServicio());
        List<String> areas= areaDAO.getNombreAreas();
        if (areas.size()>0){
            model.addAttribute("arealista",areas);
        }
        return "servicio/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("servicio") Servicio servicio,
                                   BindingResult bindingResult, Model model) {
        ServicioValidator servicioValidator= new ServicioValidator();
        servicioValidator.validate(servicio,bindingResult);
        if (bindingResult.hasErrors()){
            List<Area> lista2 = areaDAO.getAreas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Area e : lista2) {
                lista.add(e.getIdArea());

            }
            model.addAttribute("tipoServicio",servicioDAO.getTipoServicio());
            model.addAttribute("arealista",lista);
            return "servicio/add";
        }
        try {
            servicio.setArea(servicioDAO.getIdentificadorArea(servicio.getNomArea()));
            servicioDAO.addServicio(servicio);
        }
        catch (DuplicateKeyException e ){
            throw new ClaveDuplicadaException("Ya existe el nombre " + servicio.getNombre() + " para un servicio","CPduplicada");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/update/{nombre}", method = RequestMethod.GET)
    public String editServicio(Model model, @PathVariable String nombre) {
        model.addAttribute("servicio", servicioDAO.getServicio(nombre));
        model.addAttribute("tipoServicio",servicioDAO.getTipoServicio());
        List<Area> lista2 = areaDAO.getAreas();
        ArrayList<Integer> lista = new ArrayList<>();
        for (Area e : lista2) {
            lista.add(e.getIdArea());

        }
        model.addAttribute("arealista",lista);
        return "servicio/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("servicio") Servicio servicio,
            BindingResult bindingResult ,Model model) {

        ServicioValidator servicioValidator= new ServicioValidator();
        servicioValidator.validate(servicio,bindingResult);
        if (bindingResult.hasErrors()) {

            List<Area> lista2 = areaDAO.getAreas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Area e : lista2) {
                lista.add(e.getIdArea());

            }
            model.addAttribute("arealista",lista);
            model.addAttribute("tipoServicio",servicioDAO.getTipoServicio());
            return "servicio/update";
        }
        servicioDAO.updateServicio(servicio);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{nombre}")
    public String processDelete(@PathVariable String nombre) {
        servicioDAO.deleteServicio(nombre);
        return "redirect:../list";
    }
}
