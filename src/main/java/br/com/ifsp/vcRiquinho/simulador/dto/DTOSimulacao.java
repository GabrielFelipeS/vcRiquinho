package br.com.ifsp.vcRiquinho.simulador.dto;



public record DTOSimulacao(String tipoConta, String nomeProduto, Double montanteFinanceiroAtual, 
		String carencia, Integer dias, Double montanteFinanceiroBruto, String taxaServico, Double montanteFinanceiroLiquido) {

}
