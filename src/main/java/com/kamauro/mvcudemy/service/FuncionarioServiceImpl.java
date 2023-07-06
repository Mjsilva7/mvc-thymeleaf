package com.kamauro.mvcudemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamauro.mvcudemy.dao.FuncionarioDao;
import com.kamauro.mvcudemy.model.Funcionario;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{

    @Autowired
    private FuncionarioDao dao;

    @Override
    public void salvar(Funcionario funcionario) {
        dao.save(funcionario);
    }

    @Override
    public void editar(Funcionario funcionario) {
        dao.update(funcionario);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public Funcionario buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Funcionario> buscarTodos() {
        return dao.findAll();
    }
    
}
