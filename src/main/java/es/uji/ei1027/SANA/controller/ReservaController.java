package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.CiudadanoDAO;
import es.uji.ei1027.SANA.dao.ReservaDAO;
import es.uji.ei1027.SANA.dao.ReservaZonaDAO;
import es.uji.ei1027.SANA.dao.ZonaDAO;
import es.uji.ei1027.SANA.model.Reserva;
import es.uji.ei1027.SANA.model.UserDetails;
import es.uji.ei1027.SANA.model.Zona;
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
import java.util.List;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    private ReservaDAO reservaDAO;
    private ZonaDAO zonaDAO;
    private ReservaZonaDAO reservaZonaDAO;
    private ReservaZonaController reservaZonaController;
    private CiudadanoDAO ciudadanoDAO;

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
    @Autowired
    public void setCiudadanoDAO(CiudadanoDAO ciudadanoDAO) { this.ciudadanoDAO = ciudadanoDAO; }

    @RequestMapping("/list")
    public String listaDeReservas(Model model){
        model.addAttribute("reservas", reservaDAO.getReservas());
        return "reserva/list";
    }

    @RequestMapping(value = "/reservasciudadano/{ciudadano}", method = RequestMethod.GET)
    public String listaDeReservasIndividual(Model model, @PathVariable String ciudadano){
        model.addAttribute("nif",ciudadano);
        model.addAttribute("reservas", reservaDAO.getReservasporCiudadano(ciudadano));
        return "reserva/reservasciudadano";
    }

    @RequestMapping(value="/add")
    public String addReserva(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("listaarea",reservaDAO.getAreas());
        return "reserva/add";
    }

    @RequestMapping(value="/add/{nif}")
    public String addReservaNif(Model model, @PathVariable String nif) {
        Reserva r = new Reserva();
        r.setCiudadano(nif);
        model.addAttribute("nif",nif);
        model.addAttribute("reserva", r);
        model.addAttribute("listaarea",reservaDAO.getAreas());
        return "reserva/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("reserva") Reserva reserva,
                                   BindingResult bindingResult,Model model) {

        ReservaValidator reservaValidator =new ReservaValidator();
        //reservaValidator.validate(reserva,zonaDAO,bindingResult);
        reservaValidator.validate(reserva,bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("listaarea",reservaDAO.getAreas());
            return "reserva/add";
        }
        reserva.setListreserva(reservaDAO.getZonasDeReserva(reserva.getIdentificador()));
        //estamos comprobando que la reserva no se pueda hacer si no hay sitio en la zona
        //int capacidadActual = zonaDAO.getZona(reserva.getZona()).getCapacidad();

        reservaDAO.addReserva(reserva);
        //zonaDAO.setZona(reserva.getZona(),capacidadActual-reserva.getNumeroPersonas());

        return "redirect:../reservazona/add/" + reserva.getIdentificador() + "/"+ reserva.getArea() +"/" + reserva.getCiudadano();
        //return "redirect:list";
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editReserva(Model model, @PathVariable int identificador) {
        model.addAttribute("reserva", reservaDAO.getReserva(identificador));
        List<Zona> lista2 = zonaDAO.getZonas();
        ArrayList<String> lista = new ArrayList<>();
        for (Zona e : lista2)
            lista.add(e.getIdentificador());
        model.addAttribute("zonalista",lista);
        return "reserva/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit( @ModelAttribute("reserva") Reserva reserva, BindingResult bindingResult, Model model,HttpSession session) {
        UserDetails user= (UserDetails) session.getAttribute("user");
        ReservaValidator reservaValidator =new ReservaValidator();
        //reservaValidator.validate(reserva,zonaDAO,bindingResult);
        reservaValidator.validate(reserva,bindingResult);
        if (bindingResult.hasErrors()) {
            List<Zona> lista2 = zonaDAO.getZonas();
            ArrayList<String> lista = new ArrayList<>();
            for (Zona e : lista2)
                lista.add(e.getIdentificador());
            model.addAttribute("zonalista", lista);
            return "reserva/update";
        }

        reservaDAO.updateReserva(reserva);
        //return "redirect:lis";
        return "redirect:../reserva/reservasciudadano/" + user.getNif();
    }
    @RequestMapping(value="/update2/{identificador}", method = RequestMethod.GET)
    public String editReserva2(Model model, @PathVariable int identificador) {
        model.addAttribute("reserva", reservaDAO.getReserva(identificador));
        List<Zona> lista2 = zonaDAO.getZonas();
        ArrayList<String> lista = new ArrayList<>();
        for (Zona e : lista2)
            lista.add(e.getIdentificador());
        model.addAttribute("zonalista",lista);
        return "reserva/update2";
    }

    @RequestMapping(value="/update2", method = RequestMethod.POST)
    public String processUpdate2Submit( @ModelAttribute("reserva") Reserva reserva, BindingResult bindingResult, Model model,HttpSession session) {
        UserDetails user= (UserDetails) session.getAttribute("user");
        if (bindingResult.hasErrors()) {
            List<Zona> lista2 = zonaDAO.getZonas();
            ArrayList<String> lista = new ArrayList<>();
            for (Zona e : lista2)
                lista.add(e.getIdentificador());
            model.addAttribute("zonalista", lista);
            return "reserva/update";
        }

        reservaDAO.updateReserva(reserva);
        //return "redirect:list";
        return "redirect:../reserva/reservasciudadano/" + user.getNif();
    }

    @RequestMapping(value="/delete/{identificador}")
    public String processDelete(@PathVariable int identificador,HttpSession session) {
        UserDetails user= (UserDetails) session.getAttribute("user");
        reservaDAO.deleteReserva(identificador);
        return "redirect:../reserva/reservasciudadano/" + user.getNif();
    }
    @RequestMapping("/listadodetallado/{reserva}")
    public String listaDeZonasDeReserva(@PathVariable int reserva, Model model, HttpSession session){
        model.addAttribute("reservazonas", reservaDAO.getReservaZonasDeMiReserva(reserva));
        UserDetails user= (UserDetails) session.getAttribute("user");
        model.addAttribute("nif",user.getNif());
        return "reserva/listadodetallado";
    }
}