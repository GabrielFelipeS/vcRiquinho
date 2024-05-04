package br.com.ifsp.vcRiquinho.conta.models.entities.concrate;

import br.com.ifsp.vcRiquinho.conta.models.entities.abstracts.Conta;
import br.com.ifsp.vcRiquinho.produto.model.abstracts.Produto;

public class ContaInvestimentoAutomatico extends Conta {
	private Produto produto;

	public ContaInvestimentoAutomatico(Double montanteFinanceiro, Produto produto) {
		super(montanteFinanceiro);
		this.produto = produto;
	}

	@Override
	public Double renderPorDias(int dias) {
		// TODO refazer calculo

		return null;
	}

}
