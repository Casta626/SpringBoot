package com.curso.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.app.entity.Banco;



public interface BancoService {
	
	public Iterable<Banco> findAll();
	
	public Page<Banco> findAll(Pageable pageable);
	
	public Optional<Banco> findById(Long id);
	
	public Banco save(Banco banco);
	
	public void deleteById(Long id);

}
