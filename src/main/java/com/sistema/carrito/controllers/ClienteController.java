package com.sistema.carrito.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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

import com.sistema.carrito.models.Cliente;
import com.sistema.carrito.models.Producto;
import com.sistema.carrito.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/cliente")
@Api(tags = "Gestión de Cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/listar")
	@ApiOperation(value = "Listar Cliente", notes = "Lista todos las cliente existente.")
	public List<Cliente> listar() {
		return clienteService.findAll();
	}
	
	@GetMapping("/ver/{id}")
	@ApiOperation(value = "ver cliente por id", notes = " ver cliente mediante el id  ")
	public Cliente detalle(@PathVariable Long id) throws Exception {
		return clienteService.findById(id);
	}
	
	
	@PostMapping("/crear")
	 @ApiOperation(value = "Crear un nuevo cliente", notes = "Crea un nuevo cliente en el sistema.")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente crear(@RequestBody Cliente cliente) throws Exception {
		return clienteService.save(cliente);
	}
	
	@PutMapping("/editar/{id}")
	 @ApiOperation(value = "Actualizar cliente", notes = "Actualiza el cliente por el id ")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente editar(@RequestBody Cliente cliente, @PathVariable Long id) throws Exception {

		try {
			Cliente clienteDB = clienteService.findById(id);
			clienteDB.setNombre(cliente.getNombre());
			clienteDB.setTelefono(cliente.getTelefono());
			clienteDB.setDireccion(cliente.getDireccion());
			
		
			return clienteService.save(clienteDB);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	

    @DeleteMapping("/{id}")
		@ApiOperation(value = "Eliminar", notes = "Eliminar un cliente por id.")
	   public ResponseEntity<?> delete (@PathVariable(value ="id") long id) throws Exception{
		   
		 
    	Cliente ClienteDB = clienteService.findById(id);
		   
		   if(ClienteDB == null) {
			   
			   return ResponseEntity.notFound().build();
			   
		   }
		   
		   clienteService.deleteById(id);
		   
		   return  ResponseEntity.ok().build();
	   }

}
