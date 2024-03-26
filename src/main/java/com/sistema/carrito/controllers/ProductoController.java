
package com.sistema.carrito.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.carrito.models.Producto;

import com.sistema.carrito.services.ProductoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/producto")
@Api(tags = "Gestión de Producto")
public class ProductoController {
	
	@Autowired
	private  ProductoService productoService;
	
	
	@GetMapping("/listar")
	@ApiOperation(value = "Listar Producto", notes = "Lista todos los Producto existente.")
	public List<Producto> listar() {
		return productoService.findAll();
	}
	
	@GetMapping("/ver/{id}")
	@ApiOperation(value = "ver Producto por id", notes = " ver Producto mediante el id  ")
	public Producto detalle(@PathVariable Long id) throws Exception {
		return productoService.findById(id);
	}
	
	
	@PostMapping("/crear")
	 @ApiOperation(value = "Crear un nuevo Producto", notes = "Crea un nuevo Producto en el sistema.")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) throws Exception {
		return productoService.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ApiOperation(value = "Actualiza el Producto", notes = "Actualiza el Producto por el id.")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) throws Exception {

		try {
			Producto productoDB = productoService.findById(id);
			productoDB.setCategoria(producto.getCategoria());
			productoDB.setNombre(producto.getNombre());
			productoDB.setPrecio(producto.getPrecio());
			
			productoDB.setStock(producto.getStock());
		
			return productoService.save(productoDB);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
	       @DeleteMapping("/{id}")
			@ApiOperation(value = "Eliminar", notes = "Eliminar un producto por id.")
		   public ResponseEntity<?> delete (@PathVariable(value ="id") long id) throws Exception{
			   
			 
	    	   Producto ProductoDB = productoService.findById(id);
			   
			   if(ProductoDB == null) {
				   
				   return ResponseEntity.notFound().build();
				   
			   }
			   
			   productoService.deleteById(id);
			   
			   return  ResponseEntity.ok().build();
		   }
	  
	  
}
