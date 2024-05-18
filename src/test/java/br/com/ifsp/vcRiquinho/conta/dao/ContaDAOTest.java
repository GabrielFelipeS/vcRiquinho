package br.com.ifsp.vcRiquinho.conta.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContaDAOTest {
	private int DEFAULT_ID_EXISTS = 1;
	private int DEFAULT_ID_NOT_EXISTS = 1000;

	private ConnectionPostgress iDbConnector = new ConnectionPostgress();
	private Connection connection = iDbConnector.getConnection("jdbc:postgresql://localhost:5432/dbtest_vcriquinho",
			"postgres", "gabriel10*");

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

		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, 0, 0.065, "corrente");
		assertNotNull(dao.insert(dto));
	}

	@Test
	void insertTestFail() {
		ContaDAO dao = new ContaDAO(connection);

		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, 0, 0.065, "cdi");
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
	void findOptioanlByTestNotEmpty() {
		ContaDAO dao = new ContaDAO(connection);

		assertFalse(dao.findOptionalBy(DEFAULT_ID_EXISTS).isEmpty());
	}

	@Test
	void findOptioanlByTestEmpty() {
		ContaDAO dao = new ContaDAO(connection);

		assertThrows(RuntimeException.class , () -> dao.findOptionalBy(DEFAULT_ID_NOT_EXISTS).isEmpty());
	}

	@Test
	void updateByTestNotNull() {
		ContaDAO dao = new ContaDAO(connection);
		DTOConta dto = new DTOConta(DEFAULT_ID_EXISTS, "00111222000144", 0.0, 0, 0.065, "invesimento_automatico");
		assertNotNull(dao.updateBy(dto));
	}

	@Test
	void updateByTestNull() {
		ContaDAO dao = new ContaDAO(connection);
		DTOConta dto = new DTOConta(DEFAULT_ID_NOT_EXISTS, "00111222000144", 0.0, 5, 0.065, "invesimento_automatico");

		assertThrows(RuntimeException.class , () -> dao.updateBy(dto));
	}

	@Test
	void updateOptioanlByTestNotEmpty() {
		ContaDAO dao = new ContaDAO(connection);
		DTOConta dto = new DTOConta(DEFAULT_ID_EXISTS, "00111222000144", 0.0, 5, 0.065, "invesimento_automatico");

		Optional<DTOConta> newDto = dao.updateOptionalBy(dto);

		assertFalse(newDto.isEmpty());
		assertEquals(dto.id_produto(), newDto.get().id_produto());
	}

	@Test
	void updateOptioanlByTestEmpty() {
		ContaDAO dao = new ContaDAO(connection);
		DTOConta dto = new DTOConta(DEFAULT_ID_NOT_EXISTS, "00111222000144", 0.0, 5, 0.065, "invesimento_automatico");

		assertThrows(RuntimeException.class , () ->  dao.updateOptionalBy(dto));
	}

}
