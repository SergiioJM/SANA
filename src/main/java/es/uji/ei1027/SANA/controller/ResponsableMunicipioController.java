package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.MunicipioDAO;
import es.uji.ei1027.SANA.dao.ResponsableMunicipioDAO;
import es.uji.ei1027.SANA.model.Municipio;
import es.uji.ei1027.SANA.model.ResponsableMunicipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/responsablemunicipio")
public class ResponsableMunicipioController {
    private ResponsableMunicipioDAO responsableMunicipioDAO;

    @Autowired
    public void setResponsableMunicipioDAO(ResponsableMunicipioDAO responsableMunicipioDAO) {
        this.responsableMunicipioDAO = responsableMunicipioDAO;
    }

    @RequestMapping("/list")
    public String listaDeResponsableMunicipios(Model model){
        model.addAttribute("responsablemunicipio", responsableMunicipioDAO.getResponsableMunicipios());
        return "responsablemunicipio/list";
    }

    @RequestMapping(value="/add")
    public String addResponsableMunicipio(Model model) {
        model.addAttribute("responsablemunicipio", new ResponsableMunicipio());
        return "responsablemunicipio/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("responsablemunicipio") ResponsableMunicipio responsableMunicipio,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "responsablemunicipio/add";
        responsableMunicipioDAO.addResponsableMunicipio(responsableMunicipio);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editResponsableMunicipio(Model model, @PathVariable String identificador) {
        model.addAttribute("responsablemunicipio", responsableMunicipioDAO.getResponsableMunicipio(identificador));
        return "responsablemunicipio/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("responsablemunicipio") ResponsableMunicipio responsableMunicipio,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "responsablemunicipio/update";
        responsableMunicipioDAO.updateResponsableMunicipio(responsableMunicipio);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{identificador}")
    public String processDelete(@PathVariable String identificador) {
        responsableMunicipioDAO.deleteResponsableMunicipio(identificador);
        return "redirect:../list";
    }
}
