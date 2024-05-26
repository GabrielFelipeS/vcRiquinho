package br.com.ifsp.vcRiquinho.pessoa.factory.concrate;

import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreator;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryContaCreator;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoaCreator;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoaCreatorProvider;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public class FactoryPessoaCreatorProvider implements IFactoryPessoaCreatorProvider {

	@Override
	public IFactoryPessoaCreator create(Set<Conta> contas) {
		return new FactoryPessoaCreator(contas);
	}

	

}
