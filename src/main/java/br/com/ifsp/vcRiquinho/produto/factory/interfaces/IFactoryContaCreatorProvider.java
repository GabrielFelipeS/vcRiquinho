package br.com.ifsp.vcRiquinho.produto.factory.interfaces;

import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryContaCreator;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public interface IFactoryContaCreatorProvider {
	  IFactoryContaCreator create(Produto produto);
}
