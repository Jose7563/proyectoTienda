package com.cibertec.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entity.Orden;
import com.cibertec.entity.OrdenDetalle;
import com.cibertec.entity.Producto;
import com.cibertec.entity.Usuario;
import com.cibertec.repository.OrdenDetalleRepository;
import com.cibertec.repository.OrdenRepository;
import com.cibertec.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class OrdenServiceImpl  implements OrdenService{

	@Autowired
	private OrdenRepository ordenRepository;
	@Autowired
	private ProductoRepository productoRepository;  
	@Autowired
	private OrdenDetalleRepository odRepository;  
	
    @Transactional
	@Override
	public void realizarVenta(Orden orden, List<OrdenDetalle> detalles, Usuario usuario) {
	
		orden.setFechaCreacion(new Date());
		orden.setUsuario(usuario);
		orden.setTotal(calcularMontoTotal( detalles));
		ordenRepository.save(orden);
		
		for (OrdenDetalle dt:detalles) {
			dt.setOrden(orden);
			odRepository.save(dt);
		}
		int nuevaCantidad=0;
		
		for(OrdenDetalle dt:detalles) {
		 nuevaCantidad= productoRepository.findById(dt.getProducto().getId()).get().getCantidad() - dt.getCantidad();
		 productoRepository.actualizarStock(nuevaCantidad, dt.getProducto().getId());
		}
		
	}
	
	private Double calcularMontoTotal(List<OrdenDetalle> detalles) {
        return  detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        		//detalles.stream().map(e -> e.getPrecio() * e.getCantidad()).reduce(Double::sum).get();
    }
	
	@Override
	public Orden insertar(Orden ca) {
		
		return ordenRepository.save(ca);
	}

	@Override
	public List<Orden> listar() {
		
		return ordenRepository.findAll();
	}

	@Override
	public List<Orden> listaCompras(int idUsuario) {
		
		return ordenRepository.listaCompras(idUsuario);
	}

	
	
	
	
}
