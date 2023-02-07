package org.miranda.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

import org.miranda.webapp.headers.models.Producto;
import org.miranda.webapp.headers.service.ProductoService;
import org.miranda.webapp.headers.service.ProductoServiceImpl;
import org.miranda.webapp.headers.service.ProductoServiceJdbcImpl;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) request.getAttribute("conn");
		ProductoService service = new ProductoServiceJdbcImpl(conn);
		Long id;
		try {
			id = Long.valueOf(request.getParameter("id"));
			
		} catch (NumberFormatException e) {
			id = 0L;
		}
		
		if(id > 0 ) {
			Optional<Producto> producto = service.findById(id);
			if(producto.isPresent()) {
			service.deleteById(id);
			response.sendRedirect(request.getContextPath() + "/productos");
			}else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el producto");
			}
		}else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el producto");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
