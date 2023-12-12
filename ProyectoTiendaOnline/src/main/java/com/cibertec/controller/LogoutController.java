package com.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {

	@GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession sesion) {
        // Obtenemos la sesión actual o la creamos si no existe
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            // Invalidamos la sesión (cerramos la sesión)
//            session.invalidate();
//        }
		
       sesion.removeAttribute("idusuario"); 
        return "redirect:/";
    }
}
