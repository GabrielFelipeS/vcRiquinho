package br.com.ifsp.vcRiquinho.conta.models.entities.concrate;

import br.com.ifsp.vcRiquinho.conta.models.entities.abstracts.Conta;

public class ContaCDI extends Conta {
	private Double cdi;

	public ContaCDI(Double montanteFinanceiro, Double cdi) {
		super(montanteFinanceiro);
		this.cdi = cdi;
	}

	@Override
	public Double renderPorDias(int dias) {
		// TODO refazer calculo
		return null;

		// return (1/30 * cdi) * getMontanteFinanceiro();
	}

}
