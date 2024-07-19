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
@DiscriminatorValue("juridica")
public class PessoaJuridica extends Pessoa{
	public PessoaJuridica() {

	}

	public PessoaJuridica(Integer id, String nome, String email, Set<Conta> contas, String cnpj) {
		super(id, cnpj, nome, email, contas);
	}
	
	public PessoaJuridica(Integer id, String nome, String email, Conta conta, String cnpj) {
		this(id, nome, email, new HashSet<>(Arrays.asList(conta)), cnpj);
	}

	@Override
	public String tipo() {
		return "juridica";
	}
}
