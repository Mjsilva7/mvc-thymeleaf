package com.kamauro.mvcudemy.dao;

import java.util.List;

import com.kamauro.mvcudemy.model.Departamento;

public interface DepartamentoDao {

    void save(Departamento departamento);

    void update(Departamento departamento);

    void delete(Long id);

    Departamento findById(Long id);

    List<Departamento> findAll();

}
