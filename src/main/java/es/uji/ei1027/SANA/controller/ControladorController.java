package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.AreaDAO;
import es.uji.ei1027.SANA.dao.ControladorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
