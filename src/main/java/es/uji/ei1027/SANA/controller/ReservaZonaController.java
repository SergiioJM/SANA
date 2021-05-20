package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.ReservaZonaDAO;
import es.uji.ei1027.SANA.dao.ZonaDAO;
import es.uji.ei1027.SANA.model.ReservaZona;
import es.uji.ei1027.SANA.model.UserDetails;
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

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/reservazona")
public class ReservaZonaController {
    private ReservaZonaDAO reservaZonaDAO;
    private ZonaDAO zonaDAO;

    @Autowired
    public void setReservaZonaDAO(ReservaZonaDAO reservaZonaDAO) {
        this.reservaZonaDAO = reservaZonaDAO;
    }
    @Autowired
    public void setZonaDAO(ZonaDAO zonaDAO) {
        this.zonaDAO = zonaDAO;
    }

    @RequestMapping("/list")
    public String listaDeReservaZonas(Model model){
        model.addAttribute("reservazonas", reservaZonaDAO.getReservaZonas());
        return "reservazona/list";
    }

    @RequestMapping(value="/add/{id}/{cantidadPersonas}/{area}/{nif}")
    public String addArea(Model model, @PathVariable int id, @PathVariable int cantidadPersonas , @PathVariable String area,@PathVariable String nif,HttpSession session) {
        ReservaZona reservaZona= new ReservaZona();
        reservaZona.setReserva(id);
        reservaZona.setPersonas(cantidadPersonas);
        session.setAttribute("area",area);

        /*List<Zona> lista2 = zonaDAO.getZonas();
        ArrayList<String> lista = new ArrayList<>();
        for (Zona e : lista2)
            lista.add(e.getIdentificador());
        model.addAttribute("zonalista",lista);
         */
        model.addAttribute("zonalista",reservaZonaDAO.getZonasArea(area));
        model.addAttribute("reservazona", reservaZona);

        return "reservazona/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("reservazona") ReservaZona reservaZona,
                                   BindingResult bindingResult, Model model, HttpSession session) {
        int capacidadTotalSeleccionada = 0;
        String area= (String) session.getAttribute("area");
        session.removeAttribute("area");

        for (String zona: reservaZona.getZona().split(",")) {
            System.out.println(zona);
            zona=zona.split("#Capacidad:")[0];
            /** cogemos cada zona que ha seleccionado el usuario y creamos cada una de las reservas de las zonas **/
            int capacidadZona = zonaDAO.getZona(zona).getCapacidad();
            capacidadTotalSeleccionada +=capacidadZona;
        }

        ReservaZonaValidator reservaValidator =new ReservaZonaValidator();
        reservaValidator.validate(reservaZona , capacidadTotalSeleccionada ,bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("zonalista",reservaZonaDAO.getZonasArea(area));
            return "reservazona/add";
        }
        for (String zona: reservaZona.getZona().split(",")) {
            zona=zona.split("#Capacidad:")[0];
            ReservaZona reservaZona1= new ReservaZona();
            reservaZona1.setZona(zona);
            reservaZona1.setReserva(reservaZona.getReserva());
            reservaZonaDAO.addReservaZona(reservaZona1);
            Zona modificarzona=zonaDAO.getZona(zona);
            modificarzona.setOcupada(true);
            zonaDAO.updateZona(modificarzona);
        }
        UserDetails user= (UserDetails) session.getAttribute("user");
        return "redirect:../reserva/reservasciudadano/" + user.getNif();
    }

    @RequestMapping(value="/delete/{reserva}/{zona}")
    public String processDelete(@PathVariable int reserva,@PathVariable String zona) {
        Zona modificarzona=zonaDAO.getZona(zona);
        modificarzona.setOcupada(false);
        zonaDAO.updateZona(modificarzona);
       reservaZonaDAO.deleteReservaZona(reserva,zona);
       return "redirect:../../list";
    }
}
