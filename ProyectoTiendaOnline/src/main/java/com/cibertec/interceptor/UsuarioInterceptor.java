package com.cibertec.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cibertec.entity.Usuario;
import com.cibertec.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UsuarioInterceptor implements HandlerInterceptor  {
	
//	@Autowired 
//	private UsuarioService userService; 
//	 @Override
//	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//	        if (modelAndView != null) {
//	            // Obtener el nombre de usuario de la sesión
//	            String id = (String) request.getSession().getAttribute("idusuario");
//	            if(id==null) {
//	            	id="1";
//	            }
//				Usuario us = userService.nombreUsuarioSesion(Integer.parseInt(id)); 
//	            // Agregar el nombre de usuario al modelo
//	            modelAndView.addObject("nombreDeUsuario", us== null?null:us.getNombres());
//	        }
//	    }

}
