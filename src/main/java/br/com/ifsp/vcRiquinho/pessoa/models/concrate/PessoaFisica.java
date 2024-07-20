package br.com.ifsp.vcRiquinho.pessoa.models.concrate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("fisica")
public class PessoaFisica extends Pessoa {

	public PessoaFisica() {

	}

	public PessoaFisica(Integer id, String nome, String email, Set<Conta> contas, String cpf) {
		super(id, cpf, nome, email, contas);
	}
	
	public PessoaFisica(Integer id, String nome, String email, Conta conta, String cpf) {
		this(id, nome, email, new HashSet<>(Arrays.asList(conta)), cpf);
	}

}
