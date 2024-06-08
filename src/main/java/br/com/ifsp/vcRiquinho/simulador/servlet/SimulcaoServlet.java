package br.com.ifsp.vcRiquinho.simulador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.simulador.controller.SimulacoesController;
import br.com.ifsp.vcRiquinho.simulador.dto.DTOSimulacao;

@WebServlet("/simulacao")
public class SimulcaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/simulacao.jsp");
		dispatcher.forward(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Pessoa pessoa = (Pessoa) session.getAttribute("conta");
		List<Conta> contas = pessoa.getContasListCopy();

		Integer days = Integer.valueOf(request.getParameter("days"));
		SimulacoesController controller = new SimulacoesController();
		List<DTOSimulacao> list = controller.simular(pessoa, contas, days);

		Object obj = session.getAttribute("simulacoes");

		if (obj != null) {
			List<DTOSimulacao> simulacoesAntigasDaSessao = (List<DTOSimulacao>) obj;
			list.addAll(simulacoesAntigasDaSessao);
		}

		session.setAttribute("simulacoes", list);
		response.sendRedirect("simulacao");
	}

}
