package org.miranda.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.miranda.webapp.headers.models.Categoria;
import org.miranda.webapp.headers.models.Producto;
import org.miranda.webapp.headers.repository.GenericRepository;
import org.miranda.webapp.headers.service.CategoriaService;
import org.miranda.webapp.headers.service.CategoriaServiceJdbcImpl;
import org.miranda.webapp.headers.service.ProductoService;
import org.miranda.webapp.headers.service.ProductoServiceJdbcImpl;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn =  (Connection) request.getAttribute("conn");
		CategoriaService serviceCategoria = new CategoriaServiceJdbcImpl(conn);
		ProductoService serviceProducto = new ProductoServiceJdbcImpl(conn);
		List<Categoria> categorias = serviceCategoria.listar();
		
		Long idProducto = (request.getParameter("id") != null && !request.getParameter("id").isBlank()) 
				? Long.valueOf(request.getParameter("id")) : 0L;
		
		Producto producto = new Producto();
		if(idProducto>0) {
			Optional<Producto> o = serviceProducto.findById(idProducto);
			if(o.isPresent())
				producto = o.get();
		}
		request.setAttribute("categorias", categorias);
		request.setAttribute("producto", producto);
		request.setAttribute("title", "Formulario de productos");
		request.getRequestDispatcher("/form.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn =  (Connection) request.getAttribute("conn");
		ProductoService service = new ProductoServiceJdbcImpl(conn);
		CategoriaService serviceCategoria = new CategoriaServiceJdbcImpl(conn);
		
		String nombre = request.getParameter("nombre");
		String auxPrecio = request.getParameter("precio");
		
		System.out.println("precio: "+auxPrecio);
		
		/*
		 * Integer precio = (request.getParameter("precio") != null &&
		 * !request.getParameter("precio").isBlank() &&
		 * !request.getParameter("precio").equals("")) ?
		 * Integer.parseInt(request.getParameter("precio")) : 0;
		 */
		
		Integer precio = (request.getParameter("precio") != null && !request.getParameter("precio").isBlank()) ? 
				Integer.valueOf(request.getParameter("precio")) : null;
		
		
		String sku = request.getParameter("sku");
		//String auxCategoria = request.getParameter("precio");
		
		Long idCategoria = (request.getParameter("categoria") != null && !request.getParameter("categoria").isBlank()) 
				? Long.valueOf(request.getParameter("categoria")) : 0L;
		String aux_id = request.getParameter("id"); 
		System.out.println("id: "+aux_id);
		Long idProducto = null;
		try {
			 idProducto = Long.valueOf(request.getParameter("id"));
		} catch (NumberFormatException e) {
			idProducto = null;
		}
		
		
		Map<String, String> errores = new HashMap<>();
		
		if(nombre== null || nombre.isBlank())
			errores.put("nombre", "el nombre es requerido!");
		
		if(sku== null || sku.isBlank()) {
			errores.put("sku", "el sku es requerido!");
			}else if (sku.length()>10) {
				errores.put("sku", "el sku debe tener max 10 caracteres!");	
				
			}
		
		if( (precio == null) || precio.equals(0)){
			errores.put("precio", "el precio es requerido!");
		}else if (precio < 0) {
			errores.put("precio", "el precio es invalido!");
		}
		
		
			
		
		if(idCategoria.equals(0L))
			errores.put("categoria", "la categoria es requerida!");
		
		Producto producto = new Producto();
		producto.setId(idProducto);
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setSku(sku);
		Categoria c = new Categoria();
		c.setId(idCategoria);
		producto.setCategoria(c);
		
		if(errores.isEmpty()) {
			service.save(producto);
			response.sendRedirect(request.getContextPath()+"/productos");
		}else {
			request.setAttribute("errores", errores);
			request.setAttribute("categorias", serviceCategoria.listar());
			request.setAttribute("producto", producto);
			request.setAttribute("title", "Formulario de productos");
			request.getRequestDispatcher("/form.jsp").forward(request, response);
			
		}
		
		

	}

}
