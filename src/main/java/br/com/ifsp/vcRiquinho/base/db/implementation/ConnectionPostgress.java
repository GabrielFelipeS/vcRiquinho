package br.com.ifsp.vcRiquinho.base.db.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.ifsp.vcRiquinho.base.db.exceptions.DbException;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;

public class ConnectionPostgress implements IDBConnector {
	private static Connection conn;
	private final String VARIAVEL_DE_AMBIENTE_DO_DOCKER = "POSTGRES_DBURL";

	@Override
	public Connection getConnection(String url, String user, String password) {
		if (connectionNotOpen()) {
			setConnection(url, user, password);
		}
		
		return conn;
	}
	
	private boolean connectionNotOpen() {
		try {
			return conn == null || conn.isClosed();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private void setConnection(String url, String user, String password) {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Connection getConnection() {
		return getConnectionByDockerOrLocalDataBase();
	}


	public Connection getConnectionByDockerOrLocalDataBase() {
		if (conn == null) {
			String dbUrl = System.getenv(VARIAVEL_DE_AMBIENTE_DO_DOCKER);

			if (dbUrl != null) {
				return getConnection("jdbc:postgresql://localhost:5432/vcriquinho", "wolke", "wolke2024");
			} else {
				return getConnection("jdbc:postgresql://localhost:5432/vcriquinho", "wolke", "wolke2024");
			}
		}

		return conn;
	}

	@Override
	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}
