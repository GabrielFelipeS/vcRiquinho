package br.com.ifsp.vcRiquinho.conta.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.ifsp.vcRiquinho.base.db.PostgresTestContainer;
import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;

@TestInstance(Lifecycle.PER_CLASS)
class ContaDAOTest {
	private int DEFAULT_ID_EXISTS = 1;
	private int DEFAULT_ID_NOT_EXISTS = 1000;

	private static ConnectionPostgress iDbConnector = new ConnectionPostgress();
	private static Connection connection;
	
	
	/**
	 * O método setUp utiliza a dependencia TestContainer 
	 * Para criar uma conexão com o banco de dados do container criado no momento de rodar os testes
	 */
	@BeforeAll
	public static void setUp() {
		connection = PostgresTestContainer.connectInNewContainer(iDbConnector);
		
		//iDbConnector.getConnection(ConnectionPostgress.DEFAULT_URL_DBTEST, ConnectionPostgress.DEFAULT_USER_DBTEST, ConnectionPostgress.DEFAULT_PASSWORD_DBTEST);
	}
	
	@AfterAll
	void beforeAll() {
		iDbConnector.closeConnection();
	}
	
	@AfterEach
	void afterEach() throws SQLException {
		String procedure = "{ call reset_table_in_conta() }";
		try (CallableStatement proc = connection.prepareCall(procedure)) {
			proc.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}


	@Test
	void findAllTest() {
		ContaDAO dao = new ContaDAO(connection);
		var result = dao.findAll();
		
		assertNotNull(result);
		assertTrue(result.size() > 0);
	}

	@Test
	void insertTestSucess() {
		ContaDAO dao = new ContaDAO(connection);

		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, null, 0.065, "corrente");
		assertNotNull(dao.insert(dto));
	}

	@Test
	void insertTestFail() {
		ContaDAO dao = new ContaDAO(connection);

		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, null, 0.065, "cdi");
		dao.insert(dto);
		assertThrows(RuntimeException.class, () -> dao.insert(dto), "ERRO: duplicar valor da chave viola a restrição de unicidade \"sem_duplicidade_conta_documento");
	}

	@Test
	void deleteByTestSucess() {
		ContaDAO dao = new ContaDAO(connection);

		assertTrue(dao.deleteBy(DEFAULT_ID_EXISTS));
	}

	@Test
	void deleteByTestFail() {
		ContaDAO dao = new ContaDAO(connection);

		assertThrows(RuntimeException.class , () -> dao.deleteBy(DEFAULT_ID_NOT_EXISTS));
	}

	@Test
	void findByTestNotNull() {
		ContaDAO dao = new ContaDAO(connection);

		assertNotNull(dao.findBy(DEFAULT_ID_EXISTS));
	}

	@Test
	void findByTestNull() {
		ContaDAO dao = new ContaDAO(connection);

		assertThrows(RuntimeException.class , () -> dao.findBy(DEFAULT_ID_NOT_EXISTS));
	}


	@Test
	void updateByTestNotNull() {
		ContaDAO dao = new ContaDAO(connection);
		DTOConta dto = new DTOConta(DEFAULT_ID_EXISTS, "00111222000144", 0.0, null, 0.065, "invesimento_automatico");
		assertNotNull(dao.updateBy(dto));
	}

	@Test
	void updateByTestNull() {
		ContaDAO dao = new ContaDAO(connection);
		DTOConta dto = new DTOConta(DEFAULT_ID_NOT_EXISTS, "00111222000144", 0.0, 5, 0.065, "invesimento_automatico");

		assertThrows(RuntimeException.class , () -> dao.updateBy(dto));
	}
}
