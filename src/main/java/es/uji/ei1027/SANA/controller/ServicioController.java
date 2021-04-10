package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.ServicioDAO;
import es.uji.ei1027.SANA.model.Municipio;
import es.uji.ei1027.SANA.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/servicio")
public class ServicioController {
    private ServicioDAO servicioDAO;

    @Autowired
    public void setServicioDAO(ServicioDAO servicioDAO) {
        this.servicioDAO = servicioDAO;
    }

    @RequestMapping("/list")
    public String listaDeServicios(Model model){
        model.addAttribute("servicios", servicioDAO.getServicios());
        return "servicio/list";
    }

    @RequestMapping(value="/add")
    public String addServicios(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "servicio/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("servicio") Servicio servicio,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "servicio/add";
        servicioDAO.addServicio(servicio);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{nombre}", method = RequestMethod.GET)
    public String editServicio(Model model, @PathVariable String nombre) {
        model.addAttribute("servicio", servicioDAO.getServicio(nombre));
        return "servicio/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("servicio") Servicio servicio,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "servicio/update";
        servicioDAO.updateServicio(servicio);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{nombre}")
    public String processDelete(@PathVariable String nombre) {
        servicioDAO.deleteServicio(nombre);
        return "redirect:../list";
    }
}
