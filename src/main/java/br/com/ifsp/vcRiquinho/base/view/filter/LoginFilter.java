package br.com.ifsp.vcRiquinho.base.view.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		
		HttpSession session = httpRequest.getSession();
		Object obj = session.getAttribute("logado");
		
		if(obj == null || !((Boolean) obj)) {
			session.setAttribute("semPermissao", "Você precisa estar logado para acessar esse conteúdo");
			httpResponse.sendRedirect("home");
		} else {			
			chain.doFilter(request, response);
		}
	}
	
	public void destroy() {
	}


}
