package br.com.ifsp.vcRiquinho.base.db.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.ifsp.vcRiquinho.base.db.exceptions.DbException;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;

public class ConnectionPostgress implements IDBConnector {
	private String url;
	private String user;
	private String password;
	
	private static Connection conn;
	
	public ConnectionPostgress() {
		
	}
	
	public ConnectionPostgress(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

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
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public Connection getConnection() {
		return tryConnectionByDockerOrLocalDataBase();
	}	
	
	public Connection tryConnectionByDockerOrLocalDataBase() {
		try {
			//System.out.println(System.getenv("DOCKER_POSTGRES_USER"));
			return getConnection(System.getenv("DOCKER_POSTGRES_URL"), System.getenv("DOCKER_POSTGRES_USER"), System.getenv("DOCKER_POSTGRES_PASSWORD"));
		} catch(RuntimeException e) {
			//System.out.println(System.getenv("LOCAL_POSTGRES_USER"));

			return getConnection(System.getenv("LOCAL_POSTGRES_URL"), System.getenv("LOCAL_POSTGRES_USER"), System.getenv("LOCAL_POSTGRES_PASSWORD"));
		}
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
