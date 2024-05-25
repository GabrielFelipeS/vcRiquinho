package br.com.ifsp.vcRiquinho.pessoa.factory.interfaces;

import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;

public interface IFactoryPessoaCreatorProvider {
	 IFactoryPessoaCreator create(Set<Conta> contas);
}
