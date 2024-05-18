package br.com.ifsp.vcRiquinho.base.db;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;

public class ConnectionPostgresTest {
	
	@Test
	void connectionTest() {
		ConnectionPostgress dbConnection = new ConnectionPostgress();
		
		assertDoesNotThrow(() -> {
			dbConnection.getConnection("jdbc:postgresql://localhost:5432/dbtest_vcriquinho", "wolke", "wolke2024");
			dbConnection.closeConnection();
		});
		
		assertNotEquals(null, dbConnection.getConnection());
	}
	
}
