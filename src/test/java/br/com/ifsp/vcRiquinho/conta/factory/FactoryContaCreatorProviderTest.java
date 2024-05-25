package br.com.ifsp.vcRiquinho.conta.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreator;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryContaCreator;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.produto.models.concrete.NullObjectProduto;

public class FactoryContaCreatorProviderTest {

	@Test
	void createByFactoryContaCreator() {
		IFactoryContaCreatorProvider factoryProvider = new FactoryContaCreatorProvider();

		IFactoryContaCreator factory = factoryProvider.create(new NullObjectProduto());
		assertEquals(FactoryContaCreator.class, factory.getClass());
	}
 
}
