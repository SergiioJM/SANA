package es.uji.ei1027.SANA.controller;


import es.uji.ei1027.SANA.dao.AreaDAO;
import es.uji.ei1027.SANA.dao.FranjaHorariaDAO;
import es.uji.ei1027.SANA.model.Area;
import es.uji.ei1027.SANA.model.FranjaHoraria;
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
@RequestMapping("/periodo")
public class FranjaHorariaController {

    private FranjaHorariaDAO franjaHorariaDAO;
    private AreaDAO areaDAO;

    @Autowired
    public void setPeriodoDAO(FranjaHorariaDAO franjaHorariaDAO) {
        this.franjaHorariaDAO = franjaHorariaDAO;
    }

    @Autowired
    public void setAreaDAO(AreaDAO areaDAO) {
        this.areaDAO=areaDAO;
    }

    @RequestMapping("/list")
    public String listaDePeriodos(Model model){
        model.addAttribute("periodos", franjaHorariaDAO.getPeriodos());
        return "periodo/list";
    }

    @RequestMapping(value="/add")
    public String addPeriodo(Model model) {
        model.addAttribute("periodo", new FranjaHoraria());
        List<Area> lista2 = areaDAO.getAreas();
        ArrayList<Integer> lista = new ArrayList<>();
        for (Area e : lista2)
            lista.add(e.getIdArea());
        model.addAttribute("arealista",lista);
        return "periodo/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("periodo") FranjaHoraria franjaHoraria,
                                   BindingResult bindingResult, Model model) {
        FranjaHorariaValidator franjaHorariaValidator = new FranjaHorariaValidator();
        franjaHorariaValidator.validate(franjaHoraria,bindingResult);
        if (bindingResult.hasErrors()) {
            List<Area> lista2 = areaDAO.getAreas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Area e : lista2) {
                lista.add(e.getIdArea());

            }
            model.addAttribute("arealista", lista);
            return "periodo/add";
        }
        try {
            franjaHorariaDAO.addPeriodo(franjaHoraria);
        }
        catch (DuplicateKeyException e ){
            throw new ClaveDuplicadaException("Ya existe el identificador " + franjaHoraria.getIdentificador() + " para un periodo","CPduplicada");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editPeriodo(Model model, @PathVariable int identificador) {
        model.addAttribute("periodo", franjaHorariaDAO.getPeriodo(identificador));
        List<Area> lista2 = areaDAO.getAreas();
        ArrayList<Integer> lista = new ArrayList<>();
        for (Area e : lista2)
            lista.add(e.getIdArea());
        model.addAttribute("arealista",lista);
        return "periodo/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("periodo") FranjaHoraria franjaHoraria,
            BindingResult bindingResult, Model model) {
        FranjaHorariaValidator franjaHorariaValidator = new FranjaHorariaValidator();
        franjaHorariaValidator.validate(franjaHoraria,bindingResult);
        if (bindingResult.hasErrors()) {
            List<Area> lista2 = areaDAO.getAreas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Area e : lista2)
                lista.add(e.getIdArea());
            model.addAttribute("arealista",lista);
            return "periodo/update";
        }
            franjaHorariaDAO.updatePeriodo(franjaHoraria);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{identificador}")
    public String processDelete(@PathVariable int identificador) {
        franjaHorariaDAO.deletePeriodo(identificador);
        return "redirect:../list";
    }

}
