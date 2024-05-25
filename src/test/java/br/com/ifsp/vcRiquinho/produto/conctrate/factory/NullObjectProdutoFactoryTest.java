package br.com.ifsp.vcRiquinho.produto.conctrate.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.NullObjectProtudoFactory;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProduto;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import br.com.ifsp.vcRiquinho.produto.models.concrete.NullObjectProduto;

public class NullObjectProdutoFactoryTest {
	@Test
	void createByNullObject() {
		IFactoryProduto factory = new NullObjectProtudoFactory();

		Produto produto = factory.createBy(new DTOProduto(null, null, null, null, null, null));
		assertEquals(NullObjectProduto.class, produto.getClass());
	}

	@Test
	void convertNullObject() {
		Produto produto = new NullObjectProduto();

		IFactoryProduto factory = new NullObjectProtudoFactory();

		assertEquals(produto.getId(), factory.convert(produto).id());
	}

}
