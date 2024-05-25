package br.com.ifsp.vcRiquinho.produto.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.db.PostgresTestContainer;
import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.produto.dao.IProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public class RepositoryProdutoTest {
	private static IDBConnector iDbConnector = new ConnectionPostgress();
	private static Connection connection;

	private IProdutoDAO dao = new ProdutoDAO(connection);
	private IFactoryProdutoCreator factoryProdutoCreator = new FactoryProdutoCreator();
	private IRepositoryProduto repository = new RepositoryProduto(dao, factoryProdutoCreator);

	private int ID_EXISTS = 1;
	private int ID_NOT_EXISTS = 1000;

	/**
	 * O método setUp utiliza a dependencia TestContainer Para criar uma conexão com
	 * o banco de dados do container criado no momento de rodar os testes
	 */
	@BeforeAll
	public static void setUp() {
		connection = PostgresTestContainer.connectInNewContainer(iDbConnector);

		// iDbConnector.getConnection(ConnectionPostgress.DEFAULT_URL_DBTEST,
		// ConnectionPostgress.DEFAULT_USER_DBTEST,
		// ConnectionPostgress.DEFAULT_PASSWORD_DBTEST);
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
	void findByIdExistenteEntaoSemLançamentoDeExceção() {
		assertDoesNotThrow(() -> repository.findBy(ID_EXISTS));
	}

	@Test
	void findByIdNaoExistenteEntaoLancaDeExceção() {
		assertThrows(RuntimeException.class, () -> repository.findBy(ID_NOT_EXISTS));
	}

	@Test
	void findAllTestSemErros() {
		assertDoesNotThrow(() -> repository.findAll());
	}

	@Test
	void findAllTestErroDeConexao() throws SQLException {
		connection.close();

		IProdutoDAO outroDAO = new ProdutoDAO(connection);
		IRepositoryProduto repository = new RepositoryProduto(outroDAO, factoryProdutoCreator);
		setUp();

		assertThrows(RuntimeException.class, () -> repository.findAll());
	}

	@Test
	void insertTestCriacaoBemSucedida() {
		DTOProduto dto = new DTOProduto(0, 0, "renda_variavel", "TESTE_NOME", "TESTE_DESCRICAO", 0.0);

		Produto produto = repository.insert(dto);

		assertEquals(dto.nome(), produto.getNome());
	}

	@Test
	void insertTestFalhaNaCriacaoNomeRepedito() {
		DTOProduto dto = new DTOProduto(0, 0, "renda_variavel", "TESTE_NOME", "TESTE_DESCRICAO", 0.0);

		repository.insert(dto);
		assertThrows(RuntimeException.class, () -> repository.insert(dto));
	}

	@Test
	void updateTestSucessoNaAtualizacao() {
		DTOProduto dto = new DTOProduto(ID_EXISTS, 0, "renda_variavel", "TESTE_NOME", "TESTE_DESCRICAO", 0.0);

		Produto produto = repository.update(dto);

		assertEquals(dto.nome(), produto.getNome());
	}

	@Test
	void updateTestFalhaNumContaNaoExisteNenhumaLinhaAfetada() {
		DTOProduto dto = new DTOProduto(ID_NOT_EXISTS, 0, "renda_variavel", "TESTE_NOME", "TESTE_DESCRICAO", 0.0);

		assertThrows(RuntimeException.class, () -> repository.update(dto));
	}

	@Test
	void deleteTestContaDeletadaComSucesso() {
		DTOProduto dto = new DTOProduto(ID_EXISTS, 0, "renda_variavel", "TESTE_NOME", "TESTE_DESCRICAO", 0.0);

		assertDoesNotThrow(() -> repository.deleteBy(dto.id()));
	}

	@Test
	void deleteTestFalhaNenhumaLinhaAfetada() {
		DTOProduto dto = new DTOProduto(ID_NOT_EXISTS, 0, "renda_variavel", "TESTE_NOME", "TESTE_DESCRICAO", 0.0);

		assertThrows(RuntimeException.class, () -> repository.deleteBy(dto.id()));
	}
}
