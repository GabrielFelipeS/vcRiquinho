package br.com.ifsp.vcRiquinho.pessoa.factory.concrate;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoa;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoaCreator;

public class FactoryPessoaCreatorTest {

	@Test
	void createByPessoaFisicaFactory() {
		IFactoryPessoaCreator factoryCreator = new FactoryPessoaCreator(new HashSet<>());

		IFactoryPessoa factory = factoryCreator.createBy("fisica");
		assertEquals(PessoaFisicaFactory.class, factory.getClass());
	}

	@Test
	void createByPessoaJuridicaFactory() {
		IFactoryPessoaCreator factoryCreator = new FactoryPessoaCreator(new HashSet<>());

		IFactoryPessoa factory = factoryCreator.createBy("juridica");
		assertEquals(PessoaJuridicaFactory.class, factory.getClass());
	}

	@Test
	void createByTipoPessoaNaoExiste() {
		IFactoryPessoaCreator factoryCreator = new FactoryPessoaCreator(new HashSet<>());

		IFactoryPessoa factory = factoryCreator.createBy("TIPO_NAO_EXISTE");
		assertNull(factory);
	}
}
