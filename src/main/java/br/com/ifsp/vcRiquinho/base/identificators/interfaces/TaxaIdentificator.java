package br.com.ifsp.vcRiquinho.base.identificators.interfaces;

import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;

public interface TaxaIdentificator {
	Double getTaxa(Pessoa pessoa);
}
