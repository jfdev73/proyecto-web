package org.miranda.webapp.headers.repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import org.miranda.webapp.headers.models.Categoria;


public class CategoriaRepositoryJdbcImpl implements GenericRepository<Categoria, Long> {
	
	private Connection conn;
	
	public CategoriaRepositoryJdbcImpl (Connection conn) {
		this.conn = conn;

	}
	
	private Categoria getCategoria(ResultSet rs) throws SQLException {
		Categoria categoria = new Categoria();
		categoria.setId(rs.getLong("id_categoria"));
		categoria.setCategoria(rs.getString("categoria"));
		return categoria;
	}

	@Override
	public List<Categoria> getAll() throws SQLException {
		String sql = "SELECT * FROM categoria";
		List<Categoria> categorias = new LinkedList<>();
		try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				Categoria categoria = getCategoria(rs);

				categorias.add(categoria);

			}

		}
		return categorias;
	}

	@Override
	public Categoria findById(Long id) throws SQLException {
		Categoria categoria = null;
		String sql = "SELECT * FROM categoria WHERE id_categoria=?";
		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setLong(1, id);
			
			try(ResultSet rs = ps.executeQuery()){
				
				if(rs.next()) {
					categoria = getCategoria(rs);	
				}
				
			}

		}
		return categoria;
	}

	@Override
	public void save(Categoria categoria) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
