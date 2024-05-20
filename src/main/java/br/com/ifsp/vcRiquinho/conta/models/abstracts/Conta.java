package br.com.ifsp.vcRiquinho.conta.models.abstracts;

import java.util.Objects;

import br.com.ifsp.vcRiquinho.base.interfaces.Rentavel;

public abstract class Conta implements Rentavel {
	private Integer numConta;
	private String documentoTitular;
	private Double montanteFinanceiro;

	public Conta(Integer numConta, String documentoTitular, Double montanteFinanceiro) {
		this.numConta = Objects.requireNonNull(numConta, "O número de identificação da conta não pode ser nulo");
		this.documentoTitular = Objects.requireNonNull(documentoTitular, "O documento do titular não pode ser nulo");
		this.montanteFinanceiro = Objects.requireNonNull(montanteFinanceiro, "O montante financeiro da conta não pode ser nulo");
	}

	public Integer getNumConta() {
		return numConta;
	}
	
	public String getDocumentoTitular() {
		return documentoTitular;
	}
	
	
	public Double getMontanteFinanceiro() {
		return montanteFinanceiro;
	}

	public abstract String tipoConta();
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass()) {
			return false;
		} else {

			return true;
		}
	}

}
