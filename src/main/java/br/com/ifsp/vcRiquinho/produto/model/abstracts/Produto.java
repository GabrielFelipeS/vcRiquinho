package br.com.ifsp.vcRiquinho.produto.model.abstracts;

public abstract class Produto {
	private String nome;
	private String descricao;

	public Produto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public abstract Double getRendimentoMensal();
	
	public abstract Integer getCarencia();
	
	public abstract Boolean isInGracePeriodIn(int days);
	
	
	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
