package br.com.ifsp.vcRiquinho.simulador.view.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import br.com.ifsp.vcRiquinho.simulador.dto.DTOSimulacao;

public class SimulacoesTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private JspWriter out;

	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		out = pageContext.getOut();

		HttpSession session = pageContext.getSession();

		Object obj = session.getAttribute("simulacoes");
		
		
		if (obj != null) {
			List<DTOSimulacao> simulacoes = (List<DTOSimulacao>) obj;
			
			printLine("<table  class=\"table table-bordered\">");

			printTableHeader();
			for (DTOSimulacao simulacao : simulacoes) {
				printTableLine(simulacao);
			}

			printLine("</table>");
		}
		
		return SKIP_BODY;// will not evaluate the body content of the tag
	}

	private void printLine(String line) {
		try {
			out.print(line);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	// Tipo conta - nome produto - montante financeiro atual - carencia -dias -
	// pontante financeiro bruto - taxa de servico - montante financeiro liquido
	private void printTableHeader() {
		String line = "<thead><tr> " + "    <th>Tipo conta</th>" + "    <th>Nome do produto</th>"
				+ "    <th>Montante atual</th>" + "    <th>Carência</th>"
				+ "    <th>Quantidades de dias</th>" + "    <th>Montante bruto</th>"
				+ "    <th>Taxa de servico</th>" + "    <th>Montante liquido</th>" + "  </tr></thead>";

		printLine(line);

	}

	private void printTableLine(DTOSimulacao simulacao) {
		String line = String.format(
				"<tr> <td>%s</td>  <td>%s</td> <td>R$ %.2f</td> <td>%s</td> <td>%d dias</td> <td>R$ %.2f</td> <td>%s</td> <td>R$ %.2f</td></tr>",
				simulacao.tipoConta(), simulacao.nomeProduto(), simulacao.montanteFinanceiroAtual(), simulacao.carencia(),
				simulacao.dias(), simulacao.montanteFinanceiroBruto(), simulacao.taxaServico(), simulacao.montanteFinanceiroLiquido());

		printLine(line);
	}
}
