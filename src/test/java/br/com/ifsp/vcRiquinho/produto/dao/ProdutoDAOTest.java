package br.com.ifsp.vcRiquinho.produto.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.MountableFile;

import br.com.ifsp.vcRiquinho.base.db.PostgresTestContainer;
import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;

class ProdutoDAOTest {
	private Integer DEFAULT_ID_EXISTS = 1;
	private Integer DEFAULT_ID_NOT_EXISTS = 999999;

	private static ConnectionPostgress iDbConnector = new ConnectionPostgress();
	private static Connection connection;

	
	
	/**
	 * O método setUp utiliza a dependencia TestContainer 
	 * Para criar uma conexão com o banco de dados do container criado no momento de rodar os testes
	 * 
	 * caso queira testar utilizando o 
	 */
	@BeforeAll
	public static void setUp() {
		connection = PostgresTestContainer.connectInNewContainer(iDbConnector);
		
		//iDbConnector.getConnection(ConnectionPostgress.DEFAULT_URL_DBTEST, ConnectionPostgress.DEFAULT_USER_DBTEST, ConnectionPostgress.DEFAULT_PASSWORD_DBTEST);
	}

	@AfterEach
	void afterEach() throws SQLException {
		String procedure = "{ call reset_table_in_produto_and_conta() }";
		try (CallableStatement proc = connection.prepareCall(procedure)) {
			proc.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	void findAllTest() {
		ProdutoDAO dao = new ProdutoDAO(connection);
		var result = dao.findAll();

		assertNotNull(result);
		assertTrue(result.size() > 0);
	}

	@Test
	void insertTestSucess() {
		ProdutoDAO dao = new ProdutoDAO(connection);

		DTOProduto dto = new DTOProduto(DEFAULT_ID_NOT_EXISTS, 1825, "renda_fixa", "Fundo de Renda Fixa TESTE",
				"Fundo de investimento em renda fixa com baixo risco", 0.015);
		assertNotNull(dao.insert(dto));
	}

	@Test
	void insertTestFail() {
		ProdutoDAO dao = new ProdutoDAO(connection);

		DTOProduto dto = new DTOProduto(DEFAULT_ID_EXISTS, 1825, "renda_fixa", "Fundo de Renda Fixa ABC",
				"Fundo de investimento em renda fixa com baixo risco", 0.015);

		assertThrows(RuntimeException.class, () -> dao.insert(dto),
				"ERROR: duplicate key value violates unique constraint \"produto_nome_key");
	}

	@Test
	void deleteByTestSucess() {
		ProdutoDAO dao = new ProdutoDAO(connection);

		assertTrue(dao.deleteBy(DEFAULT_ID_EXISTS));
	}

	@Test
	void deleteByTestFail() {
		ProdutoDAO dao = new ProdutoDAO(connection);

		assertThrows(RuntimeException.class, () -> dao.deleteBy(DEFAULT_ID_NOT_EXISTS));
	}

	@Test
	void findByTestNotNull() {
		ProdutoDAO dao = new ProdutoDAO(connection);

		assertNotNull(dao.findBy(DEFAULT_ID_EXISTS));
	}

	@Test
	void findByTestNull() {
		ProdutoDAO dao = new ProdutoDAO(connection);

		assertThrows(RuntimeException.class, () -> dao.findBy(DEFAULT_ID_NOT_EXISTS));
	}

	@Test
	void updateByTestNotNull() {
		ProdutoDAO dao = new ProdutoDAO(connection);
		DTOProduto dto = new DTOProduto(DEFAULT_ID_EXISTS, 1825, "renda_fixa", "Fundo de Renda Fixa ABC",
				"Fundo de investimento em renda fixa com baixo risco", 0.015);
		assertNotNull(dao.update(dto));
	}

	@Test
	void updateByTestNull() {
		ProdutoDAO dao = new ProdutoDAO(connection);
		DTOProduto dto = new DTOProduto(DEFAULT_ID_NOT_EXISTS, 1825, "renda_fixa", "Fundo de Renda Fixa ABC",
				"Fundo de investimento em renda fixa com baixo risco", 0.015);
		
		assertThrows(RuntimeException.class, () -> dao.findBy(DEFAULT_ID_NOT_EXISTS));
	}
}
