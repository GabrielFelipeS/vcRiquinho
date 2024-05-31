package br.com.ifsp.vcRiquinho.base.identificators.implementation.taxaIdentificador;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.identificators.interfaces.TaxaIdentificator;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaFisica;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaJuridica;

class SemTaxaServicoTest {

	@Test
	void givenSemTaxaServico_whenPessoaFisica_thenSemTaxa() {
		TaxaIdentificator identificador = new SemTaxaServico();
		
		assertEquals(SemTaxaServico.SEM_TAXA_SERVICO,
				identificador.getTaxa(new PessoaFisica(0, "", "", new ContaCDI(0, "", 0.0, 0.0), "")));
	}

	@Test
	void givenSemTaxaServico_whenPessoaJuridica_thenSemTaxa() {
		TaxaIdentificator identificador = new SemTaxaServico();
		
		assertEquals(SemTaxaServico.SEM_TAXA_SERVICO,
				identificador.getTaxa(new PessoaJuridica(0, "", "", new ContaCDI(0, "", 0.0, 0.0), "")));
	}

}
