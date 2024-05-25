package br.com.ifsp.vcRiquinho.pessoa.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.db.PostgresTestContainer;
import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.conta.dao.ContaDAO;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.conta.repository.IRepositoryConta;
import br.com.ifsp.vcRiquinho.conta.repository.RepositoryConta;
import br.com.ifsp.vcRiquinho.pessoa.dao.IPessoaDAO;
import br.com.ifsp.vcRiquinho.pessoa.dao.PessoaDAO;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoaConta;
import br.com.ifsp.vcRiquinho.pessoa.factory.concrate.FactoryPessoaCreatorProvider;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoaCreatorProvider;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.repository.RepositoryProduto;

public class RepositoryPessoaTest {
	private static IDBConnector iDbConnector = new ConnectionPostgress();
	private static Connection connection;

	private IRepositoryConta repositoryConta = new RepositoryConta(new ContaDAO(connection),
			new RepositoryProduto(new ProdutoDAO(connection), new FactoryProdutoCreator()),
			new FactoryContaCreatorProvider());

	private IPessoaDAO dao = new PessoaDAO(connection);
	private IFactoryPessoaCreatorProvider factoryPessoaCreatorProvider = new FactoryPessoaCreatorProvider();

	private IRepositoryPessoa repository = new RepositoryPessoa(dao, factoryPessoaCreatorProvider, repositoryConta);

	private String DOCUMENT_EXISTS = "12345678901";
	private String DOCUMENT_NOT_EXISTS = "00000000000";

	/**
	 * O método setUp utiliza a dependencia TestContainer Para criar uma conexão com
	 * o banco de dados do container criado no momento de rodar os testes
	 * @throws SQLException 
	 */
	@BeforeAll
	public static void setUp() throws SQLException {
		connection = PostgresTestContainer.connectInNewContainer(iDbConnector);
		// iDbConnector.getConnection(ConnectionPostgress.DEFAULT_URL_DBTEST,
		// ConnectionPostgress.DEFAULT_USER_DBTEST,
		// ConnectionPostgress.DEFAULT_PASSWORD_DBTEST);
	}

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
	void findByIdExistenteEntaoSemLançamentoDeExceção() throws SQLException {
		assertDoesNotThrow(() -> repository.findBy(DOCUMENT_EXISTS));
	}

	@Test
	void findByIdNaoExistenteEntaoLancaDeExceção() {
		assertThrows(RuntimeException.class, () -> repository.findBy(DOCUMENT_NOT_EXISTS));
	}

	@Test
	void findAllTestSemErros() {
		assertDoesNotThrow(() -> repository.findAll());
	}

	@Test
	void insertTestCriacaoBemSucedida() {
		DTOPessoa dtoPessoa = new DTOPessoa(0, "11111111111", "Gabriel", "andrade.gabriel1@gmail.com", "juridica");
		DTOConta dtoConta = new DTOConta(1, "11111111111", 0.0, null, 0.065, "investimento_automatico");
		DTOPessoaConta dto = new DTOPessoaConta(dtoPessoa, Set.of(dtoConta));

		Pessoa pessoa = repository.insert(dto);

		assertEquals(dtoPessoa.nome(), pessoa.getNome());
		assertNotEquals(dtoPessoa.id(), pessoa.getId());
	}

	@Test
	void insertTestFalhaNaCriacaoDocumentoRepedito() {
		DTOPessoa dtoPessoa = new DTOPessoa(0, "11111111111", "Gabriel", "andrade.gabriel1@gmail.com", "fisica");
		DTOConta dtoConta = new DTOConta(1, "11111111111", 0.0, null, 0.065, "investimento_automatico");
		DTOPessoaConta dto = new DTOPessoaConta(dtoPessoa, Set.of(dtoConta));

		repository.insert(dto);
		assertThrows(RuntimeException.class, () -> repository.insert(dto));
	}

	@Test
	void updateTestSucessoNaAtualizacao() {
		DTOPessoa dtoPessoa = new DTOPessoa(0, DOCUMENT_EXISTS, "Gabriel", "andrade.gabriel1@gmail.com", "juridica");
		DTOConta dtoConta = new DTOConta(1, "00111222000144", 0.0, null, 0.065, "investimento_automatico");
		DTOPessoaConta dto = new DTOPessoaConta(dtoPessoa, Set.of(dtoConta));

		Pessoa pessoa = repository.update(dto);

		assertEquals(dtoPessoa.documento_titular(), pessoa.getDocumentoTitular());
		assertEquals(dtoPessoa.nome(), pessoa.getNome());

	}

	@Test
	void updateTestFalhaNumContaNaoExisteNenhumaLinhaAfetada() {
		DTOPessoa dtoPessoa = new DTOPessoa(0, DOCUMENT_NOT_EXISTS, "Gabriel Felipe", "andrade.gabriel1@gmail.com",
				"fisica");
		DTOConta dtoConta = new DTOConta(1, "00111222000144", 0.0, null, 0.065, "investimento_automatico");
		DTOPessoaConta dto = new DTOPessoaConta(dtoPessoa, Set.of(dtoConta));

		assertThrows(RuntimeException.class, () -> repository.update(dto));
	}

	@Test
	void deleteTestContaDeletadaComSucesso() {
		DTOPessoa dto = new DTOPessoa(0, DOCUMENT_EXISTS, "Gabriel Felipe", "andrade.gabriel1@gmail.com", "juridica");

		assertDoesNotThrow(() -> repository.deleteBy(dto.documento_titular()));
	}

	@Test
	void deleteTestFalhaNenhumaLinhaAfetada() {
		DTOPessoa dto = new DTOPessoa(0, DOCUMENT_NOT_EXISTS, "Gabriel Felipe", "andrade.gabriel1@gmail.com", "fisica");

		assertThrows(RuntimeException.class, () -> repository.deleteBy(dto.documento_titular()));
	}
}
