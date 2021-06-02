package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.ReservaDAO;
import es.uji.ei1027.SANA.dao.ReservaZonaDAO;
import es.uji.ei1027.SANA.dao.ZonaDAO;
import es.uji.ei1027.SANA.dao.ZonaReservadaDAO;
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

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/reservazona")
public class ReservaZonaController {
    private ReservaZonaDAO reservaZonaDAO;
    private ZonaDAO zonaDAO;
    private ReservaDAO reservaDAO;
    private ZonaReservadaDAO zonaReservadaDAO;

    @Autowired
    public void setReservaZonaDAO(ReservaZonaDAO reservaZonaDAO) {
        this.reservaZonaDAO = reservaZonaDAO;
    }
    @Autowired
    public void setZonaDAO(ZonaDAO zonaDAO) {
        this.zonaDAO = zonaDAO;
    }
    @Autowired
    public void setReservaDAO(ReservaDAO reservaDAO) {
        this.reservaDAO=reservaDAO;
    }
    @Autowired
    public void setZonaReservadaDAO(ZonaReservadaDAO zonaReservadaDAO) {
        this.zonaReservadaDAO=zonaReservadaDAO;
    }

    @RequestMapping("/list")
    public String listaDeReservaZonas(Model model){
        model.addAttribute("reservazonas", reservaZonaDAO.getReservaZonas());
        return "reservazona/list";
    }

    @RequestMapping(value="/add/{id}/{cantidadPersonas}/{area}")
    public String addArea(Model model, @PathVariable int id, @PathVariable int cantidadPersonas ,
                          @PathVariable String area,HttpSession session) {

        ReservaZona reservaZona= new ReservaZona();
        reservaZona.setReserva(id);
        reservaZona.setPersonas(cantidadPersonas);
        reservaZona.setArea(area);
        session.setAttribute("area",area);
        Reserva reserva= (Reserva) session.getAttribute("reserva");
        session.setAttribute("fecha",reserva.getFecha());

        //De el area seleccionada, que zonas si que tienen ya alguna reserva
        List<ZonaReservada> zonascojidas= zonaReservadaDAO.getZonaReservada3(zonaReservadaDAO.getArea(area),reserva.getFecha());
        List<String> zonascojidas2= new ArrayList<>();
        for (ZonaReservada e: zonascojidas){
            zonascojidas2.add(e.getIdzona());
        }
        List<String> zonastotales=reservaZonaDAO.getZonasArea(area);
        List<String> zonasdisponibles= new ArrayList<>();
        for(String e: zonastotales){
            String[] z= e.split("#Capacidad:");
            if (!zonascojidas2.contains(z[0]))
                zonasdisponibles.add(e);
        }
        model.addAttribute("reservazona", reservaZona);
        UserDetails user= (UserDetails) session.getAttribute("user");
        model.addAttribute("nif",user.getNif());

        if (zonasdisponibles.size()>0) {
            model.addAttribute("zonalista", zonasdisponibles);
            return "reservazona/add";
        }
        else
            return "reserva/noreserva";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("reservazona") ReservaZona reservaZona,
                                   BindingResult bindingResult, Model model, HttpSession session) {
        int capacidadTotalSeleccionada = 0;
        //cogemos de la sesion el id del area que queremos coger sus zonas
        String area= (String) session.getAttribute("area");


        if (reservaZona.getZona() != null){//Comprobamos que ha seleccionado almenos una zona
            // recorremos las zonas como tambien tenemos la capacidad hacemos los dos split para sumar las capacidades deseadas
            for (String zona: reservaZona.getZona().split(",")) {
                zona=zona.split("#Capacidad:")[0];
                int capacidadZona = zonaDAO.getZona(zona).getCapacidad();
                capacidadTotalSeleccionada +=capacidadZona;
            }
        }
        //validamos que la capacidad deseada y las zonas escogidas concuerdan
        ReservaZonaValidator reservaValidator =new ReservaZonaValidator();
        reservaValidator.validate(reservaZona , capacidadTotalSeleccionada ,bindingResult);
        if (bindingResult.hasErrors()) {
            List<ZonaReservada> zonascojidas= zonaReservadaDAO.getZonaReservada3(zonaReservadaDAO.getArea(area), (LocalDate) session.getAttribute("fecha"));
            session.removeAttribute("fecha");
            List<String> zonascojidas2= new ArrayList<>();
            for (ZonaReservada e: zonascojidas){
                zonascojidas2.add(e.getIdzona());
            }
            List<String> zonastotales=reservaZonaDAO.getZonasArea(area);
            List<String> zonasdisponibles= new ArrayList<>();
            for(String e: zonastotales){
                String[] z= e.split("#Capacidad:");
                if (!zonascojidas2.contains(z[0]))
                    zonasdisponibles.add(e);
            }
            if (zonasdisponibles.size()>0) {
                model.addAttribute("zonalista", zonasdisponibles);
                return "reservazona/add";
            }
            else
                return "reserva/noreserva";
        }

        if (reservaZona.getZona() != null){//Comprobamos que ha seleccionado almenos una zona
            // ponemos todas las zonas escogidas a true para que no se puedan seleccionar en esa franja
            for (String zona: reservaZona.getZona().split(",")) {
                zona=zona.split("#Capacidad:")[0];
                ReservaZona reservaZona1= new ReservaZona();
                reservaZona1.setZona(zona);
                reservaZona1.setReserva(reservaZona.getReserva());
                reservaZonaDAO.addReservaZona(reservaZona1);

            }
            session.removeAttribute("area");

        }
        Reserva reserva= (Reserva) session.getAttribute("reserva");
        session.removeAttribute("reserva");
        reservaDAO.addReserva(reserva);
        for (String zona: reservaZona.getZona().split(",")) {
            zona=zona.split("#Capacidad:")[0];
            ZonaReservada zonaReservada = new ZonaReservada(zonaReservadaDAO.getArea(area),zona,reserva.getFecha(),reserva.getHora());
            zonaReservadaDAO.addZonaReservada(zonaReservada);
        }
        UserDetails user= (UserDetails) session.getAttribute("user");
        model.addAttribute("nif",user.getNif());
        return "redirect:../reserva/reservasciudadano/" + user.getNif();
    }

    @RequestMapping(value="/delete/{reserva}/{zona}")
    public String processDelete(@PathVariable int reserva,@PathVariable String zona) {
        List<String> zonas =reservaZonaDAO.getReservaZona(reserva);
        if(zonas.size() > 1){
            reservaZonaDAO.deleteReservaZona(reserva,zona);
        }

       return "redirect:/reserva/listadodetallado/" + reserva;
    }
}
