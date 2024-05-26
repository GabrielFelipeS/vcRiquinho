package br.com.ifsp.vcRiquinho.conta.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.ContaInvestimentoAutomaticoFactory;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.produto.models.concrete.NullObjectProduto;

public class ContaInvestimentoAutomaticoFactoryTest {

	@Test
	void createContaInvestimentoAutomaticoComProduto() {
		IFactoryConta factory = new ContaInvestimentoAutomaticoFactory(new NullObjectProduto());

		Conta conta = factory.createBy(new DTOConta(0, "", 0.0, 0, 0.0, ""));
		assertEquals(ContaInvestimentoAutomatico.class, conta.getClass());
	}

	@Test
	void createContaInvestimentoAutomaticoSemProdutoProduto() {
		IFactoryConta factory = new ContaInvestimentoAutomaticoFactory(null);

		assertThrows(RuntimeException.class, () -> factory.createBy(new DTOConta(0, "", 0.0, 0, 0.0, "")));
	}

	@Test
	void createContaInvestimentoAutomaticoAlgumValorNulo() {
		IFactoryConta factory = new ContaInvestimentoAutomaticoFactory(new NullObjectProduto());

		assertThrows(RuntimeException.class, () -> factory.createBy(new DTOConta(0, null, 0.0, 0, 0.0, "")));
	}

	@Test
	void convertContaInvestimentoAutomatico() {
		IFactoryConta factory = new ContaInvestimentoAutomaticoFactory(new NullObjectProduto());

		Conta conta = new ContaInvestimentoAutomatico(0, "", 0.0, new NullObjectProduto());

		assertEquals(conta.getNumConta(), factory.convert(conta).numConta());
	}

}
