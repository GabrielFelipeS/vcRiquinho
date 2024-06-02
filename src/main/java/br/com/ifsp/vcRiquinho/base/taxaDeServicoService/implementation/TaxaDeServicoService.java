package br.com.ifsp.vcRiquinho.base.taxaDeServicoService.implementation;

import java.util.Objects;

import br.com.ifsp.vcRiquinho.base.identificators.implementation.tipoContaIdentificator.PegarTipoDeContaMap;
import br.com.ifsp.vcRiquinho.base.identificators.interfaces.TipoContaIdentificador;
import br.com.ifsp.vcRiquinho.base.taxaDeServicoService.interfaces.TaxaDeServicoServiceInterface;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;

public class TaxaDeServicoService implements TaxaDeServicoServiceInterface {

	private TipoContaIdentificador identificador;
	
	
	public TaxaDeServicoService() {
		this(new PegarTipoDeContaMap());
	}
	
	public TaxaDeServicoService(TipoContaIdentificador identificador) {
		this.identificador = Objects.requireNonNull(identificador, "O tipo conta identificador não pode ser nulo");
	}
	
	
	@Override
	public Double obterTaxa(Conta conta, Pessoa pessoa) {
		return identificador.getTaxaIdentificator(conta).getTaxa(pessoa);
	}

}
