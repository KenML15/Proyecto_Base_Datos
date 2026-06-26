package com.telecom.link360.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDatabaseError(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("error", "Error: No es posible realizar esta acción porque existen registros asociados (integridad referencial).");
        return "errorPage";
    }
}