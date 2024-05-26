package br.com.ifsp.vcRiquinho.pessoa.models.concrate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;

public class PessoaFisica extends Pessoa {
	private String cpf;
	
	public PessoaFisica(Integer id, String nome, String email, Set<Conta> contas, String cpf) {
		super(id, nome, email, contas);
		this.cpf = Objects.requireNonNull(cpf, "cpf não pode ser nulo");
	}
	
	public PessoaFisica(Integer id, String nome, String email, Conta conta, String cpf) {
		this(id, nome, email, new HashSet<>(Arrays.asList(conta)), cpf);
	}

	@Override
	public String getDocumentoTitular() {
		return cpf;
	}
	
	@Override
	public String tipo() {
		return "fisica";
	}
}
