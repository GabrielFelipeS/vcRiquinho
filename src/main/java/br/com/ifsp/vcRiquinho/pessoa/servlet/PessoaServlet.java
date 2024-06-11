package br.com.ifsp.vcRiquinho.pessoa.servlet;

import java.io.IOException;

import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoaConta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;

import br.com.ifsp.vcRiquinho.pessoa.service.PessoaService;

/**
 * Servlet implementation class PessoaServlet
 */
public class PessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PessoaService service = new PessoaService();
		try {
			service.cadastrar(request);

			response.sendRedirect("login");
		} catch (Exception e) {
			request.getSession().setAttribute("mensagemErro", "Email ou Documento titular já cadastrados");
			response.sendRedirect("register"); 
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		if(session.getAttribute("logado") == null) {
			session.setAttribute("semPermissao", "Você precisa estar logado para acessar esse conteúdo");
			response.sendRedirect("home");
		} else { 	

			Pessoa pessoa = (Pessoa) Objects.requireNonNull(session.getAttribute("conta"), "Atributo não deve ser nulo");
			
			PessoaService service = new PessoaService();
			
			try {
				service.deletar(pessoa.getDocumentoTitular());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			} 
			System.out.println("DELETADO");

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
