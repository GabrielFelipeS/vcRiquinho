package br.com.ifsp.vcRiquinho.pessoa.factory.concrate;

import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoaCreator;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoaCreatorProvider;

public class FactoryPessoaCreatorProvider implements IFactoryPessoaCreatorProvider {

	@Override
	public IFactoryPessoaCreator createBy(Set<Conta> contas) {
		return new FactoryPessoaCreator(contas);
	}

	

}
