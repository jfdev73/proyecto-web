package org.miranda.webapp.headers.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.miranda.webapp.headers.models.Producto;



public class ProductoServiceImpl implements ProductoService {
	
	@Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computacion", 175000),
                new Producto(2L, "mesa escritorio", "oficina", 100000),
                new Producto(3L, "teclado mecanico", "computacion", 40000));
    }

	@Override
	public Optional<Producto> findById(Long id) {
		
		return listar().stream().filter(p -> p.getId().equals(id)).findAny();
	}

	@Override
	public void save(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
