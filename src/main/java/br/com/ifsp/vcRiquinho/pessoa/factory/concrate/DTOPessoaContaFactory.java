package br.com.ifsp.vcRiquinho.pessoa.factory.concrate;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoaConta;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IDTOPessoaContaFactory;

public class DTOPessoaContaFactory implements IDTOPessoaContaFactory {

	@Override
	public DTOPessoaConta createBy(HttpServletRequest request) {
		Integer idProduto = getIdProdutoOrNull(request.getParameter("idProduto"));
		Double cdi = getCDIOrZero(request.getParameter("cdiAtual"));

		DTOPessoa dtoPessoa = new DTOPessoa(0, request.getParameter("documento_titular"), request.getParameter("nome"),
				request.getParameter("email"), request.getParameter("tipo_pessoa"));
		DTOConta dtoConta = new DTOConta(0, request.getParameter("documento_titular"), 0.0, idProduto, cdi,
				request.getParameter("tipo_conta"));

		return new DTOPessoaConta(dtoPessoa, Set.of(dtoConta));
	}

	private Integer getIdProdutoOrNull(String parameter) {
		if (parameter != null) {
			return Integer.valueOf(parameter);
		}

		return null;
	}

	private Double getCDIOrZero(String parameter) {
		if (parameter != null) {
			return Double.valueOf(parameter);
		}

		return 0.0;
	}

}
