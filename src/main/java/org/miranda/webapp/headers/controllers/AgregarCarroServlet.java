package org.miranda.webapp.headers.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

import org.miranda.webapp.headers.models.Carro;
import org.miranda.webapp.headers.models.ItemCarro;
import org.miranda.webapp.headers.models.Producto;
import org.miranda.webapp.headers.service.ProductoService;
import org.miranda.webapp.headers.service.ProductoServiceImpl;
import org.miranda.webapp.headers.service.ProductoServiceJdbcImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

 
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        System.out.println("id: "+id);
        Connection conn = (Connection)req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        Optional<Producto> producto = service.findById(id);
        if (producto.isPresent()) {
            ItemCarro item = new ItemCarro(1, producto.get());
            HttpSession session = req.getSession();
            Carro carro;
//            if (session.getAttribute("carro") == null) {
//                carro = new Carro();
//                session.setAttribute("carro", carro);
//            } else {
                carro = (Carro) session.getAttribute("carro");
           // }
            carro.addItem(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
