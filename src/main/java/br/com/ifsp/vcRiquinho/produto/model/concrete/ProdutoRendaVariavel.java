package br.com.ifsp.vcRiquinho.produto.model.concrete;

import br.com.ifsp.vcRiquinho.produto.model.abstracts.Produto;

public class ProdutoRendaVariavel extends Produto {
	private Double rendimentoMensalEsperado;

	
	public ProdutoRendaVariavel(Double rendimentoMensalEsperado) {
		this("vcInveste", 
				"Permite clientes investir ações de empresas globais, fundos de índice, "
				+ "commodities e moedas digitais, os investidores podem diversificar suas carteiras de forma automática e eficaz"
			,rendimentoMensalEsperado);
	}
	
	public ProdutoRendaVariavel(String nome, String descricao, Double rendimentoMensalEsperado) {
		super(nome, descricao);
		this.rendimentoMensalEsperado = rendimentoMensalEsperado;
	}
	

	@Override
	public Double getRendimentoMensal() {
		return rendimentoMensalEsperado;
	}

	@Override
	public Integer getCarencia() {
		return 0;
	}

	@Override
	public Boolean isInGracePeriodIn(int days) {
		return false;
	}

}
