package br.com.ifsp.vcRiquinho.produto.models.concrete;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.produto.exceptions.DaysCannotByNegativeException;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

class ProdutoRendaVariavelTest {

	@Test
	void isInGracePeriodTestisInGracePeriodInTrue1() {
		Produto produto = new ProdutoRendaFixa("nome","descricao", 0.0, 10, LocalDate.now());
		assertTrue(produto.isInGracePeriodIn(0));
	}
	
	@Test
	void isInGracePeriodTestisInGracePeriodInTrue2() {
		Produto produto = new ProdutoRendaFixa("nome","descricao", 0.0, 10, LocalDate.now());
		assertTrue(produto.isInGracePeriodIn(9));
	}
	
	
	@Test
	void isInGracePeriodTestisInGracePeriodInTrue3() {
		Produto produto = new ProdutoRendaFixa("nome","descricao", 0.0, 10, LocalDate.now());
		assertTrue(produto.isInGracePeriodIn(10));
	}
	
	@Test
	void isInGracePeriodTestisInGracePeriodInTrue4() {
		Produto produto = new ProdutoRendaFixa(0.0, 10);
		assertTrue(produto.isInGracePeriodIn(10));
	}
	
	
	
	@Test
	void isInGracePeriodTestisInGracePeriodInFalse1() {
		Produto produto = new ProdutoRendaFixa("nome","descricao", 0.0, 10, LocalDate.now());
		assertFalse(produto.isInGracePeriodIn(11));
	}
	
	
	@Test
	void isInGracePeriodTestisInGracePeriodInFalse2() {
		Produto produto = new ProdutoRendaFixa("nome","descricao", 0.0, 10, LocalDate.now());
		assertFalse(produto.isInGracePeriodIn(50));
	}
	
	@Test
	void isInGracePeriodTestNegativeDays1() {
		Produto produto = new ProdutoRendaFixa("nome","descricao", 0.0, 10, LocalDate.now());
		assertThrows(DaysCannotByNegativeException.class, () -> produto.isInGracePeriodIn(-1));
	}
	
	@Test
	void isInGracePeriodTestNegativeDays2() {
		Produto produto = new ProdutoRendaFixa("nome","descricao", 0.0, 10, LocalDate.now());
		assertThrows(DaysCannotByNegativeException.class, () -> produto.isInGracePeriodIn(-50));
	}
	
	@Test
	void isInGracePeriodTestNoNegativeDays1() {
		Produto produto = new ProdutoRendaFixa("nome","descricao", 0.0, 10, LocalDate.now());
		assertDoesNotThrow(() -> produto.isInGracePeriodIn(0));
	}
	
	@Test
	void isInGracePeriodTestNoNegativeDays2() {
		Produto produto = new ProdutoRendaFixa("nome","descricao", 0.0, 10, LocalDate.now());
		assertDoesNotThrow(() -> produto.isInGracePeriodIn(1));
	}
}
