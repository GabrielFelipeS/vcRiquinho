package br.com.ifsp.vcRiquinho.base.models.implementation.tipoContaIdentificador;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.ifsp.vcRiquinho.base.models.implementation.taxaIdentificador.SemTaxaServico;
import br.com.ifsp.vcRiquinho.base.models.implementation.taxaIdentificador.TaxaServicoContaCDI;
import br.com.ifsp.vcRiquinho.base.models.implementation.taxaIdentificador.TaxaServicoContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.base.models.interfaces.TaxaIdentificator;
import br.com.ifsp.vcRiquinho.base.models.interfaces.TipoContaIdentificador;
import br.com.ifsp.vcRiquinho.conta.models.entities.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.entities.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.conta.models.entities.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.conta.models.entities.concrate.ContaInvestimentoAutomatico;

public class PegarTipoDeContaMap implements TipoContaIdentificador {
	private Map<Class<? extends Conta>, TaxaIdentificator> map;
	
	
	public PegarTipoDeContaMap() {
		map = new HashMap<>();
		map.put(ContaCDI.class, new TaxaServicoContaCDI());
		map.put(ContaInvestimentoAutomatico.class, new TaxaServicoContaInvestimentoAutomatico());
		map.put(ContaCorrente.class, new SemTaxaServico());
	}
	
	@Override
	public TaxaIdentificator getTaxaIdentificator(Conta conta) {
		return getTaxaIdentificator(conta.getClass());
	}
	
	@Override
	public TaxaIdentificator getTaxaIdentificator(Class<? extends Conta> conta) {
		return map.getOrDefault(conta, new SemTaxaServico());
	}
	
	

}
