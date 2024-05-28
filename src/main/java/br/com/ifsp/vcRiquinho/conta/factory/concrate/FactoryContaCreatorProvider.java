package br.com.ifsp.vcRiquinho.conta.factory.concrate;

import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryContaCreator;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public class FactoryContaCreatorProvider implements IFactoryContaCreatorProvider {

	@Override
	public IFactoryContaCreator createBy(Produto produto) {
		return new FactoryContaCreator(produto);
	}

}
