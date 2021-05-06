package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.AreaDAO;
import es.uji.ei1027.SANA.dao.MunicipioDAO;
import es.uji.ei1027.SANA.model.Area;
import es.uji.ei1027.SANA.model.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/area")

public class AreaController {

    private AreaDAO areaDao;
    private MunicipioDAO municipioDAO;

    @Autowired
    public void setAreaDao(AreaDAO areaDao) {
        this.areaDao = areaDao;
    }

    @Autowired
    public void setMunicipioDAO(MunicipioDAO municipioDAO){ this.municipioDAO = municipioDAO; }

    @RequestMapping("/list")
    public String listaDeAreas(Model model){
        model.addAttribute("areas", areaDao.getAreas());
        return "area/list";
    }

    @RequestMapping(value="/add")
    public String addArea(Model model) {
        model.addAttribute("area", new Area());
        List<Municipio> lista = municipioDAO.getMunicipios();
        ArrayList<String> nombres = new ArrayList<>();
        for(Municipio mun : lista)
            nombres.add(mun.getCp());
        model.addAttribute("municipiolista",nombres);
        return "area/add";
    }
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("area") Area area,
                                   BindingResult bindingResult) {
        AreaValidator areaValidator = new AreaValidator();
        areaValidator.validate(area, bindingResult);
        if (bindingResult.hasErrors())
        return "area/add";
        areaDao.addArea(area);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{idArea}", method = RequestMethod.GET)
    public String editArea(Model model, @PathVariable int idArea) {
        model.addAttribute("area", areaDao.getArea(idArea));
        return "area/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("area") Area area,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "area/update";
        areaDao.updateArea(area);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{idArea}")
    public String processDelete(@PathVariable int idArea) {
        areaDao.deleteArea(idArea);
        return "redirect:../list";
    }
}