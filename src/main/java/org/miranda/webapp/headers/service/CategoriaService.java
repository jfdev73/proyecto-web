package org.miranda.webapp.headers.service;

import java.util.List;
import java.util.Optional;

import org.miranda.webapp.headers.models.Categoria;


public interface CategoriaService {
	
	List<Categoria> listar();
	
    Optional<Categoria> findById(Long id);

}
