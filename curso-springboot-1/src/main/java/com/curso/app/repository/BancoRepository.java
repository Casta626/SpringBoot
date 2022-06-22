package com.curso.app.repository;

import org.springframework.stereotype.Repository;

import com.curso.app.entity.Banco;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {

}
