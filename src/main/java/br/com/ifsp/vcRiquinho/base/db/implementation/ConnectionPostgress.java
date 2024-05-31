package br.com.ifsp.vcRiquinho.base.db.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.ifsp.vcRiquinho.base.db.exceptions.DbException;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;

public class ConnectionPostgress implements IDBConnector {
	public static final String DEFAULT_URL_DBTEST = "jdbc:postgresql://db:5433/vcriquinho";
	public static final String DEFAULT_USER_DBTEST = "postgres";
	public static final String DEFAULT_PASSWORD_DBTEST = "admin";
	
	private String url;
	private String user;
	private String password;
	
	private Connection conn;

	public Connection getConnection(String url, String user, String password) {
		if (connectionNotOpen()) {
			setConnection(url, user, password);
			setter(url, user, password);
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
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setter(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	 
	@Override
	public Connection getConnection() {
		if(url != null) {
			return getConnection(url, user, password);
		}
		
		return tryConnectionByDockerOrLocalDataBase();
	}	
	
	public Connection tryConnectionByDockerOrLocalDataBase() {
		try {
			return getConnection(System.getenv("POSTGRES_URL"), System.getenv("POSTGRES_USER"), System.getenv("POSTGRES_PASSWORD"));
		} catch(RuntimeException e) {
			return getConnection(DEFAULT_URL_DBTEST, DEFAULT_USER_DBTEST, DEFAULT_PASSWORD_DBTEST);
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
	
	@Override
	public void commit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void rollback() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void disableAutoCommit() {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			new RuntimeException(e.getMessage());
		}
	}



}
