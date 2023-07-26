package com.kamauro.mvcudemy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericInterfaceService<T, K> {

    public T cadastrar(T t);

	public T alterar(K i,T t);

	public void deletar(K id);

	public Optional<T> buscaPorId(K id);

	public List<T> listarTodos();
	
	public Page<T> buscaPaginada(String pesquisa, Pageable pageable);
    
}
