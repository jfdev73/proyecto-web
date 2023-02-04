package org.miranda.webapp.headers.repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository <T, ID>{
	
	List<T> getAll() throws SQLException;
	
	T findById(ID id) throws SQLException;
	
	void save(T t) throws SQLException;
	
	void delete(ID id) throws SQLException;

}
