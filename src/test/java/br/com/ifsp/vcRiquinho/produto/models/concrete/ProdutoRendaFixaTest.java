package br.com.ifsp.vcRiquinho.produto.models.concrete;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

class ProdutoRendaFixaTest {

	@Test
	void isInGracePeriodTest1() {
		Produto produto = new ProdutoRendaVariavel(0, 20.0);
		assertFalse(produto.isInGracePeriodIn(0));
	}

	@Test
	void isInGracePeriodTest2() {
		Produto produto = new ProdutoRendaVariavel(0, 20.0);
		assertFalse(produto.isInGracePeriodIn(-50));
	}

	@Test
	void isInGracePeriodTest3() {
		Produto produto = new ProdutoRendaVariavel(0, 20.0);
		assertFalse(produto.isInGracePeriodIn(50));
	}

}
