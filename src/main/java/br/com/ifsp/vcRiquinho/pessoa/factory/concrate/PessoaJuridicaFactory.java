package br.com.ifsp.vcRiquinho.pessoa.factory.concrate;

import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoa;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;

public class PessoaJuridicaFactory implements IFactoryPessoa {
	private Set<Conta> contas;

	public PessoaJuridicaFactory(Set<Conta> contas) {
		this.contas = contas;
	}

	@Override
	public Pessoa createBy(DTOPessoa obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DTOPessoa convert(Pessoa obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
