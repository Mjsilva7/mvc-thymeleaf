package com.kamauro.mvcudemy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kamauro.mvcudemy.model.Funcionario;
import com.kamauro.mvcudemy.repository.FuncionarioRepository;

@Service
@Transactional(readOnly = false)
public class FuncionarioService implements GenericInterfaceService<Funcionario, Long>{

    @Autowired
    private FuncionarioRepository repositorio;

    @Override
    public Funcionario cadastrar(Funcionario funcionario) {
        return repositorio.saveAndFlush(funcionario);
    }

    @Override
    public Funcionario alterar(Long id, Funcionario funcionario) {
        Funcionario funcionarioBanco = repositorio.getReferenceById(id);

        if(funcionarioBanco == null) {
            throw new EmptyResultDataAccessException(1);
        }

        BeanUtils.copyProperties(funcionario, funcionarioBanco, "id");

        return repositorio.saveAndFlush(funcionarioBanco);
         
    }

    @Override
    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Funcionario> buscaPorId(Long id) {
        return (Optional<Funcionario>) repositorio.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> listarTodos() {
        return repositorio.findAll();
    }

    @Override
    public Page<Funcionario> buscaPaginada(String pesquisa, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscaPaginada'");
    }

    // void salvar(Funcionario funcionario);

    // void editar(Funcionario funcionario);

    // void excluir(Long id);

    // Funcionario buscarPorId(Long id);

    // List<Funcionario> buscarTodos();
    
}
