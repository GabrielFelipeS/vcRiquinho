package br.com.ifsp.vcRiquinho.produto.models.abstracts;

import java.util.Objects;

public abstract class Produto {
	private String nome;
	private String descricao;

	public Produto(String nome, String descricao) {
		this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo");
		this.descricao = Objects.requireNonNull(descricao, "Descrição não pode ser nulo");
	}

	public abstract Double getRendimentoMensal();
	
	// TODO talvez código inutil, verificar
	public abstract Integer getCarencia();
	
	public abstract Boolean isInGracePeriodIn(int days);
	
	
	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
