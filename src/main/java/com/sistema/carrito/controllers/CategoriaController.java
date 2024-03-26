package com.sistema.carrito.controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
/*
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
*/
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.carrito.models.Categoria;
import com.sistema.carrito.services.CategoriaService;





@RestController
@RequestMapping("/api/categoria")

@Api(tags = "Gestión de Categorias")
public class CategoriaController {
	
	
	@Autowired
	private  CategoriaService    categoriaService;
	
	@GetMapping("/listar")

	@ApiOperation(value = "Listar Categorias", notes = "Lista todas las categorias existente.")
	public List<Categoria> listar() {
		return categoriaService.findAll();
	}
	
	@GetMapping("/ver/{id}")
	@ApiOperation(value = "ver Categorias por id", notes = " ver categoria mediante el id  ")
	public Categoria detalle(@PathVariable Long id) throws Exception {
		return categoriaService.findById(id);
	}
	
	
	@PostMapping("/crear")
	 @ApiOperation(value = "Crear un nueva Categoria", notes = "Crea un nueva categoria en el sistema.")
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria crear(@RequestBody Categoria categoria) throws Exception {
		return categoriaService.save(categoria);
	}
	
	@PutMapping("/editar/{id}")
	 @ApiOperation(value = "Actualiza la Categoria", notes = "Actualiza categoria por el id en el sistema.")
	@ResponseStatus(HttpStatus.CREATED)
	
	public Categoria editar(@RequestBody Categoria categoria, @PathVariable Long id) throws Exception {

		try {
			Categoria categoriaDB = categoriaService.findById(id);
			categoriaDB.setNombre(categoria.getNombre());
		
			return categoriaService.save(categoriaDB);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	

	   @DeleteMapping("/{id}")
		@ApiOperation(value = "Eliminar", notes = "Eliminar un categoria por id.")
	   public ResponseEntity<?> delete (@PathVariable(value ="id") long id) throws Exception{
		   
		 
			Categoria categoriaDB = categoriaService.findById(id);
		   
		   if(categoriaDB == null) {
			   
			   return ResponseEntity.notFound().build();
			   
		   }
		   
		   categoriaService.deleteById(id);
		   
		   return  ResponseEntity.ok().build();
	   }
	   
	   
}
