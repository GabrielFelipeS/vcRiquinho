package br.com.ifsp.vcRiquinho.base.models.implementation.taxaIdentificador;

import br.com.ifsp.vcRiquinho.base.models.interfaces.TaxaIdentificator;
import br.com.ifsp.vcRiquinho.pessoa.model.abstracts.Pessoa;

public class SemTaxaServico implements TaxaIdentificator {
	public static final double SEM_TAXA_SERVICO = 0.0;
	public static final double TAXA_SERVICO_PF = SEM_TAXA_SERVICO;
	public static final double TAXA_SERVICO_PJ = SEM_TAXA_SERVICO;
	
	@Override
	public Double getTaxa(Class<? extends Pessoa> pessoa) {
		return SEM_TAXA_SERVICO;
	}

	@Override
	public Double getTaxa(Pessoa pessoa) {
		return getTaxa(pessoa.getClass());
	}

}
