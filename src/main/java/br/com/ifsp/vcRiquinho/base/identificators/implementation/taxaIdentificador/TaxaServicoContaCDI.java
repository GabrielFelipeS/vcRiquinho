package br.com.ifsp.vcRiquinho.base.identificators.implementation.taxaIdentificador;

import java.util.HashMap;
import java.util.Map;

import br.com.ifsp.vcRiquinho.base.identificators.interfaces.TaxaIdentificator;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaFisica;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaJuridica;

public class TaxaServicoContaCDI implements TaxaIdentificator {
	private Map<Class<? extends Pessoa>, Double> map;

	// TODO Validar valores, possiblidade de ser 0.07 e 0.07
	public static final double TAXA_SERVICO_PF = 0.0007;
	public static final double TAXA_SERVICO_PJ = 0.0007;
	public static final double SEM_TAXA_SERVICO = 0.0;

	public TaxaServicoContaCDI() {
		map = new HashMap<>();
		map.put(PessoaFisica.class, TAXA_SERVICO_PF);
		map.put(PessoaJuridica.class, TAXA_SERVICO_PJ);
	}

	
	@Override
	public Double getTaxa(Pessoa pessoa) {
		return map.getOrDefault(pessoa.getClass(), SEM_TAXA_SERVICO);
	}

}
