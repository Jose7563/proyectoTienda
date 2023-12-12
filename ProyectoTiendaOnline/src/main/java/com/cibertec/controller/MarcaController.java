package com.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.entity.Categoria;
import com.cibertec.entity.Marca;
import com.cibertec.service.MarcaService;

@Controller
public class MarcaController {

	@Autowired
	private MarcaService marcaService;
	

	@GetMapping("/marca")
	public String inicios(Model model) {
		
		model.addAttribute("listaMarcas", marcaService.listar());
		return "marca/marca"; 
	}
	@GetMapping("/marcaNueva")
	public String nuevomarca(Model model) {
		model.addAttribute("marca", new Marca());
		return "marca/nuevaMarca";
	}
	@PostMapping("/marcaCrear")
	public String marcaIns(@Validated Marca marca, BindingResult result, RedirectAttributes red) {
		if (result.hasErrors()) {
			return "marca/nuevaMarca";
		}
		red.addFlashAttribute("mensaje","Se creo correctamente la marca");
		marca.setEstado(1);
		marcaService.insertar(marca); 
		return "redirect:/marca";
	}
	
	@GetMapping("/marcaObtener/{id}")
	public String actualizarmarca(@PathVariable("id") Integer id,Model model) {
		Marca ca= marcaService.buscarId(id).get(); 
		model.addAttribute("categoria", ca);
		
		return "marca/marcaeditar";
	}
	
	@PostMapping("/marcaEditar")
	public String marcaUpd(@Validated Marca marca, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "marca/marcaeditar";
		}
		marca.setEstado(1);
		marcaService.actualizar(marca);  
		return "redirect:/marca";
	}
	
	@GetMapping("/marcaEliminar")
	public String marcaEliminar(@RequestParam("id") Integer id) {
		Marca ca= marcaService.buscarId(id).get(); 
		ca.setEstado(0); 
		marcaService.actualizar(ca); 
		return "redirect:/marca";
	}
	
}
