package es.uji.ei1027.SANA.controller;


import es.uji.ei1027.SANA.dao.PeriodoDAO;
import es.uji.ei1027.SANA.model.Periodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/periodo")
public class PeriodoController {

    private PeriodoDAO periodoDAO;

    @Autowired
    public void setPeriodoDAO(PeriodoDAO periodoDAO) {
        this.periodoDAO = periodoDAO;
    }

    @RequestMapping("/list")
    public String listaDePeriodos(Model model){
        model.addAttribute("periodos", periodoDAO.getPeriodos());
        return "periodo/list";
    }

    @RequestMapping(value="/add")
    public String addPeriodo(Model model) {
        model.addAttribute("periodo", new Periodo());
        return "periodo/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("periodo") Periodo periodo,
                                   BindingResult bindingResult) {
        PeriodoValidator periodoValidator = new PeriodoValidator();
        periodoValidator.validate(periodo,bindingResult);
        if (bindingResult.hasErrors())
            return "periodo/add";
        try {
            periodoDAO.addPeriodo(periodo);
        }
        catch (DuplicateKeyException e ){
            throw new ClaveDuplicadaException("Ya existe el identificador " + periodo.getIdentificador() + " para un periodo","CPduplicada");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/update/{identificador}", method = RequestMethod.GET)
    public String editPeriodo(Model model, @PathVariable String identificador) {
        model.addAttribute("periodo", periodoDAO.getPeriodo(identificador));
        return "periodo/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("periodo") Periodo periodo,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "periodo/update";
        periodoDAO.updatePeriodo(periodo);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{identificador}")
    public String processDelete(@PathVariable String identificador) {
        periodoDAO.deletePeriodo(identificador);
        return "redirect:../list";
    }

}
