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

import com.curso.app.entity.Cuenta;
import com.curso.app.service.CuentaService;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {
	
	@Autowired
	private CuentaService cuentaService;
	
	//Crear un nuevo usuario
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Cuenta cuenta){
		return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.save(cuenta));
	}
	
	//leer un usuario
		//public ResponseEntity<?> read (@PathVariable(value = "id") Long userId){
		@GetMapping("/{id}")
		public ResponseEntity<?> read (@PathVariable Long id){
			Optional<Cuenta> oCuenta = cuentaService.findById(id);
			
			if(!oCuenta.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(oCuenta);
		}
		
		// Actualizar usuarios
		@PutMapping("/{id}")
		public ResponseEntity<?> update (@RequestBody Cuenta cuentaDetails, @PathVariable(value = "id") Long cuentaId){
			Optional<Cuenta> cuenta = cuentaService.findById(cuentaId);
			
			if(!cuenta.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			//BeanUtils.copyProperties(userDetails, user.get());
			
			cuenta.get().setSaldo(cuentaDetails.getSaldo());
			cuenta.get().setTitular(cuentaDetails.getTitular());
			
			
			return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.save(cuenta.get())); 
		}
		
		//Borrar usuario
		@DeleteMapping("/{id}")
		public ResponseEntity<?> delete (@PathVariable(value = "id") Long cuentaId){
			
			if(!cuentaService.findById(cuentaId).isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			cuentaService.deleteById(cuentaId);
			return ResponseEntity.ok().build();
			
		}
		
		// Leer todos los usuarios
		@GetMapping
		public List<Cuenta> readAll(){
			List<Cuenta> cuenta = StreamSupport
					.stream(cuentaService.findAll().spliterator(), false)
					.collect(Collectors.toList());
			
			return cuenta;
		}
}
