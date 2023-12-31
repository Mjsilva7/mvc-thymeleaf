package com.kamauro.mvcudemy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kamauro.mvcudemy.model.Departamento;
import com.kamauro.mvcudemy.repository.DepartamentoRepository;

import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class DepartamentoService implements GenericInterfaceService<Departamento, Long>{

    @Autowired
    private DepartamentoRepository repositorio;

    @Override
    public Departamento cadastrar(Departamento departamento) {
        
        return repositorio.saveAndFlush(departamento);
    }

    @Override
    public Departamento alterar(Long id, Departamento departamento) {
        Departamento departamentoBanco = repositorio.getReferenceById(id);

        if(departamentoBanco == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(departamento, departamentoBanco, "id");
        return repositorio.saveAndFlush(departamentoBanco);
    }

    @Override
    public void deletar(Long id) {
       repositorio.deleteById(id);
    }

    @Override
    public Optional<Departamento> buscaPorId(Long id) {
        return (Optional<Departamento>) repositorio.findById(id);
    }

    @Transactional(readOnly = true)
    public Departamento buscarPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public List<Departamento> listarTodos() {
       return repositorio.findAll();
    }

    @Override
    public Page<Departamento> buscaPaginada(String pesquisa, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscaPaginada'");
    }

    public boolean departamentoTemCargos(Long id) {
        if(buscaPorId(id).get().getCargos().isEmpty()) {
            return false;
        }
        return true;
    }    
}
