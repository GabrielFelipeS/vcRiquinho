package br.com.ifsp.vcRiquinho.pessoa.factory.concrate;

import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoa;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaFisica;

public class PessoaFisicaFactory implements IFactoryPessoa {
	private Set<Conta> contas;

	public PessoaFisicaFactory(Set<Conta> contas) {
		this.contas = contas;
	}

	@Override
	public Pessoa createBy(DTOPessoa dto) {
		return new PessoaFisica(dto.id() ,dto.nome(), dto.email(), contas, dto.documento_titular());
	}

	@Override
	public DTOPessoa convert(Pessoa pessoa) {
		return new DTOPessoa(pessoa.getId(), pessoa.getDocumentoTitular(), pessoa.getNome(), pessoa.getEmail(), pessoa.tipo());
	}
	
	@Override
	public String toString() {
		return "PessoaFisicaFactory";
	}

}
