package com.kamauro.mvcudemy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kamauro.mvcudemy.model.Cargo;
import com.kamauro.mvcudemy.model.Departamento;
import com.kamauro.mvcudemy.repository.CargoRepository;



@Service
@Transactional(readOnly = false)
public class CargoService implements GenericInterfaceService<Cargo, Long> {

    @Autowired
    private CargoRepository repositorio;

    @Override
    public Cargo cadastrar(Cargo cargo) {
        return repositorio.saveAndFlush(cargo);
    }

    @Override
    public Cargo alterar(Long id, Cargo cargo) {
        Cargo cargoBanco = repositorio.getReferenceById(id);

        if(cargoBanco == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(cargo, cargoBanco, "id");
        return repositorio.saveAndFlush(cargoBanco);
    }

    @Override
    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cargo> buscaPorId(Long id) {
        return (Optional<Cargo>) repositorio.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cargo> listarTodos() {
        return repositorio.findAll();
    }

    public List<Cargo> buscarPorDepartamentos(List<Departamento> lista) {
        List<Cargo> cargos = new ArrayList<>();
        for(Departamento for_departamento : lista) {
            cargos.addAll(repositorio.findByDepartamento(for_departamento));
        }
        return cargos;
    }

    @Override
    public Page<Cargo> buscaPaginada(String pesquisa, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscaPaginada'");
    }

    public boolean cargoTemFuncionarios(Long id) {
        if(buscaPorId(id).get().getFuncionarios().isEmpty()) {
            return false;
        }
        return true;
    }
}
