package org.miranda.webapp.headers.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.miranda.webapp.headers.models.Producto;

public class ProductoRepositoryJdbcImpl implements GenericRepository<Producto, Long>{
	private Connection conn;
	private String sql;

	public ProductoRepositoryJdbcImpl(Connection conn) {
		this.conn = conn;
	}

	private Producto getProducto(ResultSet rs) throws SQLException {
		Producto producto = new Producto();
		producto.setId(rs.getLong(1));
		producto.setNombre(rs.getString(2));
		producto.setPrecio(rs.getInt(3));
		producto.setTipo(rs.getString(5));
		return producto;
	}

	@Override
	public List<Producto> getAll() throws SQLException {
		sql = "SELECT * FROM producto as p inner join categoria as c ON p.categoria=c.id_categoria";
		List<Producto> productos = new LinkedList<>();
		try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				Producto producto = getProducto(rs);

				productos.add(producto);

			}

		}
		return productos;
	}

	@Override
	public Producto findById(Long id) throws SQLException {
		Producto producto = null;
		sql = "SELECT * FROM producto as p inner join categoria as c ON p.categoria=c.id_categoria WHERE id_producto=?";
		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setLong(1, id);
			
			try(ResultSet rs = ps.executeQuery()){
				
				if(rs.next()) {
					producto = getProducto(rs);	
				}
				
			}

		}
		return producto;
	}

	@Override
	public void save(Producto t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) throws SQLException {
		// TODO Auto-generated method stub

	}
}
