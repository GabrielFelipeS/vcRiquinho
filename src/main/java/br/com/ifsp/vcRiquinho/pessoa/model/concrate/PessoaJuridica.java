package br.com.ifsp.vcRiquinho.pessoa.model.concrate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.models.entities.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.model.abstracts.Pessoa;

public class PessoaJuridica extends Pessoa{
	private String cnpj;
	
	public PessoaJuridica(String nome, String email, Set<Conta> contas, String cnpj) {
		super(nome, email, contas);
		this.cnpj = cnpj;
	}
	
	public PessoaJuridica(String nome, String email, Conta conta, String cnpj) {
		this(nome, email, new HashSet<>(Arrays.asList(conta)), cnpj);
	}
}
