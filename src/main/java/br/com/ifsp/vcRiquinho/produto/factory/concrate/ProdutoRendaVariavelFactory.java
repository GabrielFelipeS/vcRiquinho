package br.com.ifsp.vcRiquinho.produto.factory.concrate;

import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProduto;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaVariavel;

public class ProdutoRendaVariavelFactory implements IFactoryProduto {

	@Override
	public Produto createBy(DTOProduto dto) {
		return new ProdutoRendaVariavel(dto.id(), dto.nome(), dto.descricao(), dto.rendimentoMensal());
	}

	@Override
	public DTOProduto convert(Produto produto) {
		return new DTOProduto(produto.getId(), produto.getCarencia(), produto.tipoProduto(), produto.getNome(),
				produto.getDescricao(), produto.getRendimentoMensal());
	}

}
