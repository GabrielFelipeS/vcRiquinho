package br.com.ifsp.vcRiquinho.pessoa.model.concrate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.models.entities.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.model.abstracts.Pessoa;

public class PessoaFisica extends Pessoa {
	private String cpf;
	
	public PessoaFisica(String nome, String email, Set<Conta> contas, String cpf) {
		super(nome, email, contas);
		this.cpf = cpf;
	}
	
	public PessoaFisica(String nome, String email, Conta conta, String cpf) {
		this(nome, email, new HashSet<>(Arrays.asList(conta)), cpf);
	}
}
