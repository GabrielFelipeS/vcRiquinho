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
import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaFixa;

class TaxaDeServicoServiceTest {

	
	@Test
	void validarRequerimentoDeNaoNulo() {
		assertThrows(NullPointerException.class, ()-> new TaxaDeServicoService(null));
	}
	
	@Test
	void getTaxaDeContaCDIEPessoaFisica() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		assertEquals(TaxaServicoContaCDI.TAXA_SERVICO_PF, taxaService.obterTaxa(new ContaCDI(0, "",0.0, 0.0),
				new PessoaFisica("", "", new ContaCDI(0, "", 0.0, 0.0), "")) );
	}
	
	@Test
	void getTaxaDeContaCDIEPessoaJuridica() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		assertEquals(TaxaServicoContaCDI.TAXA_SERVICO_PJ, taxaService.obterTaxa(new ContaCDI(0, "",0.0, 0.0),
				new PessoaJuridica("", "", new ContaCDI(0, "",0.0, 0.0), "")) );
	}
	
	@Test
	void getTaxaDeContaInvestimentoAutomaticoEPessoaFisica() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		assertEquals(TaxaServicoContaInvestimentoAutomatico.TAXA_SERVICO_PF, taxaService.obterTaxa(new ContaInvestimentoAutomatico(0, "",0.0,  new ProdutoRendaFixa(0, 0.0, 0)),
				new PessoaFisica("", "", new ContaInvestimentoAutomatico(0, "",0.0,  new ProdutoRendaFixa(0, 0.0, 0)), "")) );
	}
	
	@Test
	void getTaxaDeContaInvestimentoAutomaticoEPessoaJuridica() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		assertEquals(TaxaServicoContaInvestimentoAutomatico.TAXA_SERVICO_PJ, taxaService.obterTaxa(new ContaInvestimentoAutomatico(0, "",0.0,  new ProdutoRendaFixa(0, 0.0, 0)),
				new PessoaJuridica("", "", new ContaInvestimentoAutomatico(0, "",0.0, new ProdutoRendaFixa(0, 0.0, 0)), "")) );
	}
	
	@Test
	void getTaxaDeContaCorrenteEPessoaFisica() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		assertEquals(SemTaxaServico.TAXA_SERVICO_PF, taxaService.obterTaxa(new ContaCorrente(0, "",0.0),
				new PessoaFisica("", "", new ContaCorrente(0, "",0.0), "")) );
	}
	
	@Test
	void getTaxaDeContaCorrenteEPessoaJuridica() {
		TaxaDeServicoServiceInterface taxaService = new TaxaDeServicoService();
		assertEquals(SemTaxaServico.TAXA_SERVICO_PJ, taxaService.obterTaxa(new ContaCorrente(0, "", 0.0),
				new PessoaJuridica("", "", new ContaCorrente(0, "", 0.0), "")) );
	}

}
