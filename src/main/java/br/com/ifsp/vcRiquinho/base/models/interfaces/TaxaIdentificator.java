package br.com.ifsp.vcRiquinho.base.models.interfaces;

import br.com.ifsp.vcRiquinho.pessoa.model.abstracts.Pessoa;

public interface TaxaIdentificator {
	Double getTaxa(Class<? extends Pessoa> pessoa);
	Double getTaxa(Pessoa pessoa);
}
