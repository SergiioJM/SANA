package es.uji.ei1027.SANA.controller;

import javax.servlet.http.HttpSession;

import es.uji.ei1027.SANA.dao.*;
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
    private ControladorDAO controladorDAO;

    @Autowired
    public void setCiudadanoDAO(CiudadanoDAO ciudadanoDAO) { this.ciudadanoDAO = ciudadanoDAO; }

    @Autowired
    public void setResponsableMunicipioDAO(ResponsableMunicipioDAO responsableMunicipioDAO) { this.responsableMunicipioDAO = responsableMunicipioDAO;}

    @Autowired
    public void setControladorDao(ControladorDAO controladorDAO) { this.controladorDAO = controladorDAO; }

    /**
     * LOGIN DEL USUARIO
     */

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

    /**
     * LOGIN DEL GESTOR MUNICIPAL
     */

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

    /**
     * LOGIN DEL CONTROLADOR
     */

    @RequestMapping("/loginControlador")
    public String login3(Model model) {
        model.addAttribute("user", new UserDetails());
        return "loginControlador";
    }

    @RequestMapping(value="/loginControlador", method=RequestMethod.POST)
    public String checkLogin3(@ModelAttribute("user") UserDetails user,
                              BindingResult bindingResult, HttpSession session) {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "loginControlador";
        }
        System.out.println(controladorDAO.dameIdAreaPorEmail(user.getEmail()));
        user = userDao.loadUserByUsername3(user.getEmail(), user.getPassword(),controladorDAO.dameIdAreaPorEmail(user.getEmail()), controladorDAO);
        if (user == null) {
            bindingResult.rejectValue("password", "password", "Contraseña incorrecta");
            return "loginControlador";
        }
        session.setAttribute("user", user);

        return "redirect:/user/controlador";
    }

    /**
     * LOGIN DEL RESPONSABLE DE MEDIO AMBIENTE
     */

    @RequestMapping("/loginResponsableMedioAmbiente")
    public String login4(Model model) {
        model.addAttribute("user", new UserDetails());
        return "loginResponsableMedioAmbiente";
    }

    @RequestMapping(value="/loginResponsableMedioAmbiente", method=RequestMethod.POST)
    public String checkLogin4(@ModelAttribute("user") UserDetails user,
                              BindingResult bindingResult, HttpSession session) {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "loginResponsableMedioAmbiente";
        }

        user = userDao.loadUserByUsername4(user.getUsuario(), user.getPassword());
        if (user == null) {
            bindingResult.rejectValue("password", "password", "Contraseña incorrecta");
            return "loginResponsableMedioAmbiente";
        }
        session.setAttribute("user", user);
        return "redirect:/user/medioAmbiente";
    }

    /**
     * CERRAR SESION
     */

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/ciudadano";
    }
}