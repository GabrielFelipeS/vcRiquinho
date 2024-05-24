package br.com.ifsp.vcRiquinho.produto.models.concrete;

import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public class NullObjectProduto extends Produto{

	public NullObjectProduto() {
		super(0, "", "");
	}

	@Override
	public Double getRendimentoMensal() {
		return 0.0;
	}

	@Override
	public Integer getCarencia() {
		return 0;
	}

	@Override
	public Boolean isInGracePeriodIn(int days) {
		return false;
	}

	@Override
	public String tipoProduto() {
		return "null_object";
	}

	
	

}
