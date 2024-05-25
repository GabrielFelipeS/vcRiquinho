package br.com.ifsp.vcRiquinho.base.db;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;

//@Disabled
//@Testcontainers
public class PostgresTestContainer {
	private static Connection connection;

	@Container
	public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:16")
			.withUsername("postgres")
			.withPassword("admin")
			.withExposedPorts(5432)
			.withCopyFileToContainer(
					MountableFile.forHostPath(
							Paths.get("scripts", "init.sql").toAbsolutePath().toString()),
							"/docker-entrypoint-initdb.d/init.sql");

	@BeforeAll
	public static void setUp() {
		connection = connectInContainer(new ConnectionPostgress());
	}

	@Test
	public void testInitialData() throws Exception {
		try (Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM produto");
			assertTrue(rs.next());
			assertTrue(rs.next());
		}
	}

	@Test
	void connectionEstablished() {
		assertTrue(postgresContainer.isCreated());
		assertTrue(postgresContainer.isRunning());
	}
	
	public static Connection connectInContainer(IDBConnector iDbConnector) {
		PostgresTestContainer.postgresContainer.start();

		return iDbConnector.getConnection(
				String.format("jdbc:postgresql://localhost:%d/dbtest_vcriquinho", PostgresTestContainer.postgresContainer.getMappedPort(5432)),
				PostgresTestContainer.postgresContainer.getUsername(),
				PostgresTestContainer.postgresContainer.getPassword());
	}

}
