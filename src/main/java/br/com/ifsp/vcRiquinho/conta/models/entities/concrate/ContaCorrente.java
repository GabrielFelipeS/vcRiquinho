package br.com.ifsp.vcRiquinho.conta.models.entities.concrate;

import br.com.ifsp.vcRiquinho.conta.models.entities.abstracts.Conta;

public class ContaCorrente extends Conta {
	private Double SEM_RENDIMENTO_PARA_CONTA_CORRENTE = 0.0;

	public ContaCorrente(Double montanteFinanceiro) {
		super(montanteFinanceiro);
	}

	@Override
	public Double renderPorDias(int dias) {
		return SEM_RENDIMENTO_PARA_CONTA_CORRENTE;
	}
}
