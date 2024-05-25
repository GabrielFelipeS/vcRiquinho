package br.com.ifsp.vcRiquinho.produto.conctrate.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.ProdutoRendaFixaFactory;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProduto;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaFixa;

public class ProdutoRendaFixaFactoryTest {

	@Test
	void createByProdutoRendaFixa() {
		IFactoryProduto factory = new ProdutoRendaFixaFactory();

		Produto produto = factory.createBy(new DTOProduto(0, 0, "renda_fixa", "", "", 0.0));
		assertEquals(ProdutoRendaFixa.class, produto.getClass());
	}

	@Test
	void createByProdutoRendaFixaErroNaCriacao() {
		IFactoryProduto factory = new ProdutoRendaFixaFactory();

		assertThrows(RuntimeException.class,
				() -> factory.createBy(new DTOProduto(0, null, "renda_fixa", "", "", 0.0)));
	}

	@Test
	void convertProdutoRendaFixa() {
		Produto produto = new ProdutoRendaFixa(0, "", "", 0.0, 0, LocalDate.now());

		IFactoryProduto factory = new ProdutoRendaFixaFactory();

		assertEquals(produto.getId(), factory.convert(produto).id());
	}

}
