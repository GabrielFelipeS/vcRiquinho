package br.com.ifsp.vcRiquinho.pessoa.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.conta.dao.ContaDAO;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.conta.repository.RepositoryConta;
import br.com.ifsp.vcRiquinho.pessoa.dao.PessoaDAO;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoaConta;
import br.com.ifsp.vcRiquinho.pessoa.factory.concrate.FactoryPessoaCreatorProvider;
import br.com.ifsp.vcRiquinho.pessoa.repository.RepositoryPessoa;
import br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.repository.RepositoryProduto;
import br.com.ifsp.vcRiquinho.usuario.dao.UserDAO;
import br.com.ifsp.vcRiquinho.usuario.dto.DTOUser;

public class CadastroPessoaService {
	 private static final Logger logger = LoggerFactory.getLogger(CadastroPessoaService.class);
	
	public void cadastrar(HttpServletRequest request) throws Exception {
		Integer idProduto = getIdProdutoOrNull(request.getParameter("idProduto"));
		Double cdi = getCDIOrZero(request.getParameter("cdiAtual"));
		
		DTOUser userDTO = new DTOUser(request.getParameter("email"), request.getParameter("email"));
		DTOPessoaConta dto = generateBy(request, idProduto, cdi);

		IDBConnector connector = new ConnectionPostgress();

		try (Connection conn = connector.getConnection()){
			conn.setAutoCommit(false);
			RepositoryPessoa repository = generateRepositoryPessoa(conn);
			repository.insert(dto);
			
			UserDAO userDAO = new UserDAO(conn);
			userDAO.insert(userDTO);
			
			connector.commit();
		} catch (RuntimeException | SQLException e) {
			connector.rollback();
			System.out.println(e.getMessage());
			logger.error("Erro ao inserir pessoa", e);
			throw e;
		} finally {
			connector.closeConnection();
		}
	}

	private RepositoryPessoa generateRepositoryPessoa(Connection conn) {
		RepositoryProduto repositoryProduto = new RepositoryProduto(new ProdutoDAO(conn), new FactoryProdutoCreator());
		RepositoryConta repositoryConta = new RepositoryConta(new ContaDAO(conn), repositoryProduto,
				new FactoryContaCreatorProvider());

		return new RepositoryPessoa(new PessoaDAO(conn), new FactoryPessoaCreatorProvider(), repositoryConta);
	}

	private DTOPessoaConta generateBy(HttpServletRequest request, Integer idProduto, Double cdi) {
		DTOPessoa dtoPessoa = new DTOPessoa(0, request.getParameter("documento_titular"), request.getParameter("nome"),
				request.getParameter("email"), request.getParameter("tipo_pessoa"));
		DTOConta dtoConta = new DTOConta(0, request.getParameter("documento_titular"), 0.0, idProduto, cdi,
				request.getParameter("tipo_conta"));

		return new DTOPessoaConta(dtoPessoa, Set.of(dtoConta));
	}

	private Integer getIdProdutoOrNull(String parameter) {
		if (parameter != null) {
			return Integer.valueOf(parameter);
		}

		return null;
	}

	private Double getCDIOrZero(String parameter) {
		if (parameter != null) {
			return Double.valueOf(parameter);
		}

		return 0.0;
	}
}
