package br.com.ifsp.vcRiquinho.simulador.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.factory.concrate.PessoaRepositoryFactory;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IPessoaRepositoryFactory;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.pessoa.repository.IRepositoryPessoa;
import br.com.ifsp.vcRiquinho.simulador.controller.SimulacoesController;
import br.com.ifsp.vcRiquinho.simulador.dto.DTOSimulacao;

public class SimulacaoGeralServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/simulacaoGeral.jsp");
		dispatcher.forward(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IDBConnector connector = new ConnectionPostgress();
		Connection conn = connector.getConnection();
		
		IPessoaRepositoryFactory factoryRepository = new PessoaRepositoryFactory();
		IRepositoryPessoa repository = factoryRepository.createBy(conn);
		
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
