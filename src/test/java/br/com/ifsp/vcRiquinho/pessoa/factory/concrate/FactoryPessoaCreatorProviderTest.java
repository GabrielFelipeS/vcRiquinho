package br.com.ifsp.vcRiquinho.pessoa.factory.concrate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoaCreator;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoaCreatorProvider;

public class FactoryPessoaCreatorProviderTest {

	@Test
	void createByFactoryPessoaCreator() {
		IFactoryPessoaCreatorProvider factoryProvider = new FactoryPessoaCreatorProvider();

		IFactoryPessoaCreator factory = factoryProvider.create(new HashSet<>());
		assertEquals(FactoryPessoaCreator.class, factory.getClass());
	}
}
