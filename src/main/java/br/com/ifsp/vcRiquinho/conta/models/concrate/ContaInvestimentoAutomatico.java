package br.com.ifsp.vcRiquinho.conta.models.concrate;

import java.util.Objects;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public class ContaInvestimentoAutomatico extends Conta {
	private Produto produto;

	public ContaInvestimentoAutomatico(Integer numConta, String documentoTitular,Double montanteFinanceiro, Produto produto) {
		super(numConta, documentoTitular, montanteFinanceiro);
		this.produto = Objects.requireNonNull(produto, "Produto não pode ser nulo");
	}

	@Override
	public Double renderPorDias(int dias) {
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
		return "invesimento_automatico";
	}

}
