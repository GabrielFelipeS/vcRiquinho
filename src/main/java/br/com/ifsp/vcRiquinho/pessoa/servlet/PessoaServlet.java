package br.com.ifsp.vcRiquinho.pessoa.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ifsp.vcRiquinho.pessoa.service.CadastroPessoaService;

/**
 * Servlet implementation class PessoaServlet
 */
public class PessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("documento_titular"));
		System.out.println(request.getParameter("tipo_pessoa"));
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("documentoTitular_conta"));
		System.out.println(request.getParameter("cdiAtual"));
		System.out.println(request.getParameter("idProduto"));
		System.out.println(request.getParameter("tipo_conta"));
		
		CadastroPessoaService service = new CadastroPessoaService();
		try {
			service.cadastrar(request);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("mensagemErro", "Email ou Documento titular já cadastrados");
			request.getRequestDispatcher("/cadastroPessoa").forward(request,response);
			//response.sendRedirect("FormPessoaServlet");
		}
	}

}
