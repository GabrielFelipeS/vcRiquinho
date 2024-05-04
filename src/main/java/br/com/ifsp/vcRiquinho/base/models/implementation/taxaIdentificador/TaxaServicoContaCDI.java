package br.com.ifsp.vcRiquinho.base.models.implementation.taxaIdentificador;

import java.util.HashMap;
import java.util.Map;

import br.com.ifsp.vcRiquinho.base.models.interfaces.TaxaIdentificator;
import br.com.ifsp.vcRiquinho.pessoa.model.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.pessoa.model.concrate.PessoaFisica;
import br.com.ifsp.vcRiquinho.pessoa.model.concrate.PessoaJuridica;

public class TaxaServicoContaCDI implements TaxaIdentificator {
	private Map<Class<? extends Pessoa>, Double> map;

	// TODO Validar valores, possiblidade de ser 0.0007 e 0.0007
	public static final double TAXA_SERVICO_PF = 0.0007;
	public static final double TAXA_SERVICO_PJ = 0.0007;
	public static final double SEM_TAXA_SERVICO = 0.0;

	public TaxaServicoContaCDI() {
		map = new HashMap<>();
		map.put(PessoaFisica.class, TAXA_SERVICO_PF);
		map.put(PessoaJuridica.class, TAXA_SERVICO_PJ);
	}

	@Override
	public Double getTaxa(Class<? extends Pessoa> pessoa) {
		return map.getOrDefault(pessoa, SEM_TAXA_SERVICO);
	}
	
	@Override
	public Double getTaxa(Pessoa pessoa) {
		return getTaxa(pessoa.getClass());
	}

}
