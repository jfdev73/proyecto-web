package org.miranda.webapp.headers.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import org.miranda.webapp.headers.models.Producto;
import org.miranda.webapp.headers.service.LoginService;
import org.miranda.webapp.headers.service.LoginServiceImpl;
import org.miranda.webapp.headers.service.ProductoService;
import org.miranda.webapp.headers.service.ProductoServiceImpl;
import org.miranda.webapp.headers.service.ProductoServiceJdbcImpl;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	Connection conn = (Connection)req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceImpl();
        Optional<String> usernameOptional = auth.getUsername(req);
        //Se crea en cada peticion request
        String mensajeRequest = (String) req.getAttribute("mensaje");
        // se crea una vez al iniciar la aplicacion y se destruye al finalizar
        String mensajeApp = (String) getServletContext().getAttribute("mensaje");
        req.setAttribute("productos", productos);
        req.setAttribute("username", usernameOptional);
        req.getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}