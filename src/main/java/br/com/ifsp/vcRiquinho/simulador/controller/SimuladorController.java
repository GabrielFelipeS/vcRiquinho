package br.com.ifsp.vcRiquinho.simulador.controller;

import java.util.Objects;

import br.com.ifsp.vcRiquinho.base.taxaDeServicoService.interfaces.TaxaDeServicoServiceInterface;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.simulador.exceptions.GracePeriodException;
import br.com.ifsp.vcRiquinho.simulador.models.abstracts.SimuladorInterface;
import br.com.ifsp.vcRiquinho.simulador.models.concrates.SimuladorService;

public class SimuladorController {
	private TaxaDeServicoServiceInterface servico;
	private SimuladorInterface simulador;

	public SimuladorController(TaxaDeServicoServiceInterface servico) {
		this(servico, new SimuladorService());
	}

	public SimuladorController(TaxaDeServicoServiceInterface servico, SimuladorInterface simulador) {
		this.servico = Objects.requireNonNull(servico, "O servico não pode ser nulo");
		this.simulador = Objects.requireNonNull(simulador, "O simulador não pode ser nulo");
	}

	public Double taxaDeServico(Pessoa pessoa, Conta conta, Integer dias) throws GracePeriodException {
		Double taxaDeServico = servico.obterTaxa(conta, pessoa);
		return simulador.simular(conta, taxaDeServico, dias);
	}
}
