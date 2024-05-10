package br.com.ifsp.vcRiquinho.base.db;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;

public class ConnectionPostgresTest {
	/*
	@Test
	void connectionTest() {
		ConnectionPostgress dbConnection = new ConnectionPostgress();
		
		assertDoesNotThrow(() -> {
			dbConnection.getConnectionByDockerOrLocalDataBase();
			dbConnection.closeConnection();
		});
		
		assertNotEquals(null, dbConnection.getConnection());
	}
	
	@Test
	void verifyConnection() {
		ConnectionPostgress dbConnection = new ConnectionPostgress();
		Connection connection = dbConnection.getConnectionByDockerOrLocalDataBase();
		
		assertDoesNotThrow(() -> {
			Statement st  =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet  rs = st.executeQuery("select * from TestConection");
			int QUANTIDADE_VALORES = 3;
			rs.last();
			int quanidadeDeLinhasBuscadas =  rs.getRow();
			rs.beforeFirst();
			assertEquals(QUANTIDADE_VALORES,quanidadeDeLinhasBuscadas);
			
			dbConnection.closeResultSet(rs);
			dbConnection.closeStatement(st);
		});
		
		dbConnection.closeConnection();
	}
	
	*/
}
