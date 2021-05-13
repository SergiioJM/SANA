package es.uji.ei1027.SANA.controller;


import es.uji.ei1027.SANA.dao.AreaDAO;
import es.uji.ei1027.SANA.dao.ZonaDAO;
import es.uji.ei1027.SANA.model.Area;
import es.uji.ei1027.SANA.model.Zona;
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
@RequestMapping("/zona")
public class ZonaController {

    private ZonaDAO zonaDAO;
    private AreaDAO areaDAO;

    @Autowired
    public void setZonaDAO(ZonaDAO zonaDAO) { this.zonaDAO = zonaDAO; }

    @Autowired
    public void setAreaDAO(AreaDAO areaDAO) {
        this.areaDAO=areaDAO;
    }

    @RequestMapping("/list")
    public String listaDeZonas(Model model){
        model.addAttribute("zonas", zonaDAO.getZonas());
        return "zona/list";
    }
    @RequestMapping(value="/add")
    public String addZona(Model model) {
        model.addAttribute("zona", new Zona());
        List<Area> lista2 = areaDAO.getAreas();
        ArrayList<Integer> lista = new ArrayList<>();
        for (Area e : lista2)
            lista.add(e.getIdArea());
        model.addAttribute("arealista",lista);
        return "zona/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("zona") Zona zona,
                                   BindingResult bindingResult, Model model) {
        ZonaValidator zonaValidator =new ZonaValidator();
        zonaValidator.validate(zona,bindingResult);
        if (bindingResult.hasErrors()) {
            List<Area> lista2 = areaDAO.getAreas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Area e : lista2)
                lista.add(e.getIdArea());
            model.addAttribute("arealista", lista);
            return "zona/add";
        }
        zonaDAO.addZona(zona);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editZona(Model model, @PathVariable String identificador) {
        model.addAttribute("zona", zonaDAO.getZona(identificador));
        List<Area> lista2 = areaDAO.getAreas();
        ArrayList<Integer> lista = new ArrayList<>();
        for (Area e : lista2)
            lista.add(e.getIdArea());
        model.addAttribute("arealista",lista);
        return "zona/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("zona") Zona zona,
            BindingResult bindingResult, Model model) {
        ZonaValidator zonaValidator =new ZonaValidator();
        zonaValidator.validate(zona,bindingResult);
        if (bindingResult.hasErrors()) {
            List<Area> lista2 = areaDAO.getAreas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Area e : lista2)
                lista.add(e.getIdArea());
            model.addAttribute("arealista", lista);
            return "zona/update";
        }
        try {
            zonaDAO.updateZona(zona);
        }
        catch (DuplicateKeyException e ) {
            throw new ClaveDuplicadaException("Ya existe una zona con ese identificador: " + zona.getIdentificador(), "CPduplicada");
        }

        return "redirect:list";
    }

    @RequestMapping(value="/delete/{cp}")
    public String processDelete(@PathVariable String cp) {
        zonaDAO.deleteZona(cp);
        return "redirect:../list";
    }


}
