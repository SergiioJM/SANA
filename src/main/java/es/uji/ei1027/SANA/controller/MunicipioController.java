package es.uji.ei1027.SANA.controller;

import es.uji.ei1027.SANA.dao.MunicipioDAO;
import es.uji.ei1027.SANA.model.Area;
import es.uji.ei1027.SANA.model.Municipio;
import es.uji.ei1027.SANA.model.Reserva;
import es.uji.ei1027.SANA.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/municipio")
public class MunicipioController {

    private MunicipioDAO municipioDAO;

    @Autowired
    public void setMunicipioDAO(MunicipioDAO municipioDAO) {
        this.municipioDAO = municipioDAO;
    }

    @RequestMapping("/list")
    public String listaDeMunicipios(Model model){
        model.addAttribute("municipios", municipioDAO.getMunicipios());
        return "municipio/list";
    }

    @RequestMapping(value="/add")
    public String addMunicipio(Model model) {
        model.addAttribute("municipio", new Municipio());
        return "municipio/add";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("municipio") Municipio municipio,
                                   BindingResult bindingResult) {
        MunicipioValidator MunicipioValidator = new MunicipioValidator();
        MunicipioValidator.validate(municipio, bindingResult);
        if (bindingResult.hasErrors())
            return "municipio/add";
        try {
            municipioDAO.addMunicipio(municipio);
        }
        catch (DuplicateKeyException e ){
            throw new ClaveDuplicadaException("Ya existe el codigo postal " + municipio.getCp() + " para un municipio","CPduplicada");
        }
        return "redirect:list";
    }

    @RequestMapping(value="/update/{cp}", method = RequestMethod.GET)
    public String editMunicipio(Model model, @PathVariable String cp) {
        model.addAttribute("municipio", municipioDAO.getMunicipio(cp));
        return "municipio/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("municipio") Municipio municipio,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "municipio/update";
        municipioDAO.updateMunicipio(municipio);
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{cp}")
    public String processDelete(@PathVariable String cp) {
        municipioDAO.deleteMunicipio(cp);
        return "redirect:../list";
    }

     @RequestMapping(value="/verMasDetalles/{cp}", method = RequestMethod.GET)
    public String verDetallesMunicipio(Model model, @PathVariable String cp) {
        model.addAttribute("areaMunicipio", municipioDAO.getAreasMunicipio(cp));
        return "area/areasMunicipio";
    }

    @RequestMapping(value="/filtrarAreas", method= RequestMethod.POST)
    public String processAddSubmit0(@ModelAttribute("fecha") Date fecha,
                                    BindingResult bindingResult, Model model, HttpSession session) {




        return "redirect:../reserva/add1";
    }

}
