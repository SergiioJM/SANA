package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.CiudadanoDAO;
import es.uji.ei1027.SANA.model.Ciudadano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ciudadano")

public class CiudadanoController {

    private CiudadanoDAO ciudadanoDAO;

    @Autowired
    public void setCiudadanoDAO(CiudadanoDAO ciudadanoDAO) { this.ciudadanoDAO = ciudadanoDAO; }


    @RequestMapping("/list")
    public String listaDeCiudadanos(Model model){
        model.addAttribute("ciudadanos", ciudadanoDAO.getCiudadanos());
        return "ciudadano/list";
    }

    @RequestMapping(value="/add")
    public String addCiudadanos(Model model) {
        model.addAttribute("ciudadano", new Ciudadano());
        return "ciudadano/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("ciudadano") Ciudadano ciudadano, BindingResult bindingResult) {
        CiudadanoValidator ciudadanoValidator=new CiudadanoValidator();
        ciudadanoValidator.validate(ciudadano,bindingResult);
        if (bindingResult.hasErrors())
            return "ciudadano/add";
        ciudadanoDAO.addCiudadano(ciudadano);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{nif}", method = RequestMethod.GET)
    public String editCiudadano(Model model, @PathVariable String nif) {
        model.addAttribute("ciudadano", ciudadanoDAO.getCiudadano(nif) );
        return "ciudadano/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("ciudadano") Ciudadano ciudadano,
            BindingResult bindingResult) {
        CiudadanoValidator ciudadanoValidator=new CiudadanoValidator();
        ciudadanoValidator.validate(ciudadano,bindingResult);
        if (bindingResult.hasErrors())
            return "ciudadano/update";
        ciudadanoDAO.updateCiudadano(ciudadano);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{nif}")
    public String processDelete(@PathVariable String nif) {
        ciudadanoDAO.deleteCiudadano(nif);
        return "redirect:../list";
    }
}
