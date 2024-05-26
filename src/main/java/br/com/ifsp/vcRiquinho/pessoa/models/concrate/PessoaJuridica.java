package br.com.ifsp.vcRiquinho.pessoa.models.concrate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;

public class PessoaJuridica extends Pessoa{
	private String cnpj;
	
	public PessoaJuridica(Integer id, String nome, String email, Set<Conta> contas, String cnpj) {
		super(id, nome, email, contas);
		this.cnpj = Objects.requireNonNull(cnpj, "cnpj não pode ser nulo");
	}
	
	public PessoaJuridica(Integer id, String nome, String email, Conta conta, String cnpj) {
		this(id, nome, email, new HashSet<>(Arrays.asList(conta)), cnpj);
	}

	@Override
	public String getDocumentoTitular() {
		return cnpj;
	}

	@Override
	public String tipo() {
		return "juridica";
	}
}
