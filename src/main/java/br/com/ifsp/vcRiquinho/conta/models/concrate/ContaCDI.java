package br.com.ifsp.vcRiquinho.conta.models.concrate;

import java.util.Objects;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;

public class ContaCDI extends Conta {
	private Double cdi;

	public ContaCDI(Integer numConta, String documentoTitular, Double montanteFinanceiro, Double cdi) {
		super(numConta, documentoTitular, montanteFinanceiro);
		this.cdi = Objects.requireNonNull(cdi, "O cdi não pode ser vazio");
	}

	public Double getCdi() {
		return cdi;
	}
	
	@Override
	public Double renderPorDias(int dias) {
		return Math.pow(getMontanteFinanceiro() * (1.0 + (cdi/30.0)), dias);
	}

	@Override
	public String tipoConta() {
		return "cdi";
	}

}
