package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.ZonaDAO;
import es.uji.ei1027.SANA.dao.tipoDeServicioDAO;
import es.uji.ei1027.SANA.model.Area;
import es.uji.ei1027.SANA.model.Zona;
import es.uji.ei1027.SANA.model.tipoServicio;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/tipoServicio")
public class tipoServicioController {
    public tipoDeServicioDAO tipoDeServicioDAO;
    @Autowired
    public void setTipoDeServicioDAO(tipoDeServicioDAO tipoDeServicioDAO) { this.tipoDeServicioDAO = tipoDeServicioDAO; }

     @RequestMapping("/list")
    public String listaDetiposServicio(Model model){
        model.addAttribute("tiposServicio", tipoDeServicioDAO.getTipoServicios());
        return "tipoServicio/list";
    }
    @RequestMapping(value="/add")
    public String addtipoServicio(Model model) {
        model.addAttribute("tipoServicio", new tipoServicio());
        return "tipoServicio/add";
    }
    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("tipoServicio") tipoServicio tipoServicio,
                                   BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "tipoServicio/add";
        }
        tipoDeServicioDAO.addTipoServicio(tipoServicio);
        return "redirect:list";
    }
    @RequestMapping(value="/delete/{nombre}")
    public String processDelete(@PathVariable String nombre) {
        tipoDeServicioDAO.deleteTipoServicio(nombre);
        return "redirect:../list";
    }
}
