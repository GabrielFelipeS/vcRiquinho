package br.com.ifsp.vcRiquinho.base.view.tags;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ForceLogout extends TagSupport{

	
	public int doStartTag() throws JspException {  
		HttpSession session = pageContext.getSession();
		session.removeAttribute("logado"); 
		session.removeAttribute("conta");
		session.removeAttribute("simulacoes");
		session.invalidate();
		
		ServletResponse response = pageContext.getResponse();
		
	    return SKIP_BODY;//will not evaluate the body content of the tag  
	}  
}
