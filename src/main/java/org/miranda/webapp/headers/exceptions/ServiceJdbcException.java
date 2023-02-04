package org.miranda.webapp.headers.exceptions;

public class ServiceJdbcException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;


	public ServiceJdbcException(String mensaje) {
		super(mensaje);
		
	}

	
	public ServiceJdbcException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
