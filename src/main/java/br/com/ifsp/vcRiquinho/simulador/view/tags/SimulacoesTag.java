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
		String line = "<thead>\n\r<tr> \n\r" + "    <th>Tipo conta</th>\n\r" + "    <th>Nome do produto</th>\n\r"
				+ "    <th>Montante financeiro atual</th>\n\r" + "    <th>Carência</th>\n\r"
				+ "    <th>Quantidades de dias</th>\n\r" + "    <th>Montante financeiro bruto</th>\n\r"
				+ "    <th>Taxa de servico</th>\n\r" + "    <th>Montante financeiro liquido</th>\n\r" + "  </tr>\n\r</thead>\n\r";

		printLine(line);

	}

	private void printTableLine(DTOSimulacao simulacao) {
		String line = String.format(
				"<tr>\n\r <td>%s</td>\n\r  <td>%s</td>\n\r          <td>R$ %.2f</td>\n\r <td>%s</td>\n\r <td>%d dias</td>\n\r <td>R$ %.2f</td>\n\r <td>%s</td>\n\r <td>R$ %.2f</td>\n\r</tr>\n",
				simulacao.tipoConta(), simulacao.nomeProduto(), simulacao.montanteFinanceiroAtual(), simulacao.carencia(),
				simulacao.dias(), simulacao.montanteFinanceiroBruto(), simulacao.taxaServico(), simulacao.montanteFinanceiroLiquido());

		printLine(line);
	}
}
