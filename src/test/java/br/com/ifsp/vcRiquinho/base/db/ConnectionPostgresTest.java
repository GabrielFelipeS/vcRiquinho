package br.com.ifsp.vcRiquinho.base.db;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;

//@Disabled
public class ConnectionPostgresTest {
	private static IDBConnector iDbConnector = new ConnectionPostgress();
	private static Connection connection;
	
	
	@BeforeAll
	public static void setUp() {
		connection = PostgresTestContainer.connectInContainer(iDbConnector);
	}
	
	@Test
	void connectionTest() throws SQLException {
		assertFalse(connection.isClosed());
		assertNotEquals(null, iDbConnector.getConnection());
		
	}
	
}
