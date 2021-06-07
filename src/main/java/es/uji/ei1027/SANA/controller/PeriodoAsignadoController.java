package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.AreaDAO;
import es.uji.ei1027.SANA.dao.ControladorDAO;
import es.uji.ei1027.SANA.dao.PeriodoAsignadoDAO;
import es.uji.ei1027.SANA.model.*;
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
@RequestMapping("/periodoAsignado")
public class PeriodoAsignadoController {

    private PeriodoAsignadoDAO periodoAsignadoDAO;
    private AreaDAO areaDAO;
    private ControladorDAO controladorDAO;

    @Autowired
    public void setPeriodoAsignadoDAO(PeriodoAsignadoDAO periodoAsignadoDAO) {
        this.periodoAsignadoDAO = periodoAsignadoDAO;
    }

    @Autowired
    public void setAreaDAO(AreaDAO areaDAO) {
        this.areaDAO=areaDAO;
    }

    @Autowired
    public void setControladorDao(ControladorDAO controladorDAO) {
        this.controladorDAO = controladorDAO;
    }


    @RequestMapping("/list")
    public String listaDePeriodosAsignados(Model model){
        model.addAttribute("periodosAsignados", periodoAsignadoDAO.getPeriodosAsignados());
        return "periodoAsignado/list";
    }

    @RequestMapping(value="/add")
    public String addPeriodoAsignado(Model model) {
        model.addAttribute("periodoAsignado", new PeriodoAsignado());
        List<Area> lista2 = areaDAO.getAreas();

        List<String> areas= periodoAsignadoDAO.getAreas();
        for(int i = 0; i< areas.size();i++)
            System.out.println(areas.get(i));
        if (areas.size()>0){
            model.addAttribute("arealista",areas);
        }

        ArrayList<Integer> lista = new ArrayList<>();
        for (Area e : lista2)
            lista.add(e.getIdArea());
        //model.addAttribute("arealista",lista);

        List<Controlador> lista3 = controladorDAO.getControladores();
        ArrayList<Integer> listaAux = new ArrayList<>();
        for (Controlador e : lista3)
            listaAux.add(e.getIdentificador());
        model.addAttribute("controladorlista",listaAux);

        return "periodoAsignado/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("periodoAsignado") PeriodoAsignado periodoAsignado,
                                   BindingResult bindingResult, Model model) {
        PeriodoAsignadoValidator periodoAsignadoValidator = new PeriodoAsignadoValidator();
        periodoAsignadoValidator.validate(periodoAsignado,bindingResult);
        if (bindingResult.hasErrors()) {
            List<Area> lista2 = areaDAO.getAreas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Area e : lista2)
                lista.add(e.getIdArea());
            model.addAttribute("arealista", lista);

            List<Controlador> lista3 = controladorDAO.getControladores();
            ArrayList<Integer> listaAux = new ArrayList<>();
            for (Controlador e : lista3)
                listaAux.add(e.getIdentificador());
            model.addAttribute("controladorlista", listaAux);

            return "periodoAsignado/add";
        }
        try {
            periodoAsignado.setArea(periodoAsignadoDAO.getIdentificadorArea(periodoAsignado.getNomArea()));
            periodoAsignadoDAO.addPeriodoAsignado(periodoAsignado);
        }
        catch (DuplicateKeyException e ) {
            throw new ClaveDuplicadaException("Ya existe el identificador " + periodoAsignado.getIdentificador()+ " para un periodo asignado", "CPduplicada");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editPeriodoAsignado(Model model, @PathVariable int identificador) {
        model.addAttribute("periodoAsignado", periodoAsignadoDAO.getPeriodoAsignado(identificador));
        List<Area> lista2 = areaDAO.getAreas();
        ArrayList<Integer> lista = new ArrayList<>();
        for (Area e : lista2)
            lista.add(e.getIdArea());
        model.addAttribute("arealista",lista);

        List<Controlador> lista3 = controladorDAO.getControladores();
        ArrayList<Integer> listaAux = new ArrayList<>();
        for (Controlador e : lista3)
            listaAux.add(e.getIdentificador());
        model.addAttribute("controladorlista",listaAux);

        return "periodoAsignado/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("periodoAsignado") PeriodoAsignado periodoAsignado,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Area> lista2 = areaDAO.getAreas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Area e : lista2)
                lista.add(e.getIdArea());
            model.addAttribute("arealista", lista);

            List<Controlador> lista3 = controladorDAO.getControladores();
            ArrayList<Integer> listaAux = new ArrayList<>();
            for (Controlador e : lista3)
                listaAux.add(e.getIdentificador());
            model.addAttribute("controladorlista", listaAux);

            return "periodoAsignado/update";
        }
        try {
            periodoAsignadoDAO.updatePeriodoAsignado(periodoAsignado);
        }
        catch (DuplicateKeyException e ){
            throw new ClaveDuplicadaException("Ya existe la clave primaria introducida","CPduplicada");
        }        return "redirect:list";
    }

    @RequestMapping(value="/delete/{identificador}")
    public String processDelete(@PathVariable int identificador) {
        periodoAsignadoDAO.deletePeriodoAsignado(identificador);
        return "redirect:../list";
    }
    @RequestMapping("/reservassuarea/{area}")
    public String listaDeReservasEnPeriodosAsignados(@PathVariable int area ,Model model){
        List<Integer> zonas= periodoAsignadoDAO.getZonasArea(area);
        List<Integer> resevaEnArea = new ArrayList<>();
        for(Integer e : zonas){
            List<Integer> a= periodoAsignadoDAO.getReservasDeUnaZona(e);
            for (Integer ide: a){
                if(!resevaEnArea.contains(ide)){
                    resevaEnArea.add(ide);
                }
            }
        }
        List<Reserva> res= new ArrayList<>();
        for (Integer w: resevaEnArea){
            Reserva reserva= periodoAsignadoDAO.getReserva(w);
            reserva.setListreserva(periodoAsignadoDAO.getZonasDeReserva(reserva.getIdentificador()));
            res.add(reserva);
        }
        model.addAttribute("listaDeReservasEnZona", res);
        return "periodoAsignado/reservassuarea";
    }
}
