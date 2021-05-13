package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.ReservaDAO;
import es.uji.ei1027.SANA.dao.ReservaZonaDAO;
import es.uji.ei1027.SANA.dao.ZonaDAO;
import es.uji.ei1027.SANA.model.Reserva;
import es.uji.ei1027.SANA.model.Zona;
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
@RequestMapping("/reserva")
public class ReservaController {

    private ReservaDAO reservaDAO;
    private ZonaDAO zonaDAO;
    private ReservaZonaDAO reservaZonaDAO;
    private ReservaZonaController reservaZonaController;

    @Autowired
    public void setReservaDAO(ReservaDAO reservaDAO) {
        this.reservaDAO = reservaDAO;
    }
    @Autowired
    public void setReservaZonaDAO(ReservaZonaDAO reservaZonaDAO) {
        this.reservaZonaDAO = reservaZonaDAO;
    }
    @Autowired
    public void setZonaDAO(ZonaDAO zonaDAO) {
        this.zonaDAO = zonaDAO;
    }
    @Autowired
    public void setReservaZonaController(ReservaZonaController reservaZonaController) {
        this.reservaZonaController = reservaZonaController;
    }

    @RequestMapping("/list")
    public String listaDeReservas(Model model){
        model.addAttribute("reservas", reservaDAO.getReservas());
        return "reserva/list";
    }

    @RequestMapping(value="/add")
    public String addReserva(Model model) {
        model.addAttribute("reserva", new Reserva());
        List<Zona> lista2 = zonaDAO.getZonas();
        ArrayList<Integer> lista = new ArrayList<>();
        for (Zona e : lista2)
            lista.add(e.getIdentificador());
        model.addAttribute("zonalista",lista);
        return "reserva/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("reserva") Reserva reserva,
                                   BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            List<Zona> lista2 = zonaDAO.getZonas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Zona e : lista2)
                lista.add(e.getIdentificador());
            model.addAttribute("zonalista", lista);
            return "reserva/add";
        }
        reserva.setListreserva(reservaDAO.getZonasDeReserva(reserva.getIdentificador()));
        reservaDAO.addReserva(reserva);
        //return "redirect:list";
        return "redirect:../reservazona/add/"+ reserva.getIdentificador();
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editReserva(Model model, @PathVariable int identificador) {
        model.addAttribute("reserva", reservaDAO.getReserva(identificador));
        List<Zona> lista2 = zonaDAO.getZonas();
        ArrayList<Integer> lista = new ArrayList<>();
        for (Zona e : lista2)
            lista.add(e.getIdentificador());
        model.addAttribute("zonalista",lista);
        return "reserva/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("reserva") Reserva reserva,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Zona> lista2 = zonaDAO.getZonas();
            ArrayList<Integer> lista = new ArrayList<>();
            for (Zona e : lista2)
                lista.add(e.getIdentificador());
            model.addAttribute("zonalista", lista);
            return "reserva/update";
        }

        reservaDAO.updateReserva(reserva);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{identificador}")
    public String processDelete(@PathVariable int identificador) {
        reservaDAO.deleteReserva(identificador);
        return "redirect:../list";
    }
}
