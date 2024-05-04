package br.com.ifsp.vcRiquinho.conta.models.entities.abstracts;

import java.util.Objects;

import br.com.ifsp.vcRiquinho.base.models.interfaces.Rentavel;

public abstract class Conta implements Rentavel {
	private Double montanteFinanceiro;

	public Conta(Double montanteFinanceiro) {
		this.montanteFinanceiro = montanteFinanceiro;
	}

	public Double getMontanteFinanceiro() {
		return montanteFinanceiro;
	}

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
