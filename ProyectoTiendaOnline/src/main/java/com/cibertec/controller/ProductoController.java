package com.cibertec.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
import com.cibertec.entity.Producto;
import com.cibertec.service.AlmacenService;
import com.cibertec.service.CategoriaService;
import com.cibertec.service.MarcaService;
import com.cibertec.service.ProductoService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	@Autowired
	private AlmacenService almacenService;
	@Autowired
	private MarcaService marcaService;
	@Autowired
	private CategoriaService categoriaService;


	@GetMapping("/marcaSeleccionada")
	public String marca(@RequestParam("nombre") String nombre,Model model) {
		
		List<Producto> pro= productoService.listaPorNombreProductoMarca(nombre);
		model.addAttribute("productos", pro);
		return "index";
	}
	
	@GetMapping("/categoriaSeleccionada")
	public String categoria(@RequestParam("nombre") String nombre,Model model) {
		
		List<Producto> pro= productoService.listaPorNombreProductoCategoria(nombre);
		model.addAttribute("productos", pro);
		return "index";
	}

	@GetMapping("/producto")
	public String inicio(Model model) {

		model.addAttribute("productos", productoService.listar());
		return "producto/producto";
	}

	@GetMapping("/productoNuevo")
	public String nuevo(Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("categorias", categoriaService.listaCategoriasActivo());
		model.addAttribute("marcas", marcaService.listaMarcasActivo());
		return "producto/nuevoProducto";
	}

	@PostMapping("/productoCrear")
	public String save(@Validated Producto producto,BindingResult result, RedirectAttributes red,Model model) throws IOException {
		
		if (result.hasErrors()) {
			model.addAttribute("marcas", marcaService.listaMarcasActivo());
			model.addAttribute("categorias", categoriaService.listaCategoriasActivo());
			return "producto/nuevoProducto";
		}
		red.addFlashAttribute("mensaje","Se creo correctamente el producto");
		String rutaPortada = almacenService.almacenarArchivo(producto.getImagen());
		producto.setImagenRuta(rutaPortada);

		productoService.insertar(producto);
		return "redirect:/producto";
	}
	
	
	@GetMapping("/productoObtener/{id}")
	public String actualizar(@PathVariable("id") Integer id,Model model) {
		Producto pro= productoService.buscarId(id).get(); 
		model.addAttribute("categorias", marcaService.listaMarcasActivo());
		model.addAttribute("marcas", categoriaService.listaCategoriasActivo());
		model.addAttribute("producto", pro);
		return "producto/productoeditar";
	}
	
	@PostMapping("/productoEditar")
	public String categoriaUpd(@Validated Producto producto,BindingResult result, Model model) {
		
		Producto p= new Producto();
		p= productoService.buscarId(producto.getId()).get();
		
		if (result.hasErrors()) {
			model.addAttribute("marcas", marcaService.listaMarcasActivo());
			model.addAttribute("categorias", categoriaService.listaCategoriasActivo());
			return "producto/productoeditar";
		}
		
		if (producto.getImagen().isEmpty()) { 
			producto.setImagenRuta(p.getImagenRuta());
		}else {
			 almacenService.eliminarArchivo(p.getImagenRuta());
			 String nombreImagen= almacenService.almacenarArchivo(producto.getImagen());
				producto.setImagenRuta(nombreImagen);
		}
		 
		productoService.actualizar(producto);
		return "redirect:/producto";
	}
	

}
