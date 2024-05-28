package br.com.ifsp.vcRiquinho.pessoa.factory.interfaces;

import java.util.Set;

import br.com.ifsp.vcRiquinho.base.interfaces.Factory;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;

public interface IFactoryPessoaCreatorProvider extends Factory<Set<Conta>, IFactoryPessoaCreator>{
}
