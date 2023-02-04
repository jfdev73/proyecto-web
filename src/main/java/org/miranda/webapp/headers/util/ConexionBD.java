package org.miranda.webapp.headers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	
	private ConexionBD() {}

	private static final String url = "jdbc:postgresql://localhost:5432/java_curso";
	private static final String user = "postgres";
	private static final String password = "1234";
	private static Connection conn;

	
	
	  public static Connection getConnection() throws SQLException {
		  try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	  conn = DriverManager.getConnection(url, user,
	  password); return conn; }
	 
	 

}
