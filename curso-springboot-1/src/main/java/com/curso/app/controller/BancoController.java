package com.curso.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
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

import com.curso.app.entity.Banco;
import com.curso.app.service.BancoService;

@RestController
@RequestMapping("/api/bancos")
public class BancoController {
	
	@Autowired
	private BancoService bancoService;
	
	//Crear un nuevo usuario
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Banco banco){
		return ResponseEntity.status(HttpStatus.CREATED).body(bancoService.save(banco));
	}
	
	//leer un usuario
		//public ResponseEntity<?> read (@PathVariable(value = "id") Long userId){
		@GetMapping("/{id}")
		public ResponseEntity<?> read (@PathVariable Long id){
			Optional<Banco> oBanco = bancoService.findById(id);
			
			if(!oBanco.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(oBanco);
		}
		
		// Actualizar usuarios
		@PutMapping("/{id}")
		public ResponseEntity<?> update (@RequestBody Banco bancoDetails, @PathVariable(value = "id") Long bancoId){
			Optional<Banco> banco = bancoService.findById(bancoId);
			
			if(!banco.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			//BeanUtils.copyProperties(userDetails, user.get());
			
			banco.get().setNombre(bancoDetails.getNombre());
			banco.get().setSede(bancoDetails.getSede());
			
			
			return ResponseEntity.status(HttpStatus.CREATED).body(bancoService.save(banco.get())); 
		}
		
		//Borrar usuario
		@DeleteMapping("/{id}")
		public ResponseEntity<?> delete (@PathVariable(value = "id") Long bancoId){
			
			if(!bancoService.findById(bancoId).isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			bancoService.deleteById(bancoId);
			return ResponseEntity.ok().build();
			
		}
		
		// Leer todos los usuarios
		@GetMapping
		public List<Banco> readAll(){
			List<Banco> banco = StreamSupport
					.stream(bancoService.findAll().spliterator(), false)
					.collect(Collectors.toList());
			
			return banco;
		}
}
