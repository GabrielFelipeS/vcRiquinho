package br.com.ifsp.vcRiquinho.simulador.servlet;

import br.com.ifsp.vcRiquinho.base.db.implementation.EntityManagerFactoryPostgres;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.pessoa.repository.IRepositoryPessoa;
import br.com.ifsp.vcRiquinho.pessoa.repository.RepositoryPessoa;
import br.com.ifsp.vcRiquinho.simulador.controller.SimulacoesController;
import br.com.ifsp.vcRiquinho.simulador.dto.DTOSimulacao;
import jakarta.persistence.EntityManagerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SimulacaoGeralServlet extends HttpServlet {
	private EntityManagerFactory emf = EntityManagerFactoryPostgres.getEntityManagerFactory();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/simulacaoGeral.jsp");
		dispatcher.forward(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RepositoryPessoa repository = new RepositoryPessoa(emf);
		
		Integer days = Integer.valueOf(request.getParameter("days"));
		
		List<Pessoa> pessoas = repository.findAll();
		List<DTOSimulacao> simulacoes = new LinkedList<>();
		SimulacoesController controller = new SimulacoesController();
		for(Pessoa pessoa : pessoas) {
			List<Conta> contas = pessoa.getContasListCopy();
			List<DTOSimulacao> list = controller.simular(pessoa, contas, days);
			simulacoes.addAll(list);
		}
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("simulacoesGeral");

		if (obj != null) {
			List<DTOSimulacao> simulacoesAntigasDaSessao = (List<DTOSimulacao>) obj;
			simulacoes.addAll(simulacoesAntigasDaSessao);
		}

		session.setAttribute("simulacoesGeral", simulacoes);
		response.sendRedirect("simulacaoGeral");
	}

}
