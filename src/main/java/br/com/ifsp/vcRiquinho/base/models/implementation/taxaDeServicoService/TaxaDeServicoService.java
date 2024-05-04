package br.com.ifsp.vcRiquinho.base.models.implementation.taxaDeServicoService;

import br.com.ifsp.vcRiquinho.base.models.implementation.tipoContaIdentificador.PegarTipoDeContaMap;
import br.com.ifsp.vcRiquinho.base.models.interfaces.TaxaDeServicoServiceInterface;
import br.com.ifsp.vcRiquinho.base.models.interfaces.TipoContaIdentificador;
import br.com.ifsp.vcRiquinho.conta.models.entities.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.model.abstracts.Pessoa;

public class TaxaDeServicoService implements TaxaDeServicoServiceInterface {

	private TipoContaIdentificador identificador;
	
	
	TaxaDeServicoService() {
		this.identificador = new PegarTipoDeContaMap();
	}
	
	public TaxaDeServicoService(TipoContaIdentificador identificador) {
		this.identificador = identificador;
	}
	
	
	@Override
	public Double obterTaxa(Conta conta, Pessoa pessoa) {
		return identificador.getTaxaIdentificator(conta).getTaxa(pessoa);
	}

}
