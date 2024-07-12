package br.com.ifsp.vcRiquinho.conta.models.concrate;

import java.util.HashMap;
import java.util.Map;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
@DiscriminatorValue("corrente")
public class ContaCorrente extends Conta {
	@Transient
	private Double SEM_RENDIMENTO_PARA_CONTA_CORRENTE = 0.0;

	public ContaCorrente() {

	}

	public ContaCorrente(Integer numConta, String documentoTitular,Double montanteFinanceiro) {
		super(numConta, documentoTitular, montanteFinanceiro);
	}

	public ContaCorrente(String documentoTitular,Double montanteFinanceiro) {
		super(documentoTitular, montanteFinanceiro);
	}


	@Override
	public Double rendimentoPorDias(int dias) {
		return SEM_RENDIMENTO_PARA_CONTA_CORRENTE;
	}

	@Override
	public String tipoConta() {
		return "corrente";
	}
	
	public Map<String, String> getDetalhes() {
		HashMap<String, String> map = new HashMap<>();
		map.put("tipoConta", this.tipoConta());
		map.put("nomeProduto", "Não aplicavel");
		map.put("carencia", "0");
		return map;
	}
}
