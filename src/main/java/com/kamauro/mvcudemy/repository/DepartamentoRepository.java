package com.kamauro.mvcudemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamauro.mvcudemy.model.Departamento;


public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
