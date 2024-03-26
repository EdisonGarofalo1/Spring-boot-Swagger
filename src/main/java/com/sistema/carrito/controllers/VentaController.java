package com.sistema.carrito.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.carrito.dtos.VentaDTO;
import com.sistema.carrito.models.Venta;
import com.sistema.carrito.services.VentaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/ventas")
@Api(tags = "Gestión de Venta")
public class VentaController {
	
	   @Autowired
	    private  VentaService ventaService;
	   
	   

		@GetMapping("/listar")
		// @ApiOperation(value = "Crear un nuevo producto", notes = "Crea un nuevo producto en el sistema.")
		@ApiOperation(value = "Listar Venta", notes = "Lista todas las Venta existente")
		public List<Venta> listar() {
			return ventaService.findAll();
		}
		
		@GetMapping("/ver/{id}")
		@ApiOperation(value = "ver Venta por id", notes = " ver Venta mediante el id  ")
		public Venta detalle(@PathVariable Long id) throws Exception {
			return ventaService.findById(id);
		}
	   @PostMapping("/realiarventa")
	   @ApiOperation(value = "Crear un nuevo Venta", notes = "Crea un nuevo Venta en el sistema.")
	    public ResponseEntity<Venta> procesarVenta(@RequestBody VentaDTO ventaDTO) {
		   
	
		    Venta ventaGuardada =   ventaService.RealizarVenta(ventaDTO);
	        return new ResponseEntity<>( ventaGuardada,HttpStatus.CREATED);
	        
		  
	        
	        
	    }
	   
	   @PutMapping("/{id}")
	   @ApiOperation(value = "Actualiza la Venta", notes = "Actualiza Venta por el id en el sistema.")
	    public ResponseEntity<Venta> actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
	        Venta ventaActualizada = ventaService.ActualizarVenta(id, ventaDTO);
	        if (ventaActualizada != null) {
	            return new ResponseEntity<>(ventaActualizada, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	   

	 
	   @DeleteMapping("/{id}")
		@ApiOperation(value = "Eliminar", notes = "Eliminar un Venta por id.")
	   public ResponseEntity<?> delete (@PathVariable(value ="id") long id) throws Exception{
		   
		 
		   Venta VentaDB = ventaService.findById(id);
		   
		   if(VentaDB == null) {
			   
			   return ResponseEntity.notFound().build();
			   
		   }
		   
		   ventaService.deleteById(id);
		   
		   return  ResponseEntity.ok().build();
	   }
	   

}
