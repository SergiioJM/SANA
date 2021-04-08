package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.MunicipalityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/municipality")
public class MunicipalityController {

    private MunicipalityDAO municipalityDAO;

    @Autowired
    public void setMunicipalityDAO(MunicipalityDAO municipalityDAO) {
        this.municipalityDAO = municipalityDAO;
    }

    @RequestMapping("/list")
    public String listaDeMunicipios(Model model){
        model.addAttribute("municipios", municipalityDAO.getMunicipios());
        return "municipality/list";
    }
}
