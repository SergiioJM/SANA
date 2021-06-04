package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.ResponsableMedioAmbienteDAO;
import es.uji.ei1027.SANA.model.Municipio;
import es.uji.ei1027.SANA.model.ResponsableMedioAmbiente;
import es.uji.ei1027.SANA.model.ResponsableMunicipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/responsableMedioAmbiente")
public class ResponsableMedioAmbienteContoller {

    private ResponsableMedioAmbienteDAO responsableMedioAmbienteDAO;

    @Autowired
    public void setResponsableMedioAmbienteDAO(ResponsableMedioAmbienteDAO responsableMedioAmbienteDAO){ this.responsableMedioAmbienteDAO = responsableMedioAmbienteDAO; }

    @RequestMapping("/list")
    public String listaResponsables(Model model){
        model.addAttribute("responsables", responsableMedioAmbienteDAO.getResponsablesMedioAmbiente());
        return "responsableMedioAmbiente/list";
    }

    @RequestMapping(value="/add")
    public String addResponsableMedioAmbiente(Model model) {
        model.addAttribute("responsableMedioAmbiente", new ResponsableMedioAmbiente());
        return "responsableMedioAmbiente/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("responsableMedioAmbiente") ResponsableMedioAmbiente responsableMedioAmbiente,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "responsableMedioAmbiente/add";


        }try {
            responsableMedioAmbienteDAO.addResponsableMedioAmbiente(responsableMedioAmbiente);
        }
        catch (DuplicateKeyException e ){
            throw new ClaveDuplicadaException("Ya existe el usuario " + responsableMedioAmbiente.getUsuario() + " para un responsable","CPduplicada");
        }
        return "redirect:list";
    }
}
