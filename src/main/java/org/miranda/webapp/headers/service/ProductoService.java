package org.miranda.webapp.headers.service;

import java.util.List;
import java.util.Optional;

import org.miranda.webapp.headers.models.Producto;



public interface ProductoService {
	List<Producto> listar();
	
    Optional<Producto> findById(Long id);
    
    void save(Producto producto);
    
    void deleteById(Long id);

}
