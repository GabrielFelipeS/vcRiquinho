package br.com.ifsp.vcRiquinho.conta.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.implementation.EntityManagerFactoryPostgres;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.conta.dao.ContaDAO;
import br.com.ifsp.vcRiquinho.conta.dao.IContaDAO;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.repository.IRepositoryConta;
import br.com.ifsp.vcRiquinho.conta.repository.RepositoryConta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.pessoa.repository.RepositoryPessoa;
import jakarta.persistence.EntityManagerFactory;

public class ContaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf = EntityManagerFactoryPostgres.getEntityManagerFactory();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		IDBConnector iDbConnector = new ConnectionPostgress();
		Connection connection = iDbConnector.getConnection();
		IContaDAO contaDAO = new ContaDAO(connection);


		RepositoryConta repository = new RepositoryConta(emf);

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
			RepositoryConta repository = new RepositoryConta(emf);
			repository.deleteBy(id);

			String documentoTitular = ((Pessoa) session.getAttribute("conta")).getDocumentoTitular() ;
			Pessoa pessoa = new RepositoryPessoa(emf).findBy(documentoTitular);

			session.setAttribute("logado", true);
			session.setAttribute("conta", pessoa);
		} catch (RuntimeException e) {
			session.setAttribute("erroProfile", e.getMessage());
			response.sendRedirect("profile");
		}
	}

}
