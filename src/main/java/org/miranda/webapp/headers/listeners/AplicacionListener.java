package org.miranda.webapp.headers.listeners;



import org.miranda.webapp.headers.models.Carro;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AplicacionListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {
	private ServletContext servletContext;
	
	
	  @Override public void contextInitialized(ServletContextEvent sce) {
	  sce.getServletContext().log("Inicializando aplicacion"); servletContext =
	  sce.getServletContext(); servletContext.setAttribute("mensaje",
	  "algun valor global"); }
	  
	  @Override public void contextDestroyed(ServletContextEvent sce) {
	  servletContext.log("Destruyendo aplicacion"); }
	  
	  
	  @Override public void requestInitialized(ServletRequestEvent sre) {
	  servletContext.log("Inicializando el request");
	  sre.getServletRequest().setAttribute("mensaje","guardando algun valor para el request ...");
	  //sre.getServletRequest().setAttribute("title","Catalogo Servlet");
	  }
	  
	  @Override public void requestDestroyed(ServletRequestEvent sre) {
	  sre.getServletContext().log("Destruyendo el request"); }
	  
	  @Override public void sessionCreated(HttpSessionEvent se) {
	  servletContext.log("Iniciando la sesion http"); Carro carro = new Carro();
	  HttpSession session = se.getSession(); session.setAttribute("carro", carro);
	  }
	  
	  @Override public void sessionDestroyed(HttpSessionEvent se) {
	  servletContext.log("Destruyendo la sesion http"); }
	 

	

	
}
