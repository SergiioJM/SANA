package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.MunicipioDAO;
import es.uji.ei1027.SANA.model.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/municipio")
public class MunicipioController {

    private MunicipioDAO municipioDAO;

    @Autowired
    public void setMunicipioDAO(MunicipioDAO municipioDAO) {
        this.municipioDAO = municipioDAO;
    }

    @RequestMapping("/list")
    public String listaDeMunicipios(Model model){
        model.addAttribute("municipios", municipioDAO.getMunicipios());
        return "municipio/list";
    }
}
