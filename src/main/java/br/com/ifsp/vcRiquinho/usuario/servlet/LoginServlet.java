package br.com.ifsp.vcRiquinho.usuario.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaJuridica;


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
		String password = request.getParameter("password");

		if (password.equals("admin123")) {
			HttpSession session = request.getSession(true);
			session.setAttribute("logado", true);
			session.setAttribute("conta", new PessoaJuridica(1, "TESTE", "TESTE@teste.com", new ContaCDI(4, "499", 2000.0, 0.65), "499"));
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
