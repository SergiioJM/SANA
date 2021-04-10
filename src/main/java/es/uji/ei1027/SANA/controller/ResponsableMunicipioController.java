package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.ResponsableMunicipioDAO;
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
@RequestMapping("/responsable")
public class ResponsableMunicipioController {

    private ResponsableMunicipioDAO responsableMunicipioDAO;

    @Autowired
    public void setResponsableMunicipioDAO(ResponsableMunicipioDAO responsableMunicipioDAO) {
        this.responsableMunicipioDAO = responsableMunicipioDAO;
    }

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("responsables", responsableMunicipioDAO.getResponsablesMunicipios());
        return "responsable/list";
    }

    @RequestMapping(value="/add")
    public String addResponsableMunicipio(Model model) {
        model.addAttribute("responsable", new ResponsableMunicipio());
        return "responsable/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("responsable") ResponsableMunicipio responsableMunicipio,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "responsable/add";
        responsableMunicipioDAO.addResponsableMunicipio(responsableMunicipio);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editResponsableMunicipio(Model model, @PathVariable String identificador) {
        model.addAttribute("responsable", responsableMunicipioDAO.getResponsableMunicipio(identificador));
        return "responsable/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("responsable") ResponsableMunicipio responsableMunicipio,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "responsable/update";
        responsableMunicipioDAO.updateResponsableMunicipio(responsableMunicipio);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{identificador}")
    public String processDelete(@PathVariable String identificador) {
        responsableMunicipioDAO.deleteResponsableMunicipio(identificador);
        return "redirect:../list";
    }
}
