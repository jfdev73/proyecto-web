package org.miranda.webapp.headers.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.miranda.webapp.headers.models.Categoria;
import org.miranda.webapp.headers.models.Producto;

public class ProductoRepositoryJdbcImpl implements GenericRepository<Producto, Long> {
	private Connection conn;

	public ProductoRepositoryJdbcImpl(Connection conn) {
		this.conn = conn;
	}

	private Producto getProducto(ResultSet rs) throws SQLException {
		Producto producto = new Producto();
		Categoria categoria = new Categoria();
		producto.setId(rs.getLong(1));
		producto.setNombre(rs.getString(2));
		producto.setPrecio(rs.getInt(3));
		producto.setSku(rs.getString("sku"));
		producto.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
		categoria.setId(rs.getLong("id_categoria"));
		categoria.setCategoria(rs.getString(8));
		producto.setCategoria(categoria);
		return producto;
	}

	@Override
	public List<Producto> getAll() throws SQLException {
		String sql = "SELECT * FROM producto as p inner join categoria as c ON p.categoria=c.id_categoria order by p.id_producto";
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
		String sql = "SELECT * FROM producto as p inner join categoria as c ON p.categoria=c.id_categoria WHERE id_producto=?";
		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setLong(1, id);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					producto = getProducto(rs);
				}

			}

		}
		return producto;
	}

	@Override
	public void save(Producto producto) throws SQLException {
		System.out.println("Producto a guardar");
		System.out.println(producto);

		boolean isUpdate = producto.getId() != null && producto.getId() > 0;
		System.out.println("isUpdate: " + isUpdate);

		String update = "UPDATE producto set producto=?, precio=?, sku=?, categoria=? WHERE id_producto=?";
		String insert = "INSERT INTO producto (producto,precio,sku,categoria,fecha_registro) VALUES(?,?,?,?,now())";
		String sql = isUpdate ? update : insert;
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, producto.getNombre());
			ps.setInt(2, producto.getPrecio());
			ps.setString(3, producto.getSku());
			ps.setLong(4, producto.getCategoria().getId());
			if (isUpdate) {
				ps.setLong(5, producto.getId());
			}
			ps.executeUpdate();
		}

	}

	@Override
	public void delete(Long id) throws SQLException {
		String sql = "DELETE FROM producto WHERE id_producto = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.executeUpdate();

		}
	}
}
