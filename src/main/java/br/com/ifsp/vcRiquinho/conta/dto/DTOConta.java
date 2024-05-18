package br.com.ifsp.vcRiquinho.conta.dto;

public record DTOConta(Integer id, String documentoTitular, Double montanteFinanceiro, Integer id_produto, Double cdi,
		String tipo_conta) {

}
