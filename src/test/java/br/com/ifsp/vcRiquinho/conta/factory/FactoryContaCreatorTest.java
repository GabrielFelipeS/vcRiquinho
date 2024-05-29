package br.com.ifsp.vcRiquinho.conta.factory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.conta.factory.concrate.ContaCdiFactory;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.ContaCorrenteFactory;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.ContaInvestimentoAutomaticoFactory;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreator;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryConta;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryContaCreator;
import br.com.ifsp.vcRiquinho.produto.models.concrete.NullObjectProduto;

public class FactoryContaCreatorTest {

	@Test
	void createByContaCDIaFactory() {
		IFactoryContaCreator factoryCreator = new FactoryContaCreator(new NullObjectProduto());

		IFactoryConta factory = factoryCreator.createBy("cdi");
		assertEquals(ContaCdiFactory.class, factory.getClass());
	}

	@Test
	void createByContaCorrenteFactory() {
		IFactoryContaCreator factoryCreator = new FactoryContaCreator(new NullObjectProduto());

		IFactoryConta factory = factoryCreator.createBy("corrente");
		assertEquals(ContaCorrenteFactory.class, factory.getClass());
	}

	@Test
	void createByContaInvestimentoAutomaticoFactory() {
		IFactoryContaCreator factoryCreator = new FactoryContaCreator(new NullObjectProduto());

		IFactoryConta factory = factoryCreator.createBy("investimento_automatico");
		assertEquals(ContaInvestimentoAutomaticoFactory.class, factory.getClass());
	}

	@Test
	void createByTipoContaNaoExisteDefaultValue() {
		IFactoryContaCreator factoryCreator = new FactoryContaCreator(new NullObjectProduto());

		IFactoryConta factory = factoryCreator.createBy("TIPO_NAO_EXISTE");
		assertEquals(ContaCorrenteFactory.class, factory.getClass());
	}

	@Test
	void convertContaCDIFactory() {
		IFactoryContaCreator factoryCreator = new FactoryContaCreator(new NullObjectProduto());

		IFactoryConta factory = new ContaCdiFactory();

		assertEquals("ContaCdiFactory", factoryCreator.convert(factory));
	}

	@Test
	void convertContaCorrenteFactory() {
		IFactoryContaCreator factoryCreator = new FactoryContaCreator(new NullObjectProduto());

		IFactoryConta factory = new ContaCorrenteFactory();

		assertEquals("ContaCorrenteFactory", factoryCreator.convert(factory));
	}

	@Test
	void convertContaInvestimentoAutomaticoFactory() {
		IFactoryContaCreator factoryCreator = new FactoryContaCreator(new NullObjectProduto());

		IFactoryConta factory = new ContaInvestimentoAutomaticoFactory(new NullObjectProduto());

		assertEquals("ContaInvestimentoAutomaticoFactory", factoryCreator.convert(factory));
	}

}
