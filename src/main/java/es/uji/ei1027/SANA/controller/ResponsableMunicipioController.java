package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.MunicipioDAO;
import es.uji.ei1027.SANA.dao.ResponsableMunicipioDAO;
import es.uji.ei1027.SANA.model.Municipio;
import es.uji.ei1027.SANA.model.ResponsableMunicipio;
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
@RequestMapping("/responsable")
public class ResponsableMunicipioController {

    private ResponsableMunicipioDAO responsableMunicipioDAO;
    private MunicipioDAO MunicipioDAO;


    @Autowired
    public void setResponsableMunicipioDAO(ResponsableMunicipioDAO responsableMunicipioDAO) {
        this.responsableMunicipioDAO = responsableMunicipioDAO;
    }
    @Autowired
    public void setMunicipioDAO(MunicipioDAO MunicipioDAO) {
        this.MunicipioDAO = MunicipioDAO;
    }

    @RequestMapping("/list")
    public String listaResponsables(Model model){
        model.addAttribute("responsables", responsableMunicipioDAO.getResponsablesMunicipios());
        return "responsable/list";
    }

    @RequestMapping(value="/add")
    public String addResponsableMunicipio(Model model) {
        model.addAttribute("responsable", new ResponsableMunicipio());

        List<Municipio> lista2 = MunicipioDAO.getMunicipios();
        ArrayList<String> lista = new ArrayList<>();
        for (Municipio e : lista2) {
            lista.add(e.getCp());

        }
        model.addAttribute("municipioslista",lista);

        return "responsable/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("responsable") ResponsableMunicipio responsableMunicipio,
                                   @ModelAttribute("municipioslista") List<String> municipios,
                                   BindingResult bindingResult) {
        for (String e: municipios){
            System.out.println("entra");
            System.out.println(e);
        }
        ResponsableMunicipioValidator responsableMunicipioValidator= new ResponsableMunicipioValidator(MunicipioDAO);
        responsableMunicipioValidator.validate(responsableMunicipio,bindingResult);
        if (bindingResult.hasErrors()) {
            return "responsable/add";

        }try {
            responsableMunicipioDAO.addResponsableMunicipio(responsableMunicipio);
        }
        catch (DuplicateKeyException e ){
            throw new ClaveDuplicadaException("Ya existe el identificador " + responsableMunicipio.getIdentificador() + " para un responsable","CPduplicada");
        }
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
        ResponsableMunicipioValidator responsableMunicipioValidator= new ResponsableMunicipioValidator(MunicipioDAO);
        responsableMunicipioValidator.validate(responsableMunicipio,bindingResult);
        responsableMunicipioDAO.updateResponsableMunicipio(responsableMunicipio);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{identificador}")
    public String processDelete(@PathVariable String identificador) {
        responsableMunicipioDAO.deleteResponsableMunicipio(identificador);
        return "redirect:../list";
    }
}
