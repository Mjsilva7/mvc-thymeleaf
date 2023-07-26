package com.kamauro.mvcudemy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kamauro.mvcudemy.model.Cargo;
import com.kamauro.mvcudemy.model.Departamento;

public interface CargoRepository extends JpaRepository<Cargo, Long>{

    List<Cargo> findByDepartamento(Departamento departamento);
    
}
