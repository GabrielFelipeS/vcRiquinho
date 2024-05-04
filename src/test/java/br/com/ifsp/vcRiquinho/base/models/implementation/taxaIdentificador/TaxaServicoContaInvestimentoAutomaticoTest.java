package br.com.ifsp.vcRiquinho.base.models.implementation.taxaIdentificador;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.models.interfaces.TaxaIdentificator;
import br.com.ifsp.vcRiquinho.conta.models.entities.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.pessoa.model.concrate.PessoaFisica;
import br.com.ifsp.vcRiquinho.pessoa.model.concrate.PessoaJuridica;

class TaxaServicoContaInvestimentoAutomaticoTest {

	@Test
	void getTaxaIdentificatorPorClassePessoaFisica() {
		TaxaIdentificator identificador = new TaxaServicoContaInvestimentoAutomatico();
		assertEquals(TaxaServicoContaInvestimentoAutomatico.TAXA_SERVICO_PF, identificador.getTaxa(PessoaFisica.class));
	}

	@Test
	void getTaxaIdentificatorPorClassePessoaFisica2() {
		TaxaIdentificator identificador = new TaxaServicoContaInvestimentoAutomatico();
		assertEquals(TaxaServicoContaInvestimentoAutomatico.TAXA_SERVICO_PF,
				identificador.getTaxa(new PessoaFisica(null, null, new ContaCDI(0.0, 0.0), null)));
	}

	@Test
	void getTaxaIdentificatorPorClassePessoaJuridica() {
		TaxaIdentificator identificador = new TaxaServicoContaInvestimentoAutomatico();
		assertEquals(TaxaServicoContaInvestimentoAutomatico.TAXA_SERVICO_PJ,
				identificador.getTaxa(PessoaJuridica.class));
	}

	@Test
	void getTaxaIdentificatorPorClassePessoaJuridica2() {
		TaxaIdentificator identificador = new TaxaServicoContaInvestimentoAutomatico();
		assertEquals(TaxaServicoContaInvestimentoAutomatico.TAXA_SERVICO_PJ,
				identificador.getTaxa(new PessoaJuridica(null, null, new ContaCDI(0.0, 0.0), null)));
	}

}
