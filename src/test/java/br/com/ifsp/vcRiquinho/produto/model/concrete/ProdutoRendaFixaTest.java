package br.com.ifsp.vcRiquinho.produto.model.concrete;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.produto.model.abstracts.Produto;

class ProdutoRendaFixaTest {

	@Test
	void isInGracePeriodTest1() {
		Produto produto = new ProdutoRendaVariavel(20.0);
		assertFalse(produto.isInGracePeriodIn(0));
	}
	
	@Test
	void isInGracePeriodTest2() {
		Produto produto = new ProdutoRendaVariavel(20.0);
		assertFalse(produto.isInGracePeriodIn(-50));
	}
	
	@Test
	void isInGracePeriodTest3() {
		Produto produto = new ProdutoRendaVariavel(20.0);
		assertFalse(produto.isInGracePeriodIn(50));
	}

}
