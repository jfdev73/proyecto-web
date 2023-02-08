package org.miranda.webapp.headers.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import org.miranda.webapp.headers.service.LoginService;
import org.miranda.webapp.headers.service.LoginServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String USERNAME = "admin";
    static final String PASSWORD = "1234";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Hola " + usernameOptional.get() + "</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Hola " + usernameOptional.get() + " has iniciado sesi�n con �xito!</h1>");
                out.println("<p><a href='" + req.getContextPath() + "/index.jsp'>volver</a></p>");
                out.println("<p><a href='" + req.getContextPath() + "/logout'>cerrar sesi�n</a></p>");
                out.println("    </body>");
                out.println("</html>");
            }
        } else {
        	req.setAttribute("title", "Login");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {

           HttpSession session = req.getSession();
           session.setAttribute("username", username);
            

            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta p�gina!");
        }
    }
}