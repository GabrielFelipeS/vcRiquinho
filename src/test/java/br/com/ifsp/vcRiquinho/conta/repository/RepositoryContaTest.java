package br.com.ifsp.vcRiquinho.conta.repository;

import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaFixa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.db.PostgresTestContainer;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryContaTest {
	private static EntityManagerFactory emf;

	private final int ID_EXISTS = 1;
	private final int OTHER_ID_EXISTS = 2;
	private final int ID_NOT_EXISTS = 1000;

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

		Conta contaNull = repository.findBy(ID_NOT_EXISTS);
		assertNull(contaNull);
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

		DTOConta dto = new DTOConta(OTHER_ID_EXISTS, "00111222000144", 0.0, 5, 0.065, "investimento_automatico");

		Conta conta = repository.update(dto);

		assertEquals(dto.documentoTitular(), conta.getDocumentoTitular());
	}

	@Test
	void updateTestFalhaNumContaNaoExisteNenhumaLinhaAfetada() {
		IRepositoryConta repository = new RepositoryConta(emf);

		DTOConta dto = new DTOConta(ID_NOT_EXISTS, "00111222000144", 0.0, null, 0.065, "cdi");

		assertThrows(RuntimeException.class, () -> repository.update(dto));
	}

	@Test
	void deleteTestContaDeletadaComSucesso() {
		IRepositoryConta repository = new RepositoryConta(emf);

		DTOConta dto = new DTOConta(ID_EXISTS, "00111222000144", 0.0, null, 0.065, "investimento_automatico");

		assertNotNull(repository.findBy(ID_EXISTS));
		repository.deleteBy(dto.numConta());
		assertNull(repository.findBy(ID_EXISTS));
	}



	//@Test
	void testeIdea() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(new ContaInvestimentoAutomatico(0, "499.306.608-28", 2000.0, new ProdutoRendaFixa(1, 200.0, 50)));
		em.getTransaction().commit();
		assertEquals(true, true);
	}
}
