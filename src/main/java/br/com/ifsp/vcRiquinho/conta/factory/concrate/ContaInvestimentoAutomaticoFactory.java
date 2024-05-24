package br.com.ifsp.vcRiquinho.conta.factory.concrate;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public class ContaInvestimentoAutomaticoFactory implements IFactoryConta {
	private Produto produto;
	
	public ContaInvestimentoAutomaticoFactory(Produto produto) {
		this.produto = produto ;
	}
	
	@Override
	public Conta createBy(DTOConta dto) {
		return new ContaInvestimentoAutomatico(dto.numConta(), dto.documentoTitular(), dto.montanteFinanceiro(), produto);
	}

	@Override
	public DTOConta convert(Conta contaToConvert) {
		ContaInvestimentoAutomatico conta = (ContaInvestimentoAutomatico) contaToConvert;
		return new DTOConta(conta);
	}
}
