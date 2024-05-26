package br.com.ifsp.vcRiquinho.produto.factory.concrate;

import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProduto;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import br.com.ifsp.vcRiquinho.produto.models.concrete.NullObjectProduto;

public class NullObjectProtudoFactory implements IFactoryProduto {

	@Override
	public Produto createBy(DTOProduto obj) {
		return new NullObjectProduto();
	}

	@Override
	public DTOProduto convert(Produto obj) {
		return new DTOProduto(obj.getId(), obj.getCarencia(), obj.tipoProduto() ,obj.getNome(), obj.getDescricao(), obj.getRendimentoMensal());
	}

	@Override
	public String toString() {
		return "NullObjectProtudoFactory";
	}

}
