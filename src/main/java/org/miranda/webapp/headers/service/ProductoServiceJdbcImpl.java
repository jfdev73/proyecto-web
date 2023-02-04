package org.miranda.webapp.headers.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.miranda.webapp.headers.exceptions.ServiceJdbcException;
import org.miranda.webapp.headers.models.Producto;
import org.miranda.webapp.headers.repository.ProductoRepositoryJdbcImpl;



public class ProductoServiceJdbcImpl implements ProductoService {
	
	private ProductoRepositoryJdbcImpl repositoryJDBC;

	public ProductoServiceJdbcImpl(Connection conn) {
		this.repositoryJDBC = new ProductoRepositoryJdbcImpl(conn);

	}

	@Override
	public List<Producto> listar() {
		try {
			return repositoryJDBC.getAll();
		} catch (SQLException e) {
			throw new ServiceJdbcException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Optional<Producto> findById(Long id) {
		try {
			return Optional.ofNullable(repositoryJDBC.findById(id));
		} catch (SQLException e) {
			throw new ServiceJdbcException(e.getMessage(), e.getCause());
		}
	}

}
