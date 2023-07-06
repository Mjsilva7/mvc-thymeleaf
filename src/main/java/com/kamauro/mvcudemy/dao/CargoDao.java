package com.kamauro.mvcudemy.dao;

import java.util.List;

import com.kamauro.mvcudemy.model.Cargo;

public interface CargoDao {

    void save(Cargo cargo);

    void update(Cargo cargo);

    void delete(Long id);

    Cargo findById(Long id);

    List<Cargo> findAll();

}
