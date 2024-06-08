package br.com.ifsp.vcRiquinho.base.view.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter extends HttpFilter implements Filter {
       
 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = request.
		//Object obj = session.getAttribute("logado");
		
//		if(obj == null || !((Boolean) obj)) {
//			session.setAttribute("semPermissao", "Você precisa estar logado para acessar esse conteúdo");
//			response.sendRedirect("home");
//		}
		
		
		
		chain.doFilter(request, response);
	}
	
	public void destroy() {
	}


}
