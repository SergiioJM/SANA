package es.uji.ei1027.SANA.controller;


import es.uji.ei1027.SANA.dao.ControladorDAO;
import es.uji.ei1027.SANA.dao.ZonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
