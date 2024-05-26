package br.com.ifsp.vcRiquinho.simulador.models.abstracts;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.simulador.exceptions.GracePeriodException;

public interface SimuladorInterface {
	Double simular(Conta conta, Double taxa, Integer dias) throws GracePeriodException;
	Boolean isGracePeriod(Conta conta, Integer dias);
}
