package br.com.ifsp.vcRiquinho.conta.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.conta.dao.ContaDAO;
import br.com.ifsp.vcRiquinho.conta.dao.IContaDAO;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.repository.IRepositoryConta;
import br.com.ifsp.vcRiquinho.conta.repository.RepositoryConta;
import br.com.ifsp.vcRiquinho.pessoa.factory.concrate.PessoaRepositoryFactory;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.produto.repository.IRepositoryProduto;
import br.com.ifsp.vcRiquinho.produto.repository.RepositoryProduto;

public class ContaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		IDBConnector iDbConnector = new ConnectionPostgress();
		Connection connection = iDbConnector.getConnection();
		IContaDAO contaDAO = new ContaDAO(connection);

		IRepositoryProduto repositoryProduto = new RepositoryProduto(new ProdutoDAO(connection),
				new FactoryProdutoCreator());
		IFactoryContaCreatorProvider factoryContaCreatorProvider = new FactoryContaCreatorProvider();

		//IRepositoryConta repository = new RepositoryConta(contaDAO, repositoryProduto, factoryContaCreatorProvider);
		IRepositoryConta repository =null;
		for (Conta c : repository.findAll()) {
			System.out.println(c.getNumConta());
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer id = Integer.valueOf(request.getParameter("contaId"));

		IDBConnector iDbConnector = new ConnectionPostgress();

		try {
			Connection connection = iDbConnector.getConnection();
			IContaDAO contaDAO = new ContaDAO(connection);
			IRepositoryProduto repositoryProduto = new RepositoryProduto(new ProdutoDAO(connection),
					new FactoryProdutoCreator());
			IFactoryContaCreatorProvider factoryContaCreatorProvider = new FactoryContaCreatorProvider();

			//IRepositoryConta repository = new RepositoryConta(contaDAO, repositoryProduto, factoryContaCreatorProvider);
			IRepositoryConta repository =null;
			
			Connection conn = new ConnectionPostgress().getConnection();
			String documentoTitular = ((Pessoa) session.getAttribute("conta")).getDocumentoTitular() ;
			Pessoa pessoa = new PessoaRepositoryFactory().createBy(conn).findBy(documentoTitular);

			session.setAttribute("logado", true);
			session.setAttribute("conta", pessoa);
		} catch (RuntimeException e) {
			session.setAttribute("erroProfile", e.getMessage());
			response.sendRedirect("profile");
		}
	}

}
