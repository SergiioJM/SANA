package es.uji.ei1027.SANA.controller;


import es.uji.ei1027.SANA.dao.ControladorDAO;
import es.uji.ei1027.SANA.dao.ZonaDAO;
import es.uji.ei1027.SANA.model.Municipio;
import es.uji.ei1027.SANA.model.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/zona")
public class ZonaController {

    private ZonaDAO zonaDAO;

    @Autowired
    public void setZonaDAO(ZonaDAO zonaDAO) { this.zonaDAO = zonaDAO; }


    @RequestMapping("/list")
    public String listaDeZonas(Model model){
        model.addAttribute("zonas", zonaDAO.getZonas());
        return "zona/list";
    }
    @RequestMapping(value="/add")
    public String addZona(Model model) {
        model.addAttribute("zona", new Zona());
        return "zona/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("zona") Zona zona,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "zona/add";
        zonaDAO.addZona(zona);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editZona(Model model, @PathVariable String identificador) {
        model.addAttribute("zona", zonaDAO.getZona(identificador));
        return "zona/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("zona") Zona zona,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "zona/update";
        zonaDAO.updateZona(zona);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{cp}")
    public String processDelete(@PathVariable String cp) {
        zonaDAO.deleteZona(cp);
        return "redirect:../list";
    }
}
