package br.com.ifsp.vcRiquinho.produto.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.repository.RepositoryProduto;

/**
 * Servlet implementation class ProdutoDatabaseServlet
 */
public class ProdutoDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: " + " ProdutoDatabaseServlet ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		IDBConnector connector = new ConnectionPostgress();

		try {
			RepositoryProduto repository = new RepositoryProduto(new ProdutoDAO(connector.getConnection()),
					new FactoryProdutoCreator());

			repository.insert(new DTOProduto(null, Integer.valueOf(request.getParameter("carencia")),
					request.getParameter("tipo_produto"), request.getParameter("nome"),
					request.getParameter("descricao"), Double.valueOf(request.getParameter("rendimento_mensal"))));

			response.sendRedirect("painelProduto");
		} catch (RuntimeException e) {
			session.setAttribute("erroProduto", e.getMessage());
			response.sendRedirect("painelProduto");
		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer id = Integer.valueOf(request.getParameter("idProduto"));

		IDBConnector connector = new ConnectionPostgress();
		
		try {
			RepositoryProduto repository = new RepositoryProduto(new ProdutoDAO(connector.getConnection()),
					new FactoryProdutoCreator());

			repository.deleteBy(id);
			System.out.println(id);
		} catch (RuntimeException e) {
			session.setAttribute("erroProduto", e.getMessage());
			response.sendRedirect("painelProduto");
		}
	}

}
