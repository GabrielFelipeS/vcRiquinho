package br.com.ifsp.vcRiquinho.pessoa.factory.interfaces;

import java.sql.Connection;

import br.com.ifsp.vcRiquinho.base.interfaces.Factory;
import br.com.ifsp.vcRiquinho.pessoa.repository.IRepositoryPessoa;

public interface IPessoaRepositoryFactory extends Factory<Connection, IRepositoryPessoa> {

}
