package br.com.ifsp.vcRiquinho.base.identificators.interfaces;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;

public interface TipoContaIdentificador {
	TaxaIdentificator getTaxaIdentificator(Conta conta);
	TaxaIdentificator getTaxaIdentificator(Class<? extends Conta> conta);
}
