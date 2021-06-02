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
import java.util.List;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    private ReservaDAO reservaDAO;
    private ZonaDAO zonaDAO;
    private ReservaZonaDAO reservaZonaDAO;
    private FranjaHorariaDAO franjaHorariaDAO;
    private ReservaZonaController reservaZonaController;
    private CiudadanoDAO ciudadanoDAO;

    @Autowired
    public void setReservaDAO(ReservaDAO reservaDAO) {
        this.reservaDAO = reservaDAO;
    }
    @Autowired
    public void setFranjaHorariaDAO(FranjaHorariaDAO franjaHorariaDAO) {
        this.franjaHorariaDAO=franjaHorariaDAO;
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

    @RequestMapping(value="/add/{nif}")
    public String addReservaNif(Model model, @PathVariable String nif) {
        Reserva r = new Reserva();
        r.setCiudadano(nif);
        model.addAttribute("nif",nif);
        model.addAttribute("reserva", r);
        List<String> municipios=reservaDAO.getMunicipios();
        if (municipios.size()>0) {
            model.addAttribute("listamunicipios", municipios);
            return "reserva/add0";
        }
        else
            return "reserva/noreserva";
    }
    @RequestMapping(value="/add0", method= RequestMethod.POST)
    public String processAddSubmit0(@ModelAttribute("reserva") Reserva reserva,
                                   BindingResult bindingResult,Model model,HttpSession session) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        reserva.setCiudadano(user.getNif());
        reserva.setEstado("disponible"); // Cada vez que añadimos siempre el estado
        ReservaValidatorAdd reservaValidatorAdd = new ReservaValidatorAdd();
        reservaValidatorAdd.validate(reserva, bindingResult);
        if (bindingResult.hasErrors()) {
            List<String> municipios=reservaDAO.getMunicipios();
            if (municipios.size()>0) {
                model.addAttribute("listamunicipios", municipios);
                return "reserva/add0";
            }
            else
                return "reserva/noreserva";
        }
        reserva.setListreserva(reservaDAO.getZonasDeReserva(reserva.getIdentificador()));
        reserva.setIdentificador(reservaDAO.obtenerR());
        session.setAttribute("reserva", reserva);

        //reservaDAO.addReserva(reserva);
        return "redirect:../reserva/add1";
        //return "redirect:../reservazona/add/" + reserva.getIdentificador() + "/"+ reserva.getNumeroPersonas() + "/"+ reserva.getArea() +"/" + user.getNif();
    }

    @RequestMapping(value="/add1")
    public String addReservaNif1(Model model,HttpSession session) {
        Reserva reserva= (Reserva) session.getAttribute("reserva");
        session.removeAttribute("reserva");
        UserDetails user= (UserDetails) session.getAttribute("user");
        model.addAttribute("nif",user.getNif());
        model.addAttribute("reserva", reserva);
        List<String> areas= reservaDAO.getAreas(reserva.getMunicipio());
        if (areas.size()>0){
            model.addAttribute("listaarea",areas);
            return "reserva/add1";
        }
        else
            return "reserva/noreserva";
    }

    @RequestMapping(value="/add1", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("reserva") Reserva reserva,
                                   BindingResult bindingResult,Model model,HttpSession session) {
        UserDetails user= (UserDetails) session.getAttribute("user");
        reserva.setCiudadano(user.getNif());
        reserva.setEstado("disponible"); // Cada vez que añadimos siempre el estado
        ReservaValidatorAdd reservaValidatorAdd =new ReservaValidatorAdd();
        reservaValidatorAdd.validate(reserva,bindingResult);
        if (bindingResult.hasErrors()) {
            List<String> areas= reservaDAO.getAreas(reserva.getMunicipio());
            if (areas.size()>0){
                model.addAttribute("listaarea",areas);
                return "reserva/add1";
            }
            else
                return "reserva/noreserva";
        }
        reserva.setListreserva(reservaDAO.getZonasDeReserva(reserva.getIdentificador()));
        reserva.setIdentificador(reservaDAO.obtenerR());
        session.setAttribute("reserva",reserva);

        //reservaDAO.addReserva(reserva);
        return "redirect:../reserva/add2";
        //return "redirect:../reservazona/add/" + reserva.getIdentificador() + "/"+ reserva.getNumeroPersonas() + "/"+ reserva.getArea() +"/" + user.getNif();

    }
    @RequestMapping(value="/add2")
    public String addReservaNif2(Model model,HttpSession session) {
        Reserva reserva= (Reserva) session.getAttribute("reserva");
        session.removeAttribute("reserva");
        UserDetails user= (UserDetails) session.getAttribute("user");
        model.addAttribute("nif",user.getNif());
        model.addAttribute("reserva", reserva);
        List<FranjaHoraria> franjas= franjaHorariaDAO.getFranjasHorariasDeArea(reserva.getArea());
        List<String> franjasfinales= new ArrayList<>();
        for (FranjaHoraria e: franjas){
            if (reserva.getFecha().isAfter(e.getFechaInicio()) && reserva.getFecha().isBefore(e.getFechaFin())){
                String res= e.getHoraInicio() + " - " + e.getHoraFin();
                franjasfinales.add(res);
            }
        }
        if (franjasfinales.size()>0) {
            model.addAttribute("franjas", franjasfinales);
            return "reserva/add2";
        }
        else
            return "reserva/noreserva";
    }
    @RequestMapping(value="/add2", method= RequestMethod.POST)
    public String processAddSubmit2(@ModelAttribute("reserva") Reserva reserva,
                                   BindingResult bindingResult,Model model,HttpSession session) {
        UserDetails user= (UserDetails) session.getAttribute("user");
        reserva.setCiudadano(user.getNif());
        reserva.setEstado("disponible"); // Cada vez que añadimos siempre el estado
        ReservaValidatorAdd reservaValidatorAdd =new ReservaValidatorAdd();
        reservaValidatorAdd.validate(reserva,bindingResult);
        if (bindingResult.hasErrors()) {
            List<FranjaHoraria> franjas= franjaHorariaDAO.getFranjasHorariasDeArea(reserva.getArea());
            List<String> franjasfinales= new ArrayList<>();
            for (FranjaHoraria e: franjas){
                if (reserva.getFecha().isAfter(e.getFechaInicio()) && reserva.getFecha().isBefore(e.getFechaFin())){
                    String res= e.getHoraInicio() + " - " + e.getHoraFin();
                    franjasfinales.add(res);
                }
            }
            if (franjasfinales.size()>0) {
                model.addAttribute("franjas", franjasfinales);
                return "reserva/add2";
            }
            else
                return "reserva/noreserva";
        }
        reserva.setIdentificador(reservaDAO.obtenerR());
        System.out.println(reserva.getIdentificador());
        reserva.setListreserva(reservaDAO.getZonasDeReserva(reserva.getIdentificador()));
        session.setAttribute("reserva",reserva);

        //reservaDAO.addReserva(reserva);
        return "redirect:../reservazona/add/" + reserva.getIdentificador() + "/"+ reserva.getNumeroPersonas() + "/"+ reserva.getArea();
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editReserva(Model model, @PathVariable int identificador, HttpSession session) {
        model.addAttribute("reserva", reservaDAO.getReserva(identificador));
        UserDetails user= (UserDetails) session.getAttribute("user");
        model.addAttribute("nif", user.getNif());
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
        reserva.setCiudadano(user.getNif());
        reserva.setListreserva(reservaDAO.getZonasDeReserva(reserva.getIdentificador()));
        int capacidadTotalZonas=reservaDAO.cantidadZonas(reserva.getListreserva());
        ReservaValidatorUpdate reservaValidatorAdd =new ReservaValidatorUpdate();
        ReservaCantidad reservaCantidad= new ReservaCantidad(reserva,capacidadTotalZonas);
        reservaValidatorAdd.validate(reservaCantidad,bindingResult);


        if (bindingResult.hasErrors()) {
            List<Zona> lista2 = zonaDAO.getZonas();
            ArrayList<String> lista = new ArrayList<>();
            for (Zona e : lista2)
                lista.add(e.getIdentificador());
            model.addAttribute("zonalista", lista);
            return "reserva/update";
        }

        reservaDAO.updateReserva(reserva);

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
        List<String> zonasreserva=reservaZonaDAO.getReservaZona(identificador);
        reservaDAO.deleteReserva(identificador);
        return "redirect:../reservasciudadano/" + user.getNif();
    }

    @RequestMapping(value="/popup/{identificador}", method = RequestMethod.GET)
    public String abrirPopup(Model model, @PathVariable int identificador, HttpSession session) {
        session.setAttribute("idReserva", identificador);
        UserDetails user= (UserDetails) session.getAttribute("user");
        return "redirect:../reservasciudadano/" + user.getNif() + "#popup";
    }

    @RequestMapping(value="/cancelar")
    public String processCancelar(HttpSession session) {
        int identificador = (int) session.getAttribute("idReserva");
        session.removeAttribute("idReserva");
        UserDetails user= (UserDetails) session.getAttribute("user");
        List<String> zonasreserva=reservaZonaDAO.getReservaZona(identificador);
        Reserva reserva= reservaDAO.getReserva(identificador);
        reserva.setEstado("cancelada");
        for (String e: zonasreserva)
            reservaDAO.eliminarZonasReservadas(reserva.getFecha(),reserva.getHora(),e);
        reservaDAO.updateReserva(reserva);
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