package com.curso.app.repository;

import org.springframework.stereotype.Repository;

import com.curso.app.entity.Cuenta;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
	
}