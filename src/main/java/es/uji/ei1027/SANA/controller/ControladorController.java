package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.AreaDAO;
import es.uji.ei1027.SANA.dao.ControladorDAO;
import es.uji.ei1027.SANA.model.Controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("controlador")

public class ControladorController {

    private ControladorDAO controladorDAO;

    @Autowired
    public void setControladorDAO(ControladorDAO controladorDAO) { this.controladorDAO = controladorDAO; }


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

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("controlador") Controlador controlador, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "controlador/add";
        //Date hoy= Date.from(Instant.now());
        //Controlador controlador1 = new Controlador("Co1","Manel","Pobla","manel@gmail.com",123456789,hoy,hoy);
        controladorDAO.addControlador(controlador);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editControlador(Model model, @PathVariable String identificador) {
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

    @RequestMapping(value="/delete/{identificador}")
    public String processDelete(@PathVariable String identificador) {
        controladorDAO.deleteControlador(identificador);
        return "redirect:../list";
    }

}
