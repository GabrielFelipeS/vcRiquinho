package br.com.ifsp.vcRiquinho.base.db.interfaces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.ifsp.vcRiquinho.base.db.exceptions.DbException;

public interface IDBConnector {
	Connection getConnection();
	Connection getConnection(String url, String user, String password);
	
	void closeConnection();
	void commit();
	void rollback();
}
