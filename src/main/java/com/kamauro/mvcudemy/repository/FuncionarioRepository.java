package com.kamauro.mvcudemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamauro.mvcudemy.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    
}
