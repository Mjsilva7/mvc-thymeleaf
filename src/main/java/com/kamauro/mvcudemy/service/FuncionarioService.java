package com.kamauro.mvcudemy.service;

import java.util.List;

import com.kamauro.mvcudemy.model.Funcionario;

public interface FuncionarioService {

    void salvar(Funcionario funcionario);

    void editar(Funcionario funcionario);

    void excluir(Long id);

    Funcionario buscarPorId(Long id);

    List<Funcionario> buscarTodos();
    
}
