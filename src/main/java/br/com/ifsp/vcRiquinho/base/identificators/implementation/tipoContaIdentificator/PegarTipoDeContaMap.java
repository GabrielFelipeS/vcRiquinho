package br.com.ifsp.vcRiquinho.base.identificators.implementation.tipoContaIdentificator;

import java.util.HashMap;
import java.util.Map;

import br.com.ifsp.vcRiquinho.base.identificators.implementation.taxaIdentificador.SemTaxaServico;
import br.com.ifsp.vcRiquinho.base.identificators.implementation.taxaIdentificador.TaxaServicoContaCDI;
import br.com.ifsp.vcRiquinho.base.identificators.implementation.taxaIdentificador.TaxaServicoContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.base.identificators.interfaces.TaxaIdentificator;
import br.com.ifsp.vcRiquinho.base.identificators.interfaces.TipoContaIdentificador;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaInvestimentoAutomatico;

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
		return map.getOrDefault(conta.getClass(), new SemTaxaServico());
	}
}
