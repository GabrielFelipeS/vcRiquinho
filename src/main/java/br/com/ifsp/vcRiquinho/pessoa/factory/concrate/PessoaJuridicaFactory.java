package br.com.ifsp.vcRiquinho.pessoa.factory.concrate;

import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoa;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaJuridica;

public class PessoaJuridicaFactory implements IFactoryPessoa {
	private Set<Conta> contas;

	public PessoaJuridicaFactory(Set<Conta> contas) {
		this.contas = contas;
	}

	@Override
	public Pessoa createBy(DTOPessoa dto) {
		return new PessoaJuridica(dto.id(), dto.nome(), dto.email(), contas, dto.documento_titular());
	}

	@Override
	public DTOPessoa convert(Pessoa pessoa) {
		return null;
	}

}
