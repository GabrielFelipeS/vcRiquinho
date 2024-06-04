package br.com.ifsp.vcRiquinho.usuario.servlet;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.pessoa.factory.concrate.PessoaRepositoryFactory;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.usuario.dao.UserDAO;
import br.com.ifsp.vcRiquinho.usuario.service.HashPassword;

// Mapping /login
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TEMPO_PARA_EXPIRACAO_15MIN = 15 * 60;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object isLoggedin = request.getSession().getAttribute("logado");
		
		if(isLoggedin != null && ((Boolean) isLoggedin)) {

			 response.sendRedirect("home");

		} else {
			getServletContext().getRequestDispatcher("/views/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");

		String hashedPassword = null;

		try {
			hashedPassword = HashPassword.generate(request.getParameter("password"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		Connection conn = new ConnectionPostgress().getConnection();
		UserDAO dao = new UserDAO(conn); 
		
		if (dao.loginValid(email, hashedPassword)) {
			Pessoa pessoa = new PessoaRepositoryFactory().createBy(conn).findByEmail(email);
			
			HttpSession session = request.getSession(true);

			session.setAttribute("logado", true);
			session.setAttribute("conta", pessoa);
			session.setAttribute("isAdmin", dao.isAdmin(email));
			session.setMaxInactiveInterval(TEMPO_PARA_EXPIRACAO_15MIN);
			
			Cookie ck = new Cookie("idSession", session.getId());
			ck.setMaxAge(TEMPO_PARA_EXPIRACAO_15MIN);
			
			response.addCookie(ck);
			response.sendRedirect("home");
		} else {
			request.getSession().setAttribute("erroLogin", "Email ou senha incorretos");
			response.sendRedirect("login");
		}
	}

}
