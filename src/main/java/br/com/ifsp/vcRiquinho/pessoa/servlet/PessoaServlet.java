package br.com.ifsp.vcRiquinho.pessoa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoaConta;
import br.com.ifsp.vcRiquinho.pessoa.service.PessoaService;

/**
 * Servlet implementation class PessoaServlet
 */
public class PessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PessoaService service = new PessoaService();
		try {
			service.cadastrar(request);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute("mensagemErro", "Email ou Documento titular já cadastrados");
			request.getRequestDispatcher("/cadastroPessoa").forward(request, response);
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PessoaService service = new PessoaService();

		try {
			service.deletar(request.getParameter("id"));
		} catch (Exception e) {
			//request.getSession().setAttribute("mensagemErro", "Email ou Documento titular já cadastrados");
			//request.getRequestDispatcher("/cadastroPessoa").forward(request, response);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PessoaService service = new PessoaService();
		try {
			DTOPessoaConta dto = null;
			service.update(dto);
		} catch (Exception e) {
			//request.getSession().setAttribute("mensagemErro", "Email ou Documento titular já cadastrados");
			//request.getRequestDispatcher("/cadastroPessoa").forward(request, response);
			throw new RuntimeException(e.getMessage());
		}
		
	}

}
