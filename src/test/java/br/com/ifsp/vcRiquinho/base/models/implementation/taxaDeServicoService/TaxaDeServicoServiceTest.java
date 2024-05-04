package br.com.ifsp.vcRiquinho.base.models.implementation.taxaDeServicoService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.models.implementation.taxaIdentificador.SemTaxaServico;
import br.com.ifsp.vcRiquinho.base.models.implementation.taxaIdentificador.TaxaServicoContaCDI;
import br.com.ifsp.vcRiquinho.base.models.implementation.taxaIdentificador.TaxaServicoContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.base.models.interfaces.TaxaDeServicoServiceInterface;
import br.com.ifsp.vcRiquinho.conta.models.entities.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.conta.models.entities.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.conta.models.entities.concrate.ContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.pessoa.model.concrate.PessoaFisica;
import br.com.ifsp.vcRiquinho.pessoa.model.concrate.PessoaJuridica;

class TaxaDeServicoServiceTest {

	@Test
	void getTaxaDeContaCDIEPessoaFisica() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		assertEquals(TaxaServicoContaCDI.TAXA_SERVICO_PF, taxaService.obterTaxa(new ContaCDI(0.0, 0.0),
				new PessoaFisica(null, null, new ContaCDI(0.0, 0.0), null)) );
	}
	
	@Test
	void getTaxaDeContaCDIEPessoaJuridica() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		assertEquals(TaxaServicoContaCDI.TAXA_SERVICO_PJ, taxaService.obterTaxa(new ContaCDI(0.0, 0.0),
				new PessoaJuridica(null, null, new ContaCDI(0.0, 0.0), null)) );
	}
	
	@Test
	void getTaxaDeContaInvestimentoAutomaticoEPessoaFisica() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		assertEquals(TaxaServicoContaInvestimentoAutomatico.TAXA_SERVICO_PF, taxaService.obterTaxa(new ContaInvestimentoAutomatico(0.0, null),
				new PessoaFisica(null, null, new ContaInvestimentoAutomatico(0.0, null), null)) );
	}
	
	@Test
	void getTaxaDeContaInvestimentoAutomaticoEPessoaJuridica() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		assertEquals(TaxaServicoContaInvestimentoAutomatico.TAXA_SERVICO_PJ, taxaService.obterTaxa(new ContaInvestimentoAutomatico(0.0, null),
				new PessoaJuridica(null, null, new ContaInvestimentoAutomatico(0.0, null), null)) );
	}
	
	@Test
	void getTaxaDeContaCorrenteEPessoaFisica() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		assertEquals(SemTaxaServico.TAXA_SERVICO_PF, taxaService.obterTaxa(new ContaCorrente(0.0),
				new PessoaFisica(null, null, new ContaCorrente(0.0), null)) );
	}
	
	@Test
	void getTaxaDeContaCorrenteEPessoaJuridica() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		assertEquals(SemTaxaServico.TAXA_SERVICO_PJ, taxaService.obterTaxa(new ContaCorrente(0.0),
				new PessoaJuridica(null, null, new ContaCorrente(0.0), null)) );
	}

}
