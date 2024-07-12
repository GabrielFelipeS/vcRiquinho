package br.com.ifsp.vcRiquinho.conta.models.concrate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("investimento_automatico")
public class ContaInvestimentoAutomatico extends Conta {

	@OneToOne(cascade = CascadeType.ALL)
	private Produto produto;

	public ContaInvestimentoAutomatico() {
	}

	public ContaInvestimentoAutomatico(Integer numConta, String documentoTitular,Double montanteFinanceiro, Produto produto) {
		super(numConta, documentoTitular, montanteFinanceiro);
		this.produto = Objects.requireNonNull(produto, "Produto não pode ser nulo");
	}

	@Override
	public Double rendimentoPorDias(int dias) {
		return Math.pow(1.0 + produto.getRendimentoMensal()/100.0, dias) * getMontanteFinanceiro(); 
	}
	
	public Integer getProdutoId() {
		return produto.getId();
	}
	
	public Produto getProduto() {
		return produto;
	}

	@Override
	public String tipoConta() {
		return "investimento_automatico";
	}	@Override
	
	
	public Map<String, String> getDetalhes() {
		HashMap<String, String> map = new HashMap<>();
		map.put("tipoConta", this.tipoConta().replace("_", " "));
		map.put("nomeProduto", produto.getNome() + " - " + produto.tipoProduto().replace("_", " "));
		map.put("carencia", String.valueOf(produto.getCarencia()));
		return map;
	}

}
