package org.miranda.webapp.headers.models;

import java.util.Objects;

public class ItemCarro {
	private int cantidad;

	private Producto producto;

	public ItemCarro(int cantidad, Producto producto) {
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getImporte() {
		return cantidad * producto.getPrecio();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCarro other = (ItemCarro) obj;
		return Objects.equals(producto.getId(), other.producto.getId())
				&& Objects.equals(producto.getNombre(), other.producto.getNombre());
	}

}
