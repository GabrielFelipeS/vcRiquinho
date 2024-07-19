package br.com.ifsp.vcRiquinho.produto.repository;

import br.com.ifsp.vcRiquinho.base.db.PostgresTestContainer;
import br.com.ifsp.vcRiquinho.produto.dao.IProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.CallableStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryProdutoTest {
	private static EntityManagerFactory emf;
	private RepositoryProduto repository = new RepositoryProduto(emf);

	private int ID_EXISTS = 1;
	private int OTHER_ID_EXISTS = 11;
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

	@AfterEach
	void afterEach() {
//		String procedure = "{ call reset_table_in_produto_and_conta() }";
//		try (CallableStatement proc = connection.prepareCall(procedure)) {
//			proc.execute();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
	}

	@Test
	void findByDocumentIdExistenteEntaoSemLancamentoDeExcecao() {
		assertDoesNotThrow(() -> repository.findBy(ID_EXISTS));
	}

	@Test
	void findByDocumentIdNaoExistenteEntaoLancaDeExcecao() {
		Produto produtoNull = repository.findBy(ID_NOT_EXISTS);

		assertNull(produtoNull);
	}

	@Test
	void findAllTestSemErros() {
		assertDoesNotThrow(() -> repository.findAll());
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

		assertThrows(RuntimeException.class, () -> {
			repository.insert(dto);
			repository.insert(dto);
		});
	}

	@Test
	void updateTestSucessoNaAtualizacao() {
		DTOProduto dto = new DTOProduto(ID_EXISTS, 0, "renda_fixa", "TESTE_NOME_ATUALIZADO", "TESTE_DESCRICAO", 0.0);

		repository.update(dto);
		Produto produto = repository.findBy(ID_EXISTS);

		assertEquals(produto.getNome(), dto.nome());
	}

	@Test
	void updateTestFalhaNumContaNaoExisteNenhumaLinhaAfetada() {
		DTOProduto dto = new DTOProduto(ID_NOT_EXISTS, 0, "renda_variavel", "TESTE_NOME", "TESTE_DESCRICAO", 0.0);

		assertDoesNotThrow(() -> repository.update(dto));
		assertNull(repository.findBy(dto.id()));
	}

	@Test
	void deleteTestContaDeletadaComSucesso() {
		DTOProduto dto = new DTOProduto(OTHER_ID_EXISTS, 0, "renda_variavel", "TESTE_NOME", "TESTE_DESCRICAO", 0.0);

		assertDoesNotThrow(() -> repository.deleteBy(dto.id()));
		assertNull(repository.findBy(OTHER_ID_EXISTS));
	}

	@Test
	void deleteTestFalhaNenhumaLinhaAfetada() {
		DTOProduto dto = new DTOProduto(ID_NOT_EXISTS, 0, "renda_variavel", "TESTE_NOME", "TESTE_DESCRICAO", 0.0);

		assertDoesNotThrow(() -> repository.deleteBy(dto.id()));
		assertNull(repository.findBy(dto.id()));
	}
}
