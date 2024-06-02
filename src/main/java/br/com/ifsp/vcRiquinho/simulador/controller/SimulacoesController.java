package br.com.ifsp.vcRiquinho.simulador.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.ifsp.vcRiquinho.base.taxaDeServicoService.implementation.TaxaDeServicoService;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.simulador.dto.DTOSimulacao;
import br.com.ifsp.vcRiquinho.simulador.exceptions.GracePeriodException;

public class SimulacoesController {
	private SimuladorController simulador;
	
	public SimulacoesController() {
		this.simulador = new SimuladorController(new TaxaDeServicoService());
	}
	
	public SimulacoesController(SimuladorController simulador) {
		this.simulador = simulador;
	}
	
	public List<DTOSimulacao> simular(Pessoa pessoa, List<Conta> contas, Integer days) {
		List<DTOSimulacao> list = new ArrayList<>();
		
		for(Conta conta : contas) {
			Map<String, String> descricaoConta = conta.getDetalhes();
			Double montanteFinanceiroAtual = conta.getMontanteFinanceiro();
			Double montanteFinanceiroBruto = montanteFinanceiroAtual + conta.rendimentoPorDias(days);
			
			DTOSimulacao dto = gerarValoresParaDTO(pessoa, conta, descricaoConta, montanteFinanceiroAtual, days, montanteFinanceiroBruto);
			
			list.add(dto);
		}
		return list;
	}

	private DTOSimulacao gerarValoresParaDTO(Pessoa pessoa, Conta conta, Map<String, String> descricaoConta, Double montanteFinanceiroAtual, Integer days,
			Double montanteFinanceiroBruto) {
		try {
			double taxaDeServico = simulador.taxaDeServico(pessoa, conta, days);
			
			return gerarDTOSimulacao(descricaoConta, montanteFinanceiroAtual, days, montanteFinanceiroBruto, 
					String.format("R$ %.2f", taxaDeServico), montanteFinanceiroBruto - taxaDeServico);
			
		} catch (GracePeriodException e) {
			return  gerarDTOSimulacao(descricaoConta, montanteFinanceiroAtual, days, montanteFinanceiroBruto, 
					"Simulação menor que carência", montanteFinanceiroBruto);
		}
	}
	
	//Tipo conta - nome produto - montante financeiro atual -  carencia  -dias  -  pontante financeiro bruto - taxa de servico - montante financeiro liquido
	// 	this.tipoConta().replace("_", " "), produto.getNome(), this.getMontanteFinanceiro(), produto.getCarencia());
	private DTOSimulacao gerarDTOSimulacao(Map<String, String> descricaoConta, Double montanteFinanceiroAtual, Integer days, 
			Double montanteFinanceiroBruto, String taxaDeServico, Double montanteFinanceiroLiquido) {
		String tipoConta = descricaoConta.get("tipoConta");
		String nomeProduto = descricaoConta.get("nomeProduto");
		String carencia = descricaoConta.get("carencia");
		
		return new DTOSimulacao(tipoConta, nomeProduto, montanteFinanceiroAtual, carencia, days, montanteFinanceiroBruto, taxaDeServico, montanteFinanceiroBruto);
	}


	
}
