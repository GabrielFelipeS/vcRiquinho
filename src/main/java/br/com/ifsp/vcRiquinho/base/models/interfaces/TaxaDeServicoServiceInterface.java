package br.com.ifsp.vcRiquinho.base.models.interfaces;

import br.com.ifsp.vcRiquinho.conta.models.entities.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.model.abstracts.Pessoa;

public interface TaxaDeServicoServiceInterface {
	Double obterTaxa(Conta conta, Pessoa pessoa);
}
