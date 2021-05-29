package es.uji.ei1027.SANA.controller;


import es.uji.ei1027.SANA.dao.AreaDAO;
import es.uji.ei1027.SANA.dao.ZonaDAO;
import es.uji.ei1027.SANA.dao.ZonaReservadaDAO;
import es.uji.ei1027.SANA.model.Area;
import es.uji.ei1027.SANA.model.Zona;
import es.uji.ei1027.SANA.model.ZonaReservada;
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
@RequestMapping("/zonaReservada")
public class ZonaReservadaController {

    private ZonaReservadaDAO zonaReservadaDAO;
    private AreaDAO areaDAO;

    @Autowired
    public void setZonaReservadaDAO(ZonaReservadaDAO zonaReservadaDAO) { this.zonaReservadaDAO = zonaReservadaDAO; }

    @Autowired
    public void setAreaDAO(AreaDAO areaDAO) {
        this.areaDAO=areaDAO;
    }

    @RequestMapping("/list")
    public String listaDeZonas(Model model){
        model.addAttribute("zonasReservadas", zonaReservadaDAO.getZonasReservadas());
        return "zonaReservada/list";
    }
    @RequestMapping(value="/add")
    public String addZona(Model model) {
        model.addAttribute("zonaReservada", new ZonaReservada());
        List<Area> lista2 = areaDAO.getAreas();
        ArrayList<Integer> lista = new ArrayList<>();
        for (Area e : lista2)
            lista.add(e.getIdArea());
        model.addAttribute("arealista",lista);
        return "zonaReservada/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("zonaReservada") ZonaReservada zonaReservada,
                                   BindingResult bindingResult, Model model) {
        /*ZonaValidator zonaValidator =new ZonaValidator();
        zonaValidator.validate(zona,bindingResult);

         */
        if (bindingResult.hasErrors()) {
            List<Area> lista2 = areaDAO.getAreas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Area e : lista2)
                lista.add(e.getIdArea());
            model.addAttribute("arealista", lista);
            return "zonaReservada/add";
        }
        zonaReservadaDAO.addZonaReservada(zonaReservada);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editZona(Model model, @PathVariable int identificador) {
        model.addAttribute("zonaReservada", zonaReservadaDAO.getZonaReservada(identificador));
        List<Area> lista2 = areaDAO.getAreas();
        ArrayList<Integer> lista = new ArrayList<>();
        for (Area e : lista2)
            lista.add(e.getIdArea());
        model.addAttribute("arealista",lista);
        return "zonaReservada/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("zonaReservada") ZonaReservada zonaReservada,
            BindingResult bindingResult, Model model) {
        /*ZonaValidator zonaValidator =new ZonaValidator();
        zonaValidator.validate(zona,bindingResult);

         */
        if (bindingResult.hasErrors()) {
            List<Area> lista2 = areaDAO.getAreas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Area e : lista2)
                lista.add(e.getIdArea());
            model.addAttribute("arealista", lista);
            return "zona/update";
        }
        try {
            zonaReservadaDAO.updateZonaReservada(zonaReservada);
        }
        catch (DuplicateKeyException e ) {
            throw new ClaveDuplicadaException("Ya existe una zona con ese identificador: " + zonaReservada.getIdentificador(), "CPduplicada");
        }

        return "redirect:list";
    }

    @RequestMapping(value="/delete/{identificador}")
    public String processDelete(@PathVariable int identificador) {
        zonaReservadaDAO.deleteZonaReservada(identificador);
        return "redirect:../list";
    }


}