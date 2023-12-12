package com.cibertec.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.entity.Orden;
import com.cibertec.entity.OrdenDetalle;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.cibertec.entity.Usuario;
import com.cibertec.service.OrdenDetalleService;
import com.cibertec.service.OrdenService;
import com.cibertec.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private OrdenDetalleService detalleService; 
	@Autowired
	private OrdenService ordenService; 
	
	
	
	@GetMapping("/ordenes")
	public String act(HttpSession session , Model model) {

		
		int id = Integer.parseInt(session.getAttribute("idusuario").toString());
		List<Orden> ordenes = ordenService.listaCompras(id); 
		
		model.addAttribute("ordenes", ordenes);
		
		return "usuario/ordenes";
	}
	
	@GetMapping("/ordenDetalle/{id}")
	public String detalle(@PathVariable("id") Integer id,HttpSession session , Model model) {

		
		int idSesion = Integer.parseInt(session.getAttribute("idusuario").toString());
		Usuario us = usuarioService.buscarId(idSesion).get();
		List<OrdenDetalle> oDetalles= detalleService.listaDetalles(id);
		
		model.addAttribute("nombre", us.getNombres());
		model.addAttribute("email", us.getEmail());
		model.addAttribute("telefono", us.getTelefono());
		model.addAttribute("nombre", us.getNombres());
		model.addAttribute("oDetalles", oDetalles);
		
		return "usuario/ordenDetalles";
	}
	
	
	
	private final Logger logger= LoggerFactory.getLogger(UsuarioController.class);
	@GetMapping("/registro")
	public String usuario() {
		return "usuario/registro"; 
	}
	
	@PostMapping("/guardar")
	public String save(Usuario usuario, RedirectAttributes red) {
		logger.info("Usuario registro: {}", usuario);
		usuario.setTipo("USER");
		
		usuarioService.insertar(usuario);	
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "usuario/login";
	}
	
	@PostMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session, RedirectAttributes red) {
		logger.info("Accesos : {}", usuario);
	
		Optional<Usuario> user=usuarioService.buscarPorEmail(usuario.getEmail());
		//logger.info("Usuario de db: {}", user.get());
		
		if (user.isPresent()) {
			session.setAttribute("idusuario", user.get().getId());
			
			if (user.get().getTipo().equals("USER")) {
				red.addFlashAttribute("mensaje", "Sesión Iniciada correctamente");
				return "redirect:/";
			}
			if (user.get().getTipo().equals("ADMIN")) {
				red.addFlashAttribute("mensaje", "Sesión Iniciada correctamente");
				return "redirect:/categoria";
			}
			
		}
		 red.addFlashAttribute("mensaje", "Credenciales incorrectas");
		
		return "redirect:/usuario/login";
	}
}
