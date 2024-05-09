package br.com.ifsp.vcRiquinho.base.db;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;

public class ConnectionPostgresTest {
	@Test
	void connectionTest() {
		IDBConnector dbConnection = new ConnectionPostgress();
		
		assertDoesNotThrow(() -> {
			dbConnection.getConnection();
			dbConnection.closeConnection();
		});
		
	}
	
	@Test
	void verifyConnection() {
		IDBConnector dbConnection = new ConnectionPostgress();
		Connection connection = dbConnection.getConnection();
		
		assertDoesNotThrow(() -> {
			Statement st = connection.createStatement();
			
			ResultSet  rs = st.executeQuery("select * from TestConection");
			rs.last();
					
			int QUANTIDADE_VALORES = 3;
			assertEquals(QUANTIDADE_VALORES, rs.getRow());
			
			dbConnection.closeResultSet(rs);
			dbConnection.closeStatement(st);
		});
		
		dbConnection.closeConnection();
	}
}
