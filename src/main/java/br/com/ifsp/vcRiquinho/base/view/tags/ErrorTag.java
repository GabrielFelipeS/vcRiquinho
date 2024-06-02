package br.com.ifsp.vcRiquinho.base.view.tags;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ErrorTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	private String attribute;
	
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	public int doStartTag() throws JspException {  
	    JspWriter out=pageContext.getOut();//returns the instance of JspWriter  
	    
	    try{  
	    	HttpSession session = pageContext.getSession();
	    	String errorMessage = (String) session.getAttribute(attribute);
	    	session.removeAttribute(attribute);
	    	
	    	if (errorMessage != null ) {
	   	     out.print("	<div id=\"error\" class=\"error-message\">"
	 	     		+ "	    				<p>"
	 	     		+ "	    					<strong>Erro: </strong>" + errorMessage + "</p>"
	 	     		+ "	    			</div>"
	 	     		+ "	    			<script id=\"errorScript\">"
	 	     		+ "	    				setTimeout(function() {"
	 	     		+ "	    				    var messageElement = document.getElementById('error');"
	 	     		+ "                         var scriptElement = document.getElementById('errorScript');"
	 	     		+ "	    				    if (messageElement) {"
	 	     		+ "	    				      messageElement.remove();"
	 	     		+ "	    				      scriptElement.remove();"
	 	     		+ "	    				    }"
	 	     		+ "	    				  }, 10000); // 10000 milissegundos = 10 segundos"
	 	     		+ "	    			</script>"
	 	     		+ "				<style> "
	 	     		+ "	    			.error-message {"
	 	     		+ "	    				background-color: #f8d7da; "
	 	     		+ "	    				color: #721c24; "
	 	     		+ "	    				padding: 10px; "
	 	     		+ "	   					margin: 20px;"
	 	     		+ "	   					border: 1px solid #f5c6cb; "
	 	     		+ "	   					border-radius: 5px; "
	 	     		+ "	    			}"
	 	     		+ "	    		</style>");
	    		}
	    }catch(Exception e){System.out.println(e);}  
	    return SKIP_BODY;//will not evaluate the body content of the tag  
	}  
}
