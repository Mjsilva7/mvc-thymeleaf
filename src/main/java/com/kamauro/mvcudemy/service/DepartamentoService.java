package com.kamauro.mvcudemy.service;

import java.util.List;

import com.kamauro.mvcudemy.model.Departamento;

public interface DepartamentoService {

    void salvar(Departamento departamento);

    void editar(Departamento departamento);

    void excluir(Long id);

    Departamento buscarPorId(Long id);

    List<Departamento> buscarTodos();
    
}
