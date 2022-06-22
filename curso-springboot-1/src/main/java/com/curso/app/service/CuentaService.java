package com.curso.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.app.entity.Cuenta;



public interface CuentaService {
	
	public Iterable<Cuenta> findAll();
	
	public Page<Cuenta> findAll(Pageable pageable);
	
	public Optional<Cuenta> findById(Long id);
	
	public Cuenta save(Cuenta cuenta);
	
	public void deleteById(Long id);

}