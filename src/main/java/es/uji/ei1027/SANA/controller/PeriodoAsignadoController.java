package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.PeriodoAsignadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
