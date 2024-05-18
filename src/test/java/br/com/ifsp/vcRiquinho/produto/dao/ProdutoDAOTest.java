package br.com.ifsp.vcRiquinho.produto.dao;

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
import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;

class ProdutoDAOTest {
	private Integer DEFAULT_ID_EXISTS = 1;
	private Integer DEFAULT_ID_NOT_EXISTS = 999999;

	private ConnectionPostgress iDbConnector = new ConnectionPostgress();
	private Connection connection = iDbConnector.getConnection("jdbc:postgresql://localhost:5433/dbtest_vcriquinho",
			"postgres", "admin");

	@AfterEach
	void afterEach() throws SQLException {
		String procedure = "{ call reset_table_in_produto_and_produto_conta() }";
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
		
		DTOProduto dto = new DTOProduto(DEFAULT_ID_NOT_EXISTS, 1825, "renda_fixa", "Fundo de Renda Fixa TESTE", "Fundo de investimento em renda fixa com baixo risco", 0.015);
		assertNotNull(dao.insert(dto));
	}

	@Test
	void insertTestFail() {
		ProdutoDAO dao = new ProdutoDAO(connection);
		
		DTOProduto dto = new DTOProduto(DEFAULT_ID_EXISTS, 1825, "renda_fixa", "Fundo de Renda Fixa ABC", "Fundo de investimento em renda fixa com baixo risco", 0.015);
		
		assertThrows(RuntimeException.class, () -> dao.insert(dto),  "ERROR: duplicate key value violates unique constraint \"produto_nome_key");
	}
	
	@Test
	void deleteByTestSucess() {
		ProdutoDAO dao = new ProdutoDAO(connection);

		assertTrue(dao.deleteBy(DEFAULT_ID_EXISTS));
	}

	@Test
	void deleteByTestFail() {
		ProdutoDAO dao = new ProdutoDAO(connection);

		assertThrows(RuntimeException.class, () ->dao.deleteBy(DEFAULT_ID_NOT_EXISTS));
	}

	@Test
	void findByTestNotNull() {
		ProdutoDAO dao = new ProdutoDAO(connection);

		assertNotNull(dao.findBy(DEFAULT_ID_EXISTS));
	}

	@Test
	void findByTestNull() {
		ProdutoDAO dao = new ProdutoDAO(connection);

		assertNull(dao.findBy(DEFAULT_ID_NOT_EXISTS));
	}

	@Test
	void findOptioanlByTestNotEmpty() {
		ProdutoDAO dao = new ProdutoDAO(connection);

		assertFalse(dao.findOptionalBy(DEFAULT_ID_EXISTS).isEmpty());
	}

	@Test
	void findOptioanlByTestEmpty() {
		ProdutoDAO dao = new ProdutoDAO(connection);

		assertTrue(dao.findOptionalBy(DEFAULT_ID_NOT_EXISTS).isEmpty());
	}

	@Test
	void updateByTestNotNull() {
		ProdutoDAO dao = new ProdutoDAO(connection);
		DTOProduto dto = new DTOProduto(DEFAULT_ID_EXISTS, 1825, "renda_fixa", "Fundo de Renda Fixa ABC", "Fundo de investimento em renda fixa com baixo risco", 0.015);
		assertNotNull(dao.updateBy(dto));
	}

	@Test
	void updateByTestNull() {
		ProdutoDAO dao = new ProdutoDAO(connection);
		DTOProduto dto = new DTOProduto(DEFAULT_ID_NOT_EXISTS, 1825, "renda_fixa", "Fundo de Renda Fixa ABC", "Fundo de investimento em renda fixa com baixo risco", 0.015);
		DTOProduto newDto = dao.updateBy(dto);
		assertNull(newDto);
	}

	@Test
	void updateOptioanlByTestNotEmpty() {
		ProdutoDAO dao = new ProdutoDAO(connection);
		DTOProduto dto = new DTOProduto(DEFAULT_ID_EXISTS, 1825, "renda_fixa", "Fundo de Renda Fixa ABC", "Fundo de investimento em renda fixa com baixo risco", 0.015);

		Optional<DTOProduto> newDto = dao.updateOptionalBy(dto);

		assertFalse(newDto.isEmpty());
	}

	@Test
	void updateOptioanlByTestEmpty() {
		ProdutoDAO dao = new ProdutoDAO(connection);
		DTOProduto dto = new DTOProduto(DEFAULT_ID_NOT_EXISTS, 1825, "renda_fixa", "Fundo de Renda Fixa ABC", "Fundo de investimento em renda fixa com baixo risco", 0.015);
		
		Optional<DTOProduto> newDto = dao.updateOptionalBy(dto);

		assertTrue(newDto.isEmpty());
	}

}
