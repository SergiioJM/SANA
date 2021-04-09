package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.MunicipioDAO;
import es.uji.ei1027.SANA.model.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/municipio")
public class MunicipioController {

    private MunicipioDAO municipioDAO;

    @Autowired
    public void setMunicipioDAO(MunicipioDAO municipioDAO) {
        this.municipioDAO = municipioDAO;
    }

    @RequestMapping("/list")
    public String listaDeMunicipios(Model model){
        model.addAttribute("municipios", municipioDAO.getMunicipios());
        return "municipio/list";
    }

    @RequestMapping(value="/add")
    public String addMunicipio(Model model) {
        model.addAttribute("municipio", new Municipio());
        return "municipio/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("municipio") Municipio municipio,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "municipio/add";
        municipioDAO.addMunicipio(municipio);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{cp}", method = RequestMethod.GET)
    public String editMunicipio(Model model, @PathVariable String cp) {
        model.addAttribute("municipio", municipioDAO.getMunicipio(cp));
        return "municipio/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("municipio") Municipio municipio,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "municipio/update";
        municipioDAO.updateMunicipio(municipio);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{cp}")
    public String processDelete(@PathVariable String cp) {
        municipioDAO.deleteMunicipio(cp);
        return "redirect:../list";
    }
}
