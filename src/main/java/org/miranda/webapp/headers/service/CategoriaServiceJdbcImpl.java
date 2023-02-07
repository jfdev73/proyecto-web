package org.miranda.webapp.headers.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.miranda.webapp.headers.exceptions.ServiceJdbcException;
import org.miranda.webapp.headers.models.Categoria;
import org.miranda.webapp.headers.repository.CategoriaRepositoryJdbcImpl;
import org.miranda.webapp.headers.repository.GenericRepository;

public class CategoriaServiceJdbcImpl implements CategoriaService{
	
	private GenericRepository<Categoria, Long> repository;
	
	public CategoriaServiceJdbcImpl(Connection conn) {
		this.repository = new CategoriaRepositoryJdbcImpl(conn);
		
	}

	@Override
	public List<Categoria> listar() {
		try {
			return repository.getAll();
		} catch (SQLException e) {
			throw new ServiceJdbcException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Optional<Categoria> findById(Long id) {
		try {
			return Optional.ofNullable(repository.findById(id));
		} catch (SQLException e) {
			throw new ServiceJdbcException(e.getMessage(), e.getCause());
		}
	}

}
