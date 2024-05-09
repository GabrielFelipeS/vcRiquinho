package br.com.ifsp.vcRiquinho.conta.models.concrate;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;

public class ContaCorrente extends Conta {
	private Double SEM_RENDIMENTO_PARA_CONTA_CORRENTE = 0.0;

	public ContaCorrente(Integer numConta, String documentoTitular,Double montanteFinanceiro) {
		super(numConta, documentoTitular, montanteFinanceiro);
	}

	@Override
	public Double renderPorDias(int dias) {
		return SEM_RENDIMENTO_PARA_CONTA_CORRENTE;
	}
}
