package br.com.ifsp.vcRiquinho.conta.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.conta.dao.ContaDAO;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.conta.repository.RepositoryConta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.repository.RepositoryProduto;

/**
 * Servlet implementation class ContaViewServlet
 */
@WebServlet("/ContaView")
public class ContaViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Connection conn = new ConnectionPostgress().getConnection();
		RepositoryProduto repositoryProduto = new RepositoryProduto(new ProdutoDAO(conn), new FactoryProdutoCreator());
		RepositoryConta repository = new RepositoryConta(new ContaDAO(conn), repositoryProduto,
				new FactoryContaCreatorProvider());
		Pessoa pessoa = (Pessoa) session.getAttribute("conta");
		List<String> list = repository.findMissingTypeAccounts(pessoa.getDocumentoTitular());
		
		if(list.size() == 0) {
			session.setAttribute("erroProfile", "Você não tem mais opções de contas a adicionar");
			response.sendRedirect("profile");
		}
	}
}
