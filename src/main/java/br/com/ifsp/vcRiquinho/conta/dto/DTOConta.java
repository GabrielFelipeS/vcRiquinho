package br.com.ifsp.vcRiquinho.conta.dto;

import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaInvestimentoAutomatico;

public record DTOConta(Integer id, String documentoTitular, Double montanteFinanceiro, Integer id_produto, Double cdi,
		String tipo_conta) {

	public DTOConta(ContaCorrente conta) {
		this(conta.getNumConta(), conta.getDocumentoTitular(), conta.getMontanteFinanceiro(), null, null,
				conta.tipoConta());
	}

	public DTOConta(ContaInvestimentoAutomatico conta) {
		this(conta.getNumConta(), conta.getDocumentoTitular(), conta.getMontanteFinanceiro(), conta.getProdutoId(),
				null, conta.tipoConta());
	}

	public DTOConta(ContaCDI conta) {
		this(conta.getNumConta(), conta.getDocumentoTitular(), conta.getMontanteFinanceiro(), null, conta.getCdi(),
				conta.tipoConta());
	}
}
