package org.miranda.webapp.headers.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.miranda.webapp.headers.exceptions.ServiceJdbcException;
import org.miranda.webapp.headers.util.ConexionBD;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class ConexionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try (Connection conn = ConexionBD.getConnection()) {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}

			try {
				request.setAttribute("conn", conn);
				chain.doFilter(request, response);
				conn.commit();
			} catch (SQLException | ServiceJdbcException e) {
				conn.rollback();
				
				((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
				e.printStackTrace();
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

}