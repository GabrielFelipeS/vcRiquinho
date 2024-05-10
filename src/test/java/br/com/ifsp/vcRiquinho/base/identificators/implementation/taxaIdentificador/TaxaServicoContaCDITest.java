package br.com.ifsp.vcRiquinho.base.identificators.implementation.taxaIdentificador;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.identificators.interfaces.TaxaIdentificator;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaFisica;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaJuridica;

class TaxaServicoContaCDITest {

	@Test
	void getTaxaIdentificatorPorClassePessoaFisica() {
		TaxaIdentificator identificador = new TaxaServicoContaCDI();
		assertEquals(TaxaServicoContaCDI.TAXA_SERVICO_PF, identificador.getTaxa(PessoaFisica.class));
	}

	@Test
	void getTaxaIdentificatorPorClassePessoaFisica2() {
		TaxaIdentificator identificador = new TaxaServicoContaCDI();
		assertEquals(TaxaServicoContaCDI.TAXA_SERVICO_PF,
				identificador.getTaxa(new PessoaFisica("", "", new ContaCDI(0, "",0.0, 0.0), "")));
	}

	@Test
	void getTaxaIdentificatorPorClassePessoaJuridica() {
		TaxaIdentificator identificador = new TaxaServicoContaCDI();
		assertEquals(TaxaServicoContaCDI.TAXA_SERVICO_PJ, identificador.getTaxa(PessoaJuridica.class));
	}

	@Test
	void getTaxaIdentificatorPorClassePessoaJuridica2() {
		TaxaIdentificator identificador = new TaxaServicoContaCDI();
		assertEquals(TaxaServicoContaCDI.TAXA_SERVICO_PJ,
				identificador.getTaxa(new PessoaJuridica("", "", new ContaCDI(0, "",0.0, 0.0), "")));
	}

}
