package br.com.ifsp.vcRiquinho.pessoa.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;

class PessoaDAOTest {
	private String DEFAULT_ID_EXISTS = "12345678901";
	private String DEFAULT_ID_NOT_EXISTS = "99999999999";

	private ConnectionPostgress iDbConnector = new ConnectionPostgress();
	private Connection connection = iDbConnector.getConnection("jdbc:postgresql://localhost:5433/dbtest_vcriquinho",
			"postgres", "admin");

	@AfterEach
	void afterEach() throws SQLException {
		String procedure = "{ call reset_table_in_pessoa() }";
		try (CallableStatement proc = connection.prepareCall(procedure)) {
			proc.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	void findAllTest() {
		PessoaDAO dao = new PessoaDAO(connection);
		var result = dao.findAll();

		assertNotNull(result);
		assertTrue(result.size() > 0);
	}

	@Test
	void insertTestSucess() {
		PessoaDAO dao = new PessoaDAO(connection);
		
		DTOPessoa dto = new DTOPessoa(0, "12345678989", "Gabriel Felipe", "andrade.gabriel1@email.com", "fisica");
		assertNotNull(dao.insert(dto));
	}

	@Test
	void insertTestFail() {
		PessoaDAO dao = new PessoaDAO(connection);
		
		DTOPessoa dto = new DTOPessoa(0, "12345678989", "Gabriel Felipe", "andrade.gabriel1@email.com", "fisica");
		dao.insert(dto);
		assertThrows(RuntimeException.class, () -> dao.insert(dto), "ERRO: duplicar valor da chave viola a restrição de unicidade \"sem_duplicidade_conta_documento");
	}

	@Test
	void deleteByTestSucess() {
		PessoaDAO dao = new PessoaDAO(connection);

		assertTrue(dao.deleteBy(DEFAULT_ID_EXISTS));
	}

	@Test
	void deleteByTestFail() {
		PessoaDAO dao = new PessoaDAO(connection);

		assertThrows(RuntimeException.class, () ->dao.deleteBy(DEFAULT_ID_NOT_EXISTS));
	}

	@Test
	void findByTestNotNull() {
		PessoaDAO dao = new PessoaDAO(connection);

		assertNotNull(dao.findBy("12345678901"));
	}

	@Test
	void findByTestNull() {
		PessoaDAO dao = new PessoaDAO(connection);

		assertNull(dao.findBy(DEFAULT_ID_NOT_EXISTS));
	}

	@Test
	void findOptioanlByTestNotEmpty() {
		PessoaDAO dao = new PessoaDAO(connection);

		assertFalse(dao.findOptionalBy(DEFAULT_ID_EXISTS).isEmpty());
	}

	@Test
	void findOptioanlByTestEmpty() {
		PessoaDAO dao = new PessoaDAO(connection);

		assertTrue(dao.findOptionalBy(DEFAULT_ID_NOT_EXISTS).isEmpty());
	}

	@Test
	void updateByTestNotNull() {
		PessoaDAO dao = new PessoaDAO(connection);
		DTOPessoa dto = new DTOPessoa(0, DEFAULT_ID_EXISTS, "João Silva", "joaosilva@email.com", "fisica");
		assertNotNull(dao.updateBy(dto));
	}

	@Test
	void updateByTestNull() {
		PessoaDAO dao = new PessoaDAO(connection);
		DTOPessoa dto = new DTOPessoa(0, DEFAULT_ID_NOT_EXISTS, "João Silva", "joaosilva@email.com", "fisica");
		DTOPessoa newDto = dao.updateBy(dto);
		assertNull(newDto);
	}

	@Test
	void updateOptioanlByTestNotEmpty() {
		PessoaDAO dao = new PessoaDAO(connection);
		DTOPessoa dto = new DTOPessoa(0, DEFAULT_ID_EXISTS, "João Silva", "joaosilva@email.com", "fisica");

		Optional<DTOPessoa> newDto = dao.updateOptionalBy(dto);

		assertFalse(newDto.isEmpty());
		//assertEquals(dto.id_produto(), newDto.get().id_produto());
	}

	@Test
	void updateOptioanlByTestEmpty() {
		PessoaDAO dao = new PessoaDAO(connection);
		DTOPessoa dto = new DTOPessoa(0, DEFAULT_ID_NOT_EXISTS, "João Silva", "joaosilva@email.com", "fisica");

		Optional<DTOPessoa> newDto = dao.updateOptionalBy(dto);

		assertTrue(newDto.isEmpty());
	}

}
