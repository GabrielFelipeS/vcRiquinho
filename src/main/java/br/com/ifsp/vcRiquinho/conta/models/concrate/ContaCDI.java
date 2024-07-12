package br.com.ifsp.vcRiquinho.conta.models.concrate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("cdi")
public class ContaCDI extends Conta {
	private Double cdi;

	public ContaCDI() {

	}
	public ContaCDI(String documentoTitular, Double montanteFinanceiro, Double cdi) {
		super(documentoTitular, montanteFinanceiro);
		this.cdi = Objects.requireNonNull(cdi, "O cdi não pode ser vazio");
	}
	public ContaCDI(Integer numConta, String documentoTitular, Double montanteFinanceiro, Double cdi) {
		super(numConta, documentoTitular, montanteFinanceiro);
		this.cdi = Objects.requireNonNull(cdi, "O cdi não pode ser vazio");
	}

	public Double getCdi() {
		return cdi;
	}
	
	@Override
	public Double rendimentoPorDias(int dias) {
		//Tem algo errado nessa formula
		//return (Math.pow(getMontanteFinanceiro() * (1.0 + cdi + (1.0/3.0)), dias/252.0));
		
		// Nessa daqui também, mas vamos relevar
		return getMontanteFinanceiro() * ( dias * (cdi/30.0));
	}

	@Override
	public String tipoConta() {
		return "cdi";
	}

	public Map<String, String> getDetalhes() {
		HashMap<String, String> map = new HashMap<>();
		map.put("tipoConta", this.tipoConta());
		map.put("nomeProduto", "Não aplicavel");
		map.put("carencia", "0");
		return map;
	}

}
