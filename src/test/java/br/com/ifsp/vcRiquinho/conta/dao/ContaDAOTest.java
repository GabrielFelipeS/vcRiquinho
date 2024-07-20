package br.com.ifsp.vcRiquinho.conta.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import br.com.ifsp.vcRiquinho.base.db.PostgresTestContainer;
import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;

class ContaDAOTest {
	private int ID_EXISTS = 1;
	private int ID_NOT_EXISTS = 1000;

	private static ConnectionPostgress iDbConnector = new ConnectionPostgress();
	private static Connection connection;

	/**
	 * O método setUp utiliza a dependencia TestContainer Para criar uma conexão com
	 * o banco de dados do container criado no momento de rodar os testes
	 */
	public static void setUp() {
		connection = PostgresTestContainer.connectInContainer(iDbConnector);
	}

	void afterEach() throws SQLException {
		String procedure = "{ call reset_table_in_conta() }";
		try (CallableStatement proc = connection.prepareCall(procedure)) {
			proc.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	void givenFindAll_whenConnectionDoesNotCloseAndDatabaseExists_thenReturnAllContas() {
		ContaDAO dao = new ContaDAO(connection);
		
		var result = dao.findAll();

		assertNotNull(result);
		assertTrue(result.size() > 0);
	}

	void givenInsert_whenAccountNotExists_thenInsertSuccess() {
		ContaDAO dao = new ContaDAO(connection);
		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, null, 0.065, "corrente");


		assertDoesNotThrow(() -> {
			DTOConta dtoReturned = dao.insert(dto);
			
			assertEquals("00111222000144", dtoReturned.documentoTitular());
			assertNotEquals(dto.numConta(), dtoReturned.numConta());
		});
	}

	void givenInsert_whenAccountAlreadyExistsForDocument_thenRuntimeException() {
		ContaDAO dao = new ContaDAO(connection);

		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, null, 0.065, "cdi");

		dao.insert(dto);
		assertThrows(RuntimeException.class, () -> dao.insert(dto));
	}

	void givenInsert_whenDocumentDoesNotExist_thenRuntimeException() {
		ContaDAO dao = new ContaDAO(connection);

		DTOConta dto = new DTOConta(0, "00000000000000", 0.0, null, 0.065, "cdi");

		assertThrows(RuntimeException.class, () -> dao.insert(dto));
	}

	void givenDeleteBy_whenIdExists_thenTrue() {
		ContaDAO dao = new ContaDAO(connection);

		assertTrue(dao.deleteBy(ID_EXISTS));
	}

	void givenDeleteBy_whenIdNotExists_thenThrowRuntimeException() {
		ContaDAO dao = new ContaDAO(connection);

		assertThrows(RuntimeException.class, () -> dao.deleteBy(ID_NOT_EXISTS));
	}

	void givenFindByDocument_whenDocumentExists_thenReturnThreeAccount() {
		final int QUANTIDADE_CONTAS_DA_PESSOA=3;
		ContaDAO dao = new ContaDAO(connection);

		String documentExists = "11122233344";
		
		
		var list = dao.findByDocument(documentExists);
		
		assertEquals(QUANTIDADE_CONTAS_DA_PESSOA, list.size());
	}

	void givenFindByDocument_whenDocumentDoesNotExist_thenThrowRuntimeException() {
		ContaDAO dao = new ContaDAO(connection);

		String documentDoesNotExist = "12345678955";
		
		assertThrows(RuntimeException.class, () -> dao.findByDocument(documentDoesNotExist));
	}

	void givenFindBy_whenDocumentExists_thenReurnNotNull() {
		ContaDAO dao = new ContaDAO(connection);

		assertNotNull(dao.findBy(ID_EXISTS));
	}

	void givenFindBy_whenContaNaoEncontrada_thenIDInexistenteExistente() {
		ContaDAO dao = new ContaDAO(connection);

		assertThrows(RuntimeException.class, () -> dao.findBy(ID_NOT_EXISTS));
	}

	void givenUpdate_whenIdExists_thenReturnNotNull() {
		ContaDAO dao = new ContaDAO(connection);
		DTOConta dto = new DTOConta(ID_EXISTS, "00111222000144", 0.0, null, 0.065, "invesimento_automatico");
		
		assertNotNull(dao.update(dto));
	}

	void givenUpdate_whenIdDoesNotExist_thenThrowRuntimeException() {
		ContaDAO dao = new ContaDAO(connection);
		DTOConta dto = new DTOConta(ID_NOT_EXISTS, "00111222000144", 0.0, 5, 0.065, "invesimento_automatico");

		assertThrows(RuntimeException.class, () -> dao.update(dto));
	}
}
