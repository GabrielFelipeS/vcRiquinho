package br.com.ifsp.vcRiquinho.usuario.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TEMPO_PARA_EXPIRACAO_15MIN = 15 * 60;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (password.equals("admin123")) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(TEMPO_PARA_EXPIRACAO_15MIN);

			Cookie ck = new Cookie("idSession", session.getId());
			ck.setMaxAge(TEMPO_PARA_EXPIRACAO_15MIN);

			response.addCookie(ck);
		} else {
			request.getRequestDispatcher("login.html").include(request, response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
