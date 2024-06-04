package br.com.ifsp.vcRiquinho.produto.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProdutoViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object isAdmin = session.getAttribute("isAdmin");
		if (isAdmin != null && (Boolean) isAdmin) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/ProdutoPanelControll.jsp");
			dispatcher.forward(request, response);
		} else {
			session.setAttribute("semPermissao", "Você não tem acesso a esse conteúdo");
			response.sendRedirect("home");
		}

		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
