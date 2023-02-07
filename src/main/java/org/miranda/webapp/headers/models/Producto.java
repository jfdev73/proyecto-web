package org.miranda.webapp.headers.models;

import java.time.LocalDate;


public class Producto {
	private Long id;
	private String nombre;
	private Categoria categoria;
	private Integer precio;
	private String sku;
	private LocalDate fechaRegistro;

	public Producto() {
		this.nombre = "";
		this.categoria = new Categoria();
		//this.precio= 0;
		this.sku = "";
	}

	public Producto(Long id, String nombre, String categoria, int precio) {
		this.id = id;
		this.nombre = nombre;
		Categoria c = new Categoria();
		c.setCategoria(categoria);
		this.categoria = c;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", precio=" + precio
				+ ", sku=" + sku + ", fechaRegistro=" + fechaRegistro + "]";
	}
	
	

	
}
