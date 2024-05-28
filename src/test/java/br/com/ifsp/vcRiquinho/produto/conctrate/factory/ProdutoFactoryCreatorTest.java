package br.com.ifsp.vcRiquinho.produto.conctrate.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.NullObjectProtudoFactory;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.ProdutoRendaFixaFactory;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.ProdutoRendaVariavelFactory;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProduto;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProdutoCreator;

public class ProdutoFactoryCreatorTest {
	@Test
	void createByProdutoRendaFixaFactory() {
		IFactoryProdutoCreator factoryCreator = new FactoryProdutoCreator();

		IFactoryProduto factory = factoryCreator.createBy("renda_fixa");
		assertEquals(ProdutoRendaFixaFactory.class, factory.getClass());
	}

	@Test
	void createByProdutoRendaVariavelFactory() {
		IFactoryProdutoCreator factoryCreator = new FactoryProdutoCreator();

		IFactoryProduto factory = factoryCreator.createBy("renda_variavel");
		assertEquals(ProdutoRendaVariavelFactory.class, factory.getClass());
	}

	@Test
	void createByNullObjectProdutoFactory() {
		IFactoryProdutoCreator factoryCreator = new FactoryProdutoCreator();

		IFactoryProduto factory = factoryCreator.createBy("null_object");
		assertEquals(NullObjectProtudoFactory.class, factory.getClass());
	}

	@Test
	void createByNaoEncontradoDefaultValue() {
		IFactoryProdutoCreator factoryCreator = new FactoryProdutoCreator();

		IFactoryProduto factory = factoryCreator.createBy("SEM_MAPEAMENTO");
		assertEquals(NullObjectProtudoFactory.class, factory.getClass());
	}
}
