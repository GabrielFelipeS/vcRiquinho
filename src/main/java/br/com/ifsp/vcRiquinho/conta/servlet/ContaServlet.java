package br.com.ifsp.vcRiquinho.conta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.conta.dao.ContaDAO;
import br.com.ifsp.vcRiquinho.conta.dao.IContaDAO;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.repository.IRepositoryConta;
import br.com.ifsp.vcRiquinho.conta.repository.RepositoryConta;
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
		
		IRepositoryProduto repositoryProduto = new RepositoryProduto(new ProdutoDAO(connection),new FactoryProdutoCreator());
		System.out.println("TESTE");
		IFactoryContaCreatorProvider factoryContaCreatorProvider = new FactoryContaCreatorProvider();
		
		IRepositoryConta repository = new RepositoryConta(contaDAO, repositoryProduto, factoryContaCreatorProvider);
		
		
		for(Conta c : repository.findAll()) {
			System.out.println(c.getNumConta());
		}
		
		
		  PrintWriter out = response.getWriter();

	        // Escreva algum conteúdo
	        out.println("<html>");
	        out.println("<body>");
	        out.println("<h1>Olá, Mundo!</h1>");
	        out.println("</body>");
	        out.println("</html>");

	        // Feche o fluxo de saída
	        out.close();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private IRepositoryConta gerarRepoitory(IDBConnector iDbConnector) {
		return null;
	}

}
