package br.com.ifsp.vcRiquinho.produto.dto;

public record DTOProduto(Integer id, Integer carencia, String tipo_produto, 
		String nome, String descricao, Double rendimentoMensal) {

}
