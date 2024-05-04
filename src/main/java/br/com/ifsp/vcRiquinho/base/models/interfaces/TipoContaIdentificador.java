package br.com.ifsp.vcRiquinho.base.models.interfaces;

import br.com.ifsp.vcRiquinho.conta.models.entities.abstracts.Conta;

public interface TipoContaIdentificador {
	TaxaIdentificator getTaxaIdentificator(Conta conta);
	TaxaIdentificator getTaxaIdentificator(Class<? extends Conta> conta);
}
