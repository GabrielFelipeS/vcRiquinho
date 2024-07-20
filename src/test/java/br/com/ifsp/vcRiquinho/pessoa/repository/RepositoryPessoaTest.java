package br.com.ifsp.vcRiquinho.pessoa.repository;

import br.com.ifsp.vcRiquinho.base.db.PostgresTestContainer;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.repository.IRepositoryConta;
import br.com.ifsp.vcRiquinho.conta.repository.RepositoryConta;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoaConta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.StoredProcedureQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryPessoaTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	private RepositoryConta repositoryConta = new RepositoryConta(emf);

	private RepositoryPessoa repository = new RepositoryPessoa(emf);

	private String DOCUMENT_EXISTS = "12345678901";
	private String DOCUMENT_NOT_EXISTS = "00000000000";
	private static final String EMAIL_EXISTS = "joaosilva@email.com";
	private static final String EMAIL_NOT_EXISTS = "notExists@gmail.com";

	/**
	 * O metodo setUp utiliza a dependencia TestContainer Para criar uma conexão com
	 * o banco de dados do container criado no momento de rodar os testes
	 * 
	 * @throws SQLException
	 */
	@BeforeAll
	public static void setUp() {
		emf = getEntityManagerFactory();
		em = emf.createEntityManager();
	}

	private static EntityManagerFactory getEntityManagerFactory() {
		return PostgresTestContainer.getEntityManagerFactoryInContainer();
	}

	@AfterEach
	void afterEach() throws SQLException {
		em.getTransaction().begin();
		StoredProcedureQuery storedProcedure= em.createStoredProcedureQuery("reset_table_in_pessoa");
		storedProcedure.execute();
		em.getTransaction().commit();
	}

	@Test
	void findByIdExistenteEntaoSemLancamentoDeExcecao() throws SQLException {
		assertDoesNotThrow(() -> repository.findBy(DOCUMENT_EXISTS));
	}

	@Test
	void findByEmailExists() {
		Optional<Pessoa> optional = repository.findByEmail(EMAIL_EXISTS);
		assertTrue(optional.isPresent());
	}

	@Test
	void findByEmailDoesNotExists() {
		Optional<Pessoa> optional = repository.findByEmail(EMAIL_NOT_EXISTS);
		assertTrue(optional.isEmpty());
	}

	@Test
	void findByIdNaoExistenteEntaoLancaDeExcecao() {
		assertThrows(RuntimeException.class, () -> repository.findBy(DOCUMENT_NOT_EXISTS));
	}

	@Test
	void findAllTestSemErros() {
		List<Pessoa> pessoas = repository.findAll();
		assertNotEquals(0, pessoas.size());
	}

	@Test
	void insertTestCriacaoBemSucedida() {
		DTOPessoa dtoPessoa = new DTOPessoa(0, "11111111112", "Gabriel", "andrade.gabriel1@gmail.com", "juridica");
		DTOConta dtoConta = new DTOConta(1, "11111111112", 0.0, 2, 0.065, "corrente");
		//DTOConta dtoConta2 = new DTOConta(2, "111111111112", 0.0, 2, 0.065, "investimento_automatico");
		DTOPessoaConta dto = new DTOPessoaConta(dtoPessoa, Set.of(dtoConta));

		Pessoa pessoa = repository.insert(dto);

		assertEquals(dtoPessoa.nome(), pessoa.getNome());
		assertNotEquals(dtoPessoa.id(), pessoa.getId());
		assertNotEquals(0, pessoa.getId());
		pessoa.getContasListCopy().forEach(System.out::println);
	}

	@Test
	void insertTestFalhaNaCriacaoDocumentoRepedito() {
		DTOPessoa dtoPessoa = new DTOPessoa(0, "11111111111", "Gabriel", "andrade.gabriel2@gmail.com", "fisica");
		DTOConta dtoConta = new DTOConta(1, "11111111111", 0.0, 2, 0.065, "investimento_automatico");
		DTOPessoaConta dto = new DTOPessoaConta(dtoPessoa, Set.of(dtoConta));


		assertThrows(RuntimeException.class, () ->{
			repository.insert(dto);
			repository.insert(dto);
		});
	}

	@Test
	void updateTestSucessoNaAtualizacao() throws InterruptedException {
		Pessoa pessoaAntesAtualizacao = repository.findBy(DOCUMENT_EXISTS);
		pessoaAntesAtualizacao.setNome("Gabriel");

		Pessoa pessoaDepoisAtualizacao = repository.update(pessoaAntesAtualizacao);

		assertEquals("Gabriel", pessoaDepoisAtualizacao.getNome());
		assertEquals(pessoaAntesAtualizacao.getNome(), pessoaDepoisAtualizacao.getNome());
	}

	@Test
	void deleteTestContaDeletadaComSucesso() {
		DTOPessoa dto = new DTOPessoa(1, DOCUMENT_EXISTS, "Gabriel Felipe", "andrade.gabriel1@gmail.com", "juridica");
		Pessoa pessoaAntesDeDeletar = repository.findBy(DOCUMENT_EXISTS);

		repository.deleteBy(dto.documento_titular());

		Pessoa pessoaDepoisDeDeletar = repository.findBy(pessoaAntesDeDeletar.getId());

		assertNotNull(pessoaAntesDeDeletar);
		assertNull(pessoaDepoisDeDeletar);
	}
}
