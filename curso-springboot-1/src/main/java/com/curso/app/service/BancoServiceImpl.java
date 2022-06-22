package com.curso.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.app.entity.Banco;
import com.curso.app.repository.BancoRepository;

@Service
public class BancoServiceImpl implements BancoService{
	
	@Autowired
	private BancoRepository bancoRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Banco> findAll() {
		return bancoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Banco> findAll(Pageable pageable) {
		return bancoRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Banco> findById(Long id) {
		return bancoRepository.findById(id);
	}

	@Override
	@Transactional
	public Banco save(Banco banco) {
		return bancoRepository.save(banco);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		bancoRepository.deleteById(id);
		
	}

}
