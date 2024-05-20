package br.com.ifsp.vcRiquinho.base.db;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

@Disabled
@Testcontainers
public class PostgresTestContainer {

    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:16")
            .withUsername("postgres")
            .withPassword("admin")
            .withExposedPorts(5432)
    		.withCopyFileToContainer(MountableFile.forHostPath(Paths.get("scripts", "init.sql").toAbsolutePath().toString()), "/docker-entrypoint-initdb.d/init.sql");

    @BeforeAll
    public static void setUp() {
        postgresContainer.start();
    }

    @Test
    public void testInitialData() throws Exception {
        String jdbcUrl = String.format("jdbc:postgresql://localhost:%d/dbtest_vcriquinho", postgresContainer.getMappedPort(5432));
        String username = postgresContainer.getUsername();
        String password = postgresContainer.getPassword();

        try (Connection conn = DriverManager.getConnection(jdbcUrl, "postgres", "admin");
             Statement stmt = conn.createStatement()) {

            // Verifique se os dados iniciais foram inseridos corretamente
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
    
    @AfterAll
    public static void tearDown() {
        postgresContainer.stop();
    }
}
