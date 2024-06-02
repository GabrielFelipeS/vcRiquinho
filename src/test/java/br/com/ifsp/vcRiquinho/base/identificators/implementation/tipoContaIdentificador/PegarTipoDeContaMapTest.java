package br.com.ifsp.vcRiquinho.base.identificators.implementation.tipoContaIdentificador;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.identificators.implementation.taxaIdentificador.SemTaxaServico;
import br.com.ifsp.vcRiquinho.base.identificators.implementation.taxaIdentificador.TaxaServicoContaCDI;
import br.com.ifsp.vcRiquinho.base.identificators.implementation.taxaIdentificador.TaxaServicoContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.base.identificators.implementation.tipoContaIdentificator.PegarTipoDeContaMap;
import br.com.ifsp.vcRiquinho.base.identificators.interfaces.TipoContaIdentificador;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.produto.models.concrete.NullObjectProduto;

class PegarTipoDeContaMapTest {


	@Test
	void getTaxaIdentificatorPassandoOObjecto() {
		
		TipoContaIdentificador identificador = new PegarTipoDeContaMap();
		assertEquals(TaxaServicoContaCDI.class,
				identificador.getTaxaIdentificator(new ContaCDI(0, "", 0.0, 0.0)).getClass());
		assertEquals(TaxaServicoContaInvestimentoAutomatico.class,
				identificador
						.getTaxaIdentificator(
								new ContaInvestimentoAutomatico(0, "", 5.0, new NullObjectProduto()))
						.getClass());
		assertEquals(SemTaxaServico.class,
				identificador.getTaxaIdentificator(new ContaCorrente(0, "", 5.0)).getClass());
	}

}
