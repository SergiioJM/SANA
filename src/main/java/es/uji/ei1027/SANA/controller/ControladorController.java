package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.ControladorDAO;
import es.uji.ei1027.SANA.model.Controlador;
import es.uji.ei1027.SANA.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/controlador")

public class ControladorController {

    private ControladorDAO controladorDAO;

    @Autowired
    public void setControladorDAO(ControladorDAO controladorDAO) {
        this.controladorDAO = controladorDAO;
    }


    @RequestMapping("/list")
    public String listaDeControladores(Model model){
        model.addAttribute("controladores", controladorDAO.getControladores());
        return "controlador/list";
    }

    @RequestMapping(value="/add")
    public String addControlador(Model model) {
        model.addAttribute("controlador", new Controlador());
        return "controlador/add";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("controlador") Controlador controlador,
                                   BindingResult bindingResult) {
        ControladorValidator controladorValidator = new ControladorValidator();
        controladorValidator.validate(controlador, bindingResult);
        if (bindingResult.hasErrors())
            return "controlador/add";
        try {
            controladorDAO.addControlador(controlador);
        }
        catch (DuplicateKeyException e ){
            throw new ClaveDuplicadaException("Ya existe el identificador " + controlador.getIdentificador(), "IDduplicado");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editControlador(Model model, @PathVariable int identificador) {
        model.addAttribute("controlador", controladorDAO.getControlador(identificador) );
        return "controlador/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("controlador") Controlador controlador,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "controlador/update";
        controladorDAO.updateControlador(controlador);
        return "redirect:list";
    }

    @RequestMapping(value="/popup/{identificador}", method = RequestMethod.GET)
    public String abrirPopup(Model model, @PathVariable int identificador, HttpSession session) {
        session.setAttribute("idControlador", identificador);
        return "redirect:../list#popup";
    }

    @RequestMapping(value="/delete")
    public String processDelete(HttpSession session) {
        int identificador = (int) session.getAttribute("idControlador");
        session.removeAttribute("idControlador");
        controladorDAO.deleteControlador(identificador);
        return "redirect:/controlador/list";
    }

}
