package br.com.ifsp.vcRiquinho.simulador.models.concrates;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import br.com.ifsp.vcRiquinho.simulador.exceptions.GracePeriodException;
import br.com.ifsp.vcRiquinho.simulador.models.abstracts.SimuladorInterface;

public class SimuladorService implements SimuladorInterface {

	@Override
	public Double simular(Conta conta, Double taxa, Integer dias) throws GracePeriodException {
		if(isGracePeriod(conta, dias)) throw new GracePeriodException();
		
		return conta.rendimentoPorDias(dias) * taxa;
	}

	@Override
	public Boolean isGracePeriod(Conta conta, Integer dias) {
		if(conta instanceof ContaInvestimentoAutomatico) {
			var contaInvesAuto = (ContaInvestimentoAutomatico) conta;
			Produto produto = contaInvesAuto.getProduto();
			
			return produto.isInGracePeriodIn(dias);
		}
		
		return false;
	}

}
