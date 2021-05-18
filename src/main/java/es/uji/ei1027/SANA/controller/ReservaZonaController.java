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
import java.util.ArrayList;
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

    @RequestMapping(value="/add/{id}/{area}/{nif}")
    public String addArea(Model model, @PathVariable int id,@PathVariable String area,@PathVariable String nif) {
        ReservaZona reservaZona= new ReservaZona();
        reservaZona.setReserva(id);
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
        System.out.println(reservaZona);
        for (String zona: reservaZona.getZona().split(",")) {
            ReservaZona reservaZona1= new ReservaZona();
            reservaZona1.setZona(zona);
            reservaZona1.setReserva(reservaZona.getReserva());
            ReservaZonaValidator reservaZonaValidator = new ReservaZonaValidator();
            reservaZonaValidator.validate(reservaZona1, bindingResult);
            if (bindingResult.hasErrors()) {
                /*
                List<Zona> lista2 = zonaDAO.getZonas();
                ArrayList<String> lista = new ArrayList<>();
                for (Zona e : lista2)
                    lista.add(e.getIdentificador());
                //model.addAttribute("zonalista", lista);
                //model.addAttribute("zonalista",zonalista);
                 */
                return "reservazona/add";

            }
            try {
                reservaZonaDAO.addReservaZona(reservaZona1);
            } catch (DuplicateKeyException e) {
                throw new ClaveDuplicadaException("Ya existe la reserva: " + reservaZona.getReserva(), "CPduplicada");
            }
        }
        UserDetails user= (UserDetails) session.getAttribute("user");
        return "redirect:../reserva/reservasciudadano/" + user.getNif();
    }

    @RequestMapping(value="/delete/{reserva}/{zona}")
    public String processDelete(@PathVariable int reserva,@PathVariable String zona) {
       reservaZonaDAO.deleteReservaZona(reserva,zona);
        return "redirect:../../list";
    }
}
