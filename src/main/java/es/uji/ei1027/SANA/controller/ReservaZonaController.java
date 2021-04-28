package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.AreaDAO;
import es.uji.ei1027.SANA.dao.ReservaZonaDAO;
import es.uji.ei1027.SANA.model.Area;
import es.uji.ei1027.SANA.model.ReservaZona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/reservazona")
public class ReservaZonaController {
    private ReservaZonaDAO reservaZonaDAO;

    @Autowired
    public void setReservaZonaDAO(ReservaZonaDAO reservaZonaDAO) {
        this.reservaZonaDAO = reservaZonaDAO;
    }

    @RequestMapping("/list")
    public String listaDeReservaZonas(Model model){
        model.addAttribute("reservazonas", reservaZonaDAO.getReservaZonas());
        return "reservazona/list";
    }

    @RequestMapping(value="/add")
    public String addArea(Model model) {
        model.addAttribute("reservazona", new ReservaZona());
        return "reservazona/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("reservazona") ReservaZona reservaZona,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "reservazona/add";
        reservaZonaDAO.addReservaZona(reservaZona);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{reserva}/{zona}")
    public String processDelete(@PathVariable String reserva,@PathVariable String zona) {
       reservaZonaDAO.deleteReservaZona(reserva,zona);
        return "redirect:../../list";
    }
}
