package br.com.ifsp.vcRiquinho.produto.models.abstracts;

import java.util.Objects;

public abstract class Produto {
	private Integer id;
	private String nome;
	private String descricao;

	public Produto(Integer id, String nome, String descricao) {
		this.id = Objects.requireNonNull(id, "ID não pode ser nulo");
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
