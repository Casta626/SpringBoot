package com.curso.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.app.entity.Cuenta;
import com.curso.app.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService {
	
	@Autowired
	private CuentaRepository cuentaRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Cuenta> findAll() {
		return cuentaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cuenta> findAll(Pageable pageable) {
		return cuentaRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cuenta> findById(Long id) {
		return cuentaRepository.findById(id);
	}

	@Override
	@Transactional
	public Cuenta save(Cuenta cuenta) {
		return cuentaRepository.save(cuenta);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		cuentaRepository.deleteById(id);
		
	}
	
}
