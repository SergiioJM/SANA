package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.*;
import es.uji.ei1027.SANA.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/area")

public class AreaController {






    private AreaDAO areaDao;
    private MunicipioDAO municipioDAO;
    private ZonaReservadaDAO zonaReservadaDAO;


    private ZonaDAO zonaDAO;

    @Autowired
    public void setZonaReservadaDAO(ZonaReservadaDAO zonaReservadaDAO) {
        this.zonaReservadaDAO = zonaReservadaDAO;
    }
    @Autowired
    public void setZonaDAO(ZonaDAO zonaDAO) {
        this.zonaDAO = zonaDAO;
    }

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
    @RequestMapping("/zonasarea/{area}")
    public String listaDeZonasenAreas(@PathVariable int area,Model model){
        model.addAttribute("zonas", areaDao.getZonasArea(area));
        return "area/zonasarea";
    }




    @RequestMapping("/zonasAreaDeterminada/{area}")
    public String addZonasDeterminadas(@PathVariable("area") int area,Model model,HttpSession session){
        model.addAttribute("fecha",new Reserva());
        model.addAttribute("zonas", areaDao.getZonasArea(area));
        session.setAttribute("idArea" , area);
        return "area/zonasAreaDeterminada"  ;
    }

    @RequestMapping(value= "/zonasAreaDeterminada",  method=RequestMethod.POST)
    public String listaDeZonasenAreasDeterminada(@ModelAttribute("fecha") Reserva reserva,
                                                 Model model ,HttpSession session){

        int area = (int) session.getAttribute("idArea");
        List<ZonaReservada> reservaZona = zonaReservadaDAO.getZonaReservada3(area,reserva.getFecha());/**reservas en una determinada zona*/
        List<Zona> zonasFechaDeterminada = new ArrayList<>();/**sacar las zonas reservadas en una fecha determinada*/


        for (ZonaReservada zonaReservada : reservaZona) {
            Zona añadirZona = zonaDAO.getZona(zonaReservada.getIdzona());
            zonasFechaDeterminada.add(añadirZona);
        }

        model.addAttribute("fecha",reserva.getFecha());
        model.addAttribute("zonas", zonasFechaDeterminada);
        return "area/zonasUnaFechaDeterminada";
    }





    @RequestMapping("/areasVisiblesCiudadanos")
    public String listaDeAreasCiudadano(Model model){
        model.addAttribute("areas", areaDao.getAreas());
        return "area/areasVisiblesCiudadano";
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
        List<Municipio> lista = municipioDAO.getMunicipios();
        ArrayList<String> nombres = new ArrayList<>();
        for(Municipio mun : lista)
            nombres.add(mun.getCp());
        model.addAttribute("municipiolista",nombres);
        return "area/update";
    }

    @RequestMapping(value="/popup/{idArea}", method = RequestMethod.GET)
    public String abrirPopup(Model model, @PathVariable int idArea, HttpSession session) {
        session.setAttribute("idArea", idArea);
        return "redirect:../list/#popup";
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

    @RequestMapping(value="/delete")
    public String processDelete(HttpSession session) {
        int idArea = (int) session.getAttribute("idArea");
        session.removeAttribute("idArea");
        areaDao.deleteArea(idArea);
        return "redirect:../list";
    }



}