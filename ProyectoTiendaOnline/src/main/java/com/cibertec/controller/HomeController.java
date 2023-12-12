package com.cibertec.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.entity.Orden;
import com.cibertec.entity.OrdenDetalle;
import com.cibertec.entity.Producto;
import com.cibertec.entity.Usuario;
import com.cibertec.service.MarcaService;
import com.cibertec.service.OrdenDetalleService;
import com.cibertec.service.OrdenService;
import com.cibertec.service.ProductoService;
import com.cibertec.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private ProductoService productoService;
	@Autowired
	private OrdenService ordenService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private OrdenDetalleService detalleOrdenService;

	Orden orden = new Orden();
	List<OrdenDetalle> detalles = new ArrayList<>();
	int idUsuario = 0;

	@GetMapping("/guardarOrden")
	public String saveOrder(HttpSession session, RedirectAttributes red) {

		if (session.getAttribute("idusuario") == null) {
			// se tiene que loguear
			red.addFlashAttribute("mensaje", "Se requiere Iniciar sesion");
			return "redirect:/getcart";
		}
		idUsuario = Integer.parseInt(session.getAttribute("idusuario").toString());
		// usuario
		Usuario usuario = usuarioService.buscarId(idUsuario).get();

		ordenService.realizarVenta(orden, detalles, usuario);

		/// limpiar lista y orden
		orden = new Orden();
		detalles.clear();

		return "redirect:/";
	}


	@PostMapping("/cart")
	public String cart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		OrdenDetalle detalle = new OrdenDetalle();
		Producto producto = new Producto();
		double sumaTotal = 0;
		producto = productoService.buscarId(id).get();
		Integer idProducto = producto.getId();
		boolean existe = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

		if (existe) {
			for (OrdenDetalle od : detalles) {
				if (od.getProducto().getId() == producto.getId()) {
					od.setCantidad(cantidad);
					od.setTotal(cantidad * producto.getPrecio());
				}
			}

		} else {
			detalle.setCantidad(cantidad);
			detalle.setPrecio(producto.getPrecio());
			detalle.setNombre(producto.getNombre());
			detalle.setProducto(producto);
			detalle.setTotal(producto.getPrecio() * cantidad);
			detalles.add(detalle);
		}

		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		orden.setTotal(sumaTotal);

		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "orden/cart";
	}

	@GetMapping("/getcart")
	public String getCart(Model model) {

		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		// sesion
//		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "orden/cart";
	}

	@GetMapping("/eliminarItem/{id}")
	public String eliminarItem(@PathVariable Integer id, Model model) {
		double sumaTotal = 0;
		List<OrdenDetalle> listaAuxiliar = new ArrayList<>();

		for (OrdenDetalle od : detalles) {
			if (od.getProducto().getId() != id)
				listaAuxiliar.add(od);
		}
		detalles = listaAuxiliar;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		return "orden/cart";
	}

	@GetMapping("/")
	public String inicios(Model model, HttpSession session) {
	
		model.addAttribute("productos", productoService.listar());
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		return "index";
	}

	@GetMapping("/verDetalle/{id}")
	public String home(@PathVariable("id") Integer id, Model model) {

		Producto producto = productoService.buscarId(id).get();
		List<Producto> lista = productoService.listaPorNombreProductoCategoria(producto.getCategoria().getNombre())
				.stream().filter(o -> o.getNombre() != producto.getNombre()).limit(4).collect(Collectors.toList());

		model.addAttribute("producto", producto);
		model.addAttribute("lista", lista);
		return "detalleProducto";
	}
}
