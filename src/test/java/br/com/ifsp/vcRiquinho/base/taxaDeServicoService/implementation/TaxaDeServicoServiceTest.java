package br.com.ifsp.vcRiquinho.base.taxaDeServicoService.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.base.identificators.implementation.taxaIdentificador.SemTaxaServico;
import br.com.ifsp.vcRiquinho.base.identificators.implementation.taxaIdentificador.TaxaServicoContaCDI;
import br.com.ifsp.vcRiquinho.base.identificators.implementation.taxaIdentificador.TaxaServicoContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.base.taxaDeServicoService.interfaces.TaxaDeServicoServiceInterface;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaFisica;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaJuridica;
import br.com.ifsp.vcRiquinho.produto.models.concrete.NullObjectProduto;
import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaFixa;

class TaxaDeServicoServiceTest {

	@Test
	void givenTaxaDeServicoService_whenContaCDIAndPessoaFisica_thenTaxaServicoPF() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		
		assertEquals(TaxaServicoContaCDI.TAXA_SERVICO_PF, taxaService.obterTaxa(
				new ContaCDI(0, "", 0.0, 0.0),
				new PessoaFisica(0, "", "", new ContaCDI(0, "", 0.0, 0.0), "")));
	}

	@Test
	void givenTaxaDeServicoService_whenContaCDIAndPessoaJuridica_thenTaxaServicoPJ() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		
		assertEquals(TaxaServicoContaCDI.TAXA_SERVICO_PJ, taxaService.obterTaxa(
				new ContaCDI(0, "", 0.0, 0.0),
				new PessoaJuridica(0, "", "", new ContaCDI(0, "", 0.0, 0.0), "")));
	}

	@Test
	void givenTaxaDeServicoService_whenContaInvestimentoAutomaticoAndPessoaFisica_thenTaxaServicoPF() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		
		assertEquals(TaxaServicoContaInvestimentoAutomatico.TAXA_SERVICO_PF,
				taxaService.obterTaxa(
						new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0, 0.0, 0)),
						new PessoaFisica(0, "", "", new ContaInvestimentoAutomatico(0, "", 0.0, new NullObjectProduto()), "")));
	}

	@Test
	void givenTaxaDeServicoService_whenContaInvestimentoAutomaticoAndPessoaJuridica_thenTaxaServicoPJ() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		
		assertEquals(TaxaServicoContaInvestimentoAutomatico.TAXA_SERVICO_PJ,
				taxaService.obterTaxa(
						new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0, 0.0, 0)),
						new PessoaJuridica(0, "", "", new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0, 0.0, 0)), "")));
	}

	@Test
	void givenTaxaDeServicoService_whenContaCorrenteAndPessoaFisica_thenTaxaServicoPF() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		
		assertEquals(SemTaxaServico.TAXA_SERVICO_PF, taxaService.obterTaxa(
				new ContaCorrente(0, "", 0.0),
				new PessoaFisica(0, "", "", new ContaCorrente(0, "", 0.0), "")));
	}

	@Test
	void givenTaxaDeServicoService_whenContaCorrenteAndPessoaJuridica_thenTaxaServicoPJ() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		
		assertEquals(SemTaxaServico.TAXA_SERVICO_PJ, taxaService.obterTaxa(
				new ContaCorrente(0, "", 0.0),
				new PessoaJuridica(0, "", "", new ContaCorrente(0, "", 0.0), "")));
	}

}
