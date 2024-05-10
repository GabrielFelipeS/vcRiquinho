package br.com.ifsp.vcRiquinho.base.db.interfaces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.ifsp.vcRiquinho.base.db.exceptions.DbException;

public interface IDBConnector {
	Connection getConnection();
	Connection getConnection(String url, String user, String password);
	
	// Primeira POG do projeto
	default Connection getConnectionByDockerOrLocalDataBase() {
		return null;
	}
	
	void closeConnection();

	default void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	default void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}
