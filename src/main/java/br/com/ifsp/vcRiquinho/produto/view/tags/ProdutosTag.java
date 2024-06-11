package br.com.ifsp.vcRiquinho.produto.view.tags;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import javax.servlet.jsp.tagext.TagSupport;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import br.com.ifsp.vcRiquinho.produto.repository.IRepositoryProduto;
import br.com.ifsp.vcRiquinho.produto.repository.RepositoryProduto;

public class ProdutosTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private JspWriter out;

	public int doStartTag() throws JspException {

		Locale locale = new java.util.Locale("pt", "BR");
		pageContext.setAttribute("locale", locale, PageContext.SESSION_SCOPE);
		

		out = pageContext.getOut();
		IDBConnector dbConnector = new ConnectionPostgress();
		Connection conn = dbConnector.getConnection();

		IRepositoryProduto repository = new RepositoryProduto(new ProdutoDAO(conn), new FactoryProdutoCreator());
		List<Produto> produtos = repository.findAll();

		printLine("<table  class=\"table table-bordered\">");

		printTableHeader();
		for (Produto produto : produtos) {
			printTableLine(produto);
		}

		printLine("</table>");

		return SKIP_BODY;// will not evaluate the body content of the tag
	}

	private void printLine(String line) {
		try {
			out.print(line);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private void printTableHeader() {
		String line = "<tr>" + "    <th>Nome</th>" + "    <th>Descrição</th>" + "    <th>Rendimento Mensal</th>"
				+ "    <th>Carência</th>" + "  </tr>";

		printLine(line);

	}

	private void printTableLine(Produto produto) {
		String line = String.format(
				"<tr> <td>%s</td>  <td>%s</td>          <td>R$ %.2f</td>                <td>%d dias</td> </tr>",
				produto.getNome(), produto.getDescricao(), produto.getRendimentoMensal(), produto.getCarencia());
		
		printLine(line);
	}

}