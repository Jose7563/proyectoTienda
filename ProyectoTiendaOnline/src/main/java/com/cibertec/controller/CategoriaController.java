package com.cibertec.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
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
import com.cibertec.service.CategoriaService;

@Controller
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	//private final Logger log= LoggerFactory.getLogger(CategoriaController.class);

	@GetMapping("/categoria")
	public String inicio(Model model) {
		
		model.addAttribute("listaCategorias", categoriaService.listar());
		return "categoria/categoria"; 
	}
	
	@GetMapping("/categoriaNueva")
	public String nuevo(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "categoria/nuevaCategoria";
	}
	
	@PostMapping("/categoriaCrear")
	public String categoriaIns(@Validated Categoria categoria, BindingResult result, RedirectAttributes red) {
		if (result.hasErrors()) {
			return "categoria/nuevaCategoria";
		}
		red.addFlashAttribute("mensaje","Se creo correctamente la categoria");
		categoria.setEstado(1);
		categoriaService.insertar(categoria); 
		return "redirect:/categoria";
	}
	@GetMapping("/categoriaObtener/{id}")
	public String actualizar(@PathVariable("id") Integer id,Model model) {
		Categoria ca= categoriaService.buscarId(id).get(); 
		model.addAttribute("categoria", ca);
		
		return "categoria/categoriaeditar";
	}
	
	@PostMapping("/categoriaEdita")
	public String categoriaUpd(@Validated Categoria categoria, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "categoria/categoriaeditar";
		}
		categoria.setEstado(1);
		categoriaService.actualizar(categoria);  
		return "redirect:/categoria";
	}
	
	@GetMapping("/categoriaEliminar")
	public String categoriaEliminar(@RequestParam("id") Integer id) {
		Categoria ca= categoriaService.buscarId(id).get(); 
		ca.setEstado(0); 
		categoriaService.actualizar(ca); 
		return "redirect:/categoria";
	}
	
}
