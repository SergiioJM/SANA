package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.PeriodoAsignadoDAO;
import es.uji.ei1027.SANA.model.PeriodoAsignado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/periodoAsignado")
public class PeriodoAsignadoController {

    private PeriodoAsignadoDAO periodoAsignadoDAO;

    @Autowired
    public void setPeriodoAsignadoDAO(PeriodoAsignadoDAO periodoAsignadoDAO) {
        this.periodoAsignadoDAO = periodoAsignadoDAO;
    }

    @RequestMapping("/list")
    public String listaDePeriodosAsignados(Model model){
        model.addAttribute("periodosAsignados", periodoAsignadoDAO.getPeriodosAsignados());
        return "periodoAsignado/list";
    }

    @RequestMapping(value="/add")
    public String addPeriodoAsignado(Model model) {
        model.addAttribute("periodoAsignado", new PeriodoAsignado());
        return "periodoAsignado/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("periodoAsignado") PeriodoAsignado periodoAsignado,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "municipio/add";
        periodoAsignadoDAO.addPeriodoAsignado(periodoAsignado);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editMunicipio(Model model, @PathVariable String identificador) {
        model.addAttribute("periodoAsignado", periodoAsignadoDAO.getPeriodoAsignado(identificador));
        return "periodoAsignado/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("periodoAsignado") PeriodoAsignado periodoAsignado,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "periodoAsignado/update";
        periodoAsignadoDAO.updatePeriodoAsignado(periodoAsignado);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{identificador}")
    public String processDelete(@PathVariable String identificador) {
        periodoAsignadoDAO.deletePeriodoAsignado(identificador);
        return "redirect:../list";
    }
}
