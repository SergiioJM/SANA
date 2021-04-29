package es.uji.ei1027.SANA.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SANAControllerAdvice {

    @ExceptionHandler(value = ClaveDuplicadaException.class)
    public ModelAndView handleClubException(ClaveDuplicadaException ex){

        ModelAndView mav = new ModelAndView("error/excepcion");
        mav.addObject("message", ex.getMessage());
        mav.addObject("errorName", ex.getErrorName());
        return mav;
    }

}

