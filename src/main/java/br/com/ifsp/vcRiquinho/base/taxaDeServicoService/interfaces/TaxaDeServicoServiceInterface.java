package br.com.ifsp.vcRiquinho.base.taxaDeServicoService.interfaces;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;

public interface TaxaDeServicoServiceInterface {
	Double obterTaxa(Conta conta, Pessoa pessoa);
}
