package br.com.ifsp.vcRiquinho.conta.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.db.PostgresTestContainer;
import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.conta.dao.ContaDAO;
import br.com.ifsp.vcRiquinho.conta.dao.IContaDAO;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.produto.repository.IRepositoryProduto;
import br.com.ifsp.vcRiquinho.produto.repository.RepositoryProduto;

class RepositoryContaTest {
	private static IDBConnector iDbConnector = new ConnectionPostgress();
	private static Connection connection;

	private IContaDAO contaDAO = new ContaDAO(connection);
	private IRepositoryProduto repositoryProduto = new RepositoryProduto(new ProdutoDAO(connection),
			new FactoryProdutoCreator());
	private IFactoryContaCreatorProvider factoryContaCreatorProvider = new FactoryContaCreatorProvider();

	private int ID_EXISTS = 1;
	private int ID_NOT_EXISTS = 1000;

	/**
	 * O metodo setUp utiliza a dependencia TestContainer Para criar uma conexão com
	 * o banco de dados do container criado no momento de rodar os testes
	 */
	@BeforeAll
	public static void setUp() {
		connection = getConnection(iDbConnector);

		// iDbConnector.getConnection(ConnectionPostgress.DEFAULT_URL_DBTEST,
		// ConnectionPostgress.DEFAULT_USER_DBTEST,
		// ConnectionPostgress.DEFAULT_PASSWORD_DBTEST);
	}

	private static Connection getConnection(IDBConnector iDbConnector2) {
		return PostgresTestContainer.connectInContainer(iDbConnector);
	}

	@Test
	void findByIdExistenteEntaoSemLancamentoDeExcecao() {
		IRepositoryConta repository = new RepositoryConta(contaDAO, repositoryProduto, factoryContaCreatorProvider);

		assertDoesNotThrow(() -> repository.findBy(ID_EXISTS));
	}

	@Test
	void findByIdNaoExistenteEntaoLancaDeExcecao() {
		IRepositoryConta repository = new RepositoryConta(contaDAO, repositoryProduto, factoryContaCreatorProvider);

		assertThrows(RuntimeException.class, () -> repository.findBy(ID_NOT_EXISTS));
	}

	@Test
	void findAllTestSemErros() {
		IRepositoryConta repository = new RepositoryConta(contaDAO, repositoryProduto, factoryContaCreatorProvider);

		assertDoesNotThrow(() -> repository.findAll());
	}

	@Test
	void findAllTestErroDeConexao() throws SQLException {
		connection.close();
		connection = getConnection(iDbConnector);

		IContaDAO outroDAO = new ContaDAO(connection);
		IRepositoryConta repository = new RepositoryConta(outroDAO, repositoryProduto, factoryContaCreatorProvider);

		assertThrows(RuntimeException.class, () -> repository.findAll());
	}

	@Test
	void insertTestCriacaoBemSucedida() {
		IRepositoryConta repository = new RepositoryConta(contaDAO, repositoryProduto, factoryContaCreatorProvider);
		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, null, 0.065, "corrente");

		Conta conta = repository.insert(dto);

		assertEquals("00111222000144", conta.getDocumentoTitular());
		assertNotEquals(dto.numConta(), conta.getNumConta());
	}

	@Test
	void insertTestFalhaNaCriacaoTipoDeContaJaExisteParaDocumento() {
		IRepositoryConta repository = new RepositoryConta(contaDAO, repositoryProduto, factoryContaCreatorProvider);

		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, null, 0.065, "cdi");

		repository.insert(dto);
		assertThrows(RuntimeException.class, () -> repository.insert(dto));
	}

	@Test
	void updateTestSucessoNaAtualizacao() {
		IRepositoryConta repository = new RepositoryConta(contaDAO, repositoryProduto, factoryContaCreatorProvider);

		DTOConta dto = new DTOConta(1, "00111222000144", 0.0, 5, 0.065, "investimento_automatico");

		Conta conta = repository.update(dto);

		assertEquals(dto.documentoTitular(), conta.getDocumentoTitular());
	}

	@Test
	void updateTestFalhaNumContaNaoExisteNenhumaLinhaAfetada() {
		IRepositoryConta repository = new RepositoryConta(contaDAO, repositoryProduto, factoryContaCreatorProvider);

		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, null, 0.065, "cdi");

		assertThrows(RuntimeException.class, () -> repository.update(dto));
	}

	@Test
	void deleteTestContaDeletadaComSucesso() {
		IRepositoryConta repository = new RepositoryConta(contaDAO, repositoryProduto, factoryContaCreatorProvider);

		DTOConta dto = new DTOConta(1, "00111222000144", 0.0, null, 0.065, "investimento_automatico");

		assertDoesNotThrow(() -> repository.deleteBy(dto.numConta()));
	}

	@Test
	void deleteTestFalhaNenhumaLinhaAfetada() {
		IRepositoryConta repository = new RepositoryConta(contaDAO, repositoryProduto, factoryContaCreatorProvider);

		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, null, 0.065, "cdi");

		assertThrows(RuntimeException.class, () -> repository.deleteBy(dto.numConta()));
	}
}
