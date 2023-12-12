package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cibertec.entity.Orden;
import com.cibertec.service.OrdenService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrdenesController {

	@Autowired
	private OrdenService ordenService; 
	
	
	@GetMapping("/productoAdd/{id}")
	public String actualizare(@PathVariable("id") Integer id,Model model) {

	model.addAttribute("mensaje", id);
		
		return "orden/carrito";
	}
	
	
	
	
}
