package es.uji.ei1027.SANA.controller;

import javax.servlet.http.HttpSession;
import es.uji.ei1027.SANA.dao.CiudadanoDAO;
import es.uji.ei1027.SANA.dao.ResponsableMunicipioDAO;
import es.uji.ei1027.SANA.dao.UserDao;
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

class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return UserDetails.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {
        UserDetails userDetails = (UserDetails)obj;
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
        user = userDao.loadUserByUsername(user.getNif(), user.getPassword(), ciudadanoDAO);
        if (user == null) {
            bindingResult.rejectValue("password", "password", "Contraseña incorrecta");
            return "login";
        }
        session.setAttribute("user", user);
        return "redirect:/user/ciudadano";
    }

    @RequestMapping("/loginGestor")
    public String login2(Model model) {
        model.addAttribute("user", new UserDetails());
        return "loginGestor";
    }

    @RequestMapping(value="/loginGestor", method=RequestMethod.POST)
    public String checkLogin2(@ModelAttribute("user") UserDetails user,
                             BindingResult bindingResult, HttpSession session) {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "loginGestor";
        }

        user = userDao.loadUserByUsername2(user.getEmail(), user.getPassword(),responsableMunicipioDAO.dameMunicipioPorEmail(user.getEmail()), responsableMunicipioDAO);
        if (user == null) {
            bindingResult.rejectValue("password", "password", "Contraseña incorrecta");
            return "loginGestor";
        }
        session.setAttribute("user", user);
        return "redirect:/user/gestor";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/ciudadano";
    }
}