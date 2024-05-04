package br.com.ifsp.vcRiquinho.base.models.implementation.tipoContaIdentificador;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.models.implementation.taxaIdentificador.SemTaxaServico;
import br.com.ifsp.vcRiquinho.base.models.implementation.taxaIdentificador.TaxaServicoContaCDI;
import br.com.ifsp.vcRiquinho.base.models.implementation.taxaIdentificador.TaxaServicoContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.base.models.interfaces.TipoContaIdentificador;
import br.com.ifsp.vcRiquinho.conta.models.entities.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.conta.models.entities.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.conta.models.entities.concrate.ContaInvestimentoAutomatico;


class PegarTipoDeContaMapTest {

	
	@Test
	void getTaxaIdentificatorPorClasse1() {
		TipoContaIdentificador identificador = new PegarTipoDeContaMap();
		assertEquals(TaxaServicoContaCDI.class, identificador.getTaxaIdentificator(ContaCDI.class).getClass() );
	}
	
	@Test
	void getTaxaIdentificatorPorClasse2() {
		TipoContaIdentificador identificador = new PegarTipoDeContaMap();
		assertEquals(TaxaServicoContaCDI.class, identificador.getTaxaIdentificator(new ContaCDI(0.0, 0.0)).getClass() );
	}
	
	@Test
	void getTaxaIdentificatorPorClasse3() {
		TipoContaIdentificador identificador = new PegarTipoDeContaMap();
		assertEquals(TaxaServicoContaInvestimentoAutomatico.class, identificador.getTaxaIdentificator(ContaInvestimentoAutomatico.class).getClass() );
	}
	
	@Test
	void getTaxaIdentificatorPorClasse4() {
		TipoContaIdentificador identificador = new PegarTipoDeContaMap();
		assertEquals(TaxaServicoContaInvestimentoAutomatico.class, identificador.getTaxaIdentificator(new ContaInvestimentoAutomatico(5.0, null)).getClass() );
	}

	@Test
	void getTaxaIdentificatorPorClasse5() {
		TipoContaIdentificador identificador = new PegarTipoDeContaMap();
		assertEquals(SemTaxaServico.class, identificador.getTaxaIdentificator(ContaCorrente.class).getClass() );
	}
	
	@Test
	void getTaxaIdentificatorPorClasse6() {
		TipoContaIdentificador identificador = new PegarTipoDeContaMap();
		assertEquals(SemTaxaServico.class, identificador.getTaxaIdentificator(new ContaCorrente(5.0)).getClass() );
	}
	
	
}
