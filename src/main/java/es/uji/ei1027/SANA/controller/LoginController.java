package es.uji.ei1027.SANA.controller;

import javax.servlet.http.HttpSession;

import es.uji.ei1027.SANA.dao.CiudadanoDAO;
import es.uji.ei1027.SANA.dao.ResponsableMunicipioDAO;
import es.uji.ei1027.SANA.dao.UserDao;
import es.uji.ei1027.SANA.model.Ciudadano;
import es.uji.ei1027.SANA.model.ResponsableMunicipio;
import es.uji.ei1027.SANA.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return UserDetails.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {
        UserDetails userDetails = (UserDetails)obj;
        if (userDetails.getNif().trim().equals(""))
            errors.rejectValue("nif", "obligatori",
                    "El campo usuario no puede estar vacio");
        if (userDetails.getPassword().trim().equals(""))
            errors.rejectValue("password", "obligatori",
                    "El campo contraseña no puede estar vacio");
    }
}

@Controller
public class LoginController {
    @Autowired
    private UserDao userDao;
    private ResponsableMunicipioDAO responsableMunicipioDAO;
    private CiudadanoDAO ciudadanoDAO;

    @Autowired
    public void setCiudadanoDAO(CiudadanoDAO ciudadanoDAO) { this.ciudadanoDAO = ciudadanoDAO; }

    @Autowired
    public void setResponsableMunicipioDAO(ResponsableMunicipioDAO responsableMunicipioDAO) { this.responsableMunicipioDAO = responsableMunicipioDAO;}

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserDetails());
        return "login";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") UserDetails user,
                             BindingResult bindingResult, HttpSession session) {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "login";
        }
        List<Ciudadano> ciudadano = ciudadanoDAO.getCiudadanos();
        List<ResponsableMunicipio> responsable = responsableMunicipioDAO.getResponsablesMunicipios();
        for (int i = 0; i < ciudadano.size(); i++){
            if (ciudadano.get(i).getNif().equals(user.getNif())){
                user = userDao.loadUserByUsername(user.getNif(), user.getPassword(), ciudadanoDAO);
                if (user == null) {
                    bindingResult.rejectValue("password", "badpw", "Contraseña incorrecta");
                    return "redirect:/index.html";
                }
                session.setAttribute("user", user);
                return "redirect:/user/ciudadano";
            }/**else if(responsable.get(i).){

             }**/
        }
        user = userDao.loadUserByUsername(user.getNif(), user.getPassword(), ciudadanoDAO);
        if (user == null) {
            bindingResult.rejectValue("password", "badpw", "Contraseña incorrecta");
            return "redirect:/index.html";
        }
        session.setAttribute("user", user);
        return "redirect:/user/ciudadano";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/pagina2.html";
    }
}