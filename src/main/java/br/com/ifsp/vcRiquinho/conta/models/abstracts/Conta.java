package br.com.ifsp.vcRiquinho.conta.models.abstracts;

import java.util.Map;
import java.util.Objects;

import br.com.ifsp.vcRiquinho.base.interfaces.Rentavel;
import jakarta.persistence.*;

@Entity
@Inheritance
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING)
public abstract class Conta implements Rentavel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer numConta;

	@Column(name = "documento_titular")
	private String documentoTitular;

	@Column(name = "montante_financeiro")
	private Double montanteFinanceiro;

	public Conta() {
	}

	public Conta(Integer numConta, String documentoTitular, Double montanteFinanceiro) {
		this.numConta = Objects.requireNonNull(numConta, "O número de identificação da conta não pode ser nulo");
		this.documentoTitular = Objects.requireNonNull(documentoTitular, "O documento do titular não pode ser nulo");
		this.montanteFinanceiro = Objects.requireNonNull(montanteFinanceiro, "O montante financeiro da conta não pode ser nulo");
	}

	public Conta(String documentoTitular, Double montanteFinanceiro) {
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
	
	public abstract Map<String, String> getDetalhes();
	
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
