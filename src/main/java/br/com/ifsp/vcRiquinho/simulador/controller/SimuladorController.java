package br.com.ifsp.vcRiquinho.simulador.controller;

import java.util.Objects;

import br.com.ifsp.vcRiquinho.base.taxaDeServicoService.interfaces.TaxaDeServicoServiceInterface;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.simulador.exceptions.GracePeriodException;
import br.com.ifsp.vcRiquinho.simulador.models.abstracts.SimuladorInterface;
import br.com.ifsp.vcRiquinho.simulador.models.concrates.SimuladorService;

public class SimuladorController {
	private SimuladorInterface simulador;
	private TaxaDeServicoServiceInterface identificador;
	
	public SimuladorController(TaxaDeServicoServiceInterface identificador) {
		this(new SimuladorService(), identificador);
	}
	
	public SimuladorController(SimuladorInterface simulador, TaxaDeServicoServiceInterface identificador) {
		this.simulador = simulador;
		this.identificador = identificador;
	}

	public SimuladorController(SimuladorInterface simulador) {
		this.simulador = Objects.requireNonNull(simulador, "O simulador não pode ser nulo");
	}

	public Double taxaDeServico(Pessoa pessoa, Conta conta, Integer dias) throws GracePeriodException {
		Double taxaDeServico = identificador.obterTaxa(conta, pessoa);
		return simulador.simular(conta, taxaDeServico, dias);
	}
}
