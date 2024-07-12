package br.com.ifsp.vcRiquinho.conta.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaFixa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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
	private static EntityManagerFactory emf;

	private int ID_EXISTS = 1;
	private int ID_NOT_EXISTS = 1000;

	/**
	 * O metodo setUp utiliza a dependencia TestContainer Para criar uma conexão com
	 * o banco de dados do container criado no momento de rodar os testes
	 */
	@BeforeAll
	public static void setUp() {
		emf = getEntityManagerFactory();
	}

	private static EntityManagerFactory getEntityManagerFactory() {
		return PostgresTestContainer.getEntityManagerFactoryInContainer();
	}

	@Test
	void findByIdExistenteEntaoSemLancamentoDeExcecao() {
		IRepositoryConta repository = new RepositoryConta(emf);

		assertDoesNotThrow(() -> repository.findBy(ID_EXISTS));
	}

	@Test
	void findByIdNaoExistenteEntaoLancaExcecao() {
		IRepositoryConta repository = new RepositoryConta(emf);

		assertThrows(RuntimeException.class, () -> repository.findBy(ID_NOT_EXISTS));
	}

	@Test
	void findAllTestSemErros() {
		IRepositoryConta repository = new RepositoryConta(emf);

		assertDoesNotThrow(() -> repository.findAll());
	}

	@Test
	void insertTestCriacaoBemSucedida() {
		IRepositoryConta repository = new RepositoryConta(emf);
		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, null, 0.065, "corrente");

		Conta conta = repository.insert(dto);

		assertEquals("00111222000144", conta.getDocumentoTitular());
		assertNotEquals(dto.numConta(), conta.getNumConta());
	}

	@Test
	void insertTestFalhaNaCriacaoTipoDeContaJaExisteParaDocumento() {
		IRepositoryConta repository = new RepositoryConta(emf);

		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, null, 0.065, "cdi");

		repository.insert(dto);
		assertThrows(RuntimeException.class, () -> repository.insert(dto));
	}

	@Test
	void updateTestSucessoNaAtualizacao() {
		IRepositoryConta repository = new RepositoryConta(emf);

		DTOConta dto = new DTOConta(1, "00111222000144", 0.0, 5, 0.065, "investimento_automatico");

		Conta conta = repository.update(dto);

		assertEquals(dto.documentoTitular(), conta.getDocumentoTitular());
	}

	@Test
	void updateTestFalhaNumContaNaoExisteNenhumaLinhaAfetada() {
		IRepositoryConta repository = new RepositoryConta(emf);

		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, null, 0.065, "cdi");

		assertThrows(RuntimeException.class, () -> repository.update(dto));
	}

	@Test
	void deleteTestContaDeletadaComSucesso() {
		IRepositoryConta repository = new RepositoryConta(emf);

		DTOConta dto = new DTOConta(1, "00111222000144", 0.0, null, 0.065, "investimento_automatico");

		assertDoesNotThrow(() -> repository.deleteBy(dto.numConta()));
	}

	@Test
	void deleteTestFalhaNenhumaLinhaAfetada() {
		IRepositoryConta repository = new RepositoryConta(emf);

		DTOConta dto = new DTOConta(0, "00111222000144", 0.0, null, 0.065, "cdi");

		assertThrows(RuntimeException.class, () -> repository.deleteBy(dto.numConta()));
	}

	@Test
	void testeIdea() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("vcriquinho-postgres");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(new ContaInvestimentoAutomatico(0, "499.306.608-28", 2000.0, new ProdutoRendaFixa(1, 200.0, 50)));
		em.getTransaction().commit();
		assertEquals(true, true);
	}
}
