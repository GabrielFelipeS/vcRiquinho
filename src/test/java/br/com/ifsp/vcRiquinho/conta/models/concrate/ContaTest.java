package br.com.ifsp.vcRiquinho.conta.models.concrate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaFixa;

public class ContaTest {

	@Test
	void validarRequerimentoDeNuloCDI1() {
		assertThrows(NullPointerException.class,
				() -> new ContaCDI(null, "", 0.0, 0.0));
	}

	@Test
	void validarRequerimentoDeNuloCDI2() {
		assertThrows(NullPointerException.class,
				() -> new ContaCDI(0, null, 0.0, 0.0));
	}

	@Test
	void validarRequerimentoDeNuloCDI3() {
		assertThrows( NullPointerException.class,
				() -> new ContaCDI(0, "", null, 0.0));
	}

	@Test
	void validarRequerimentoDeNuloCDI4() {
		assertThrows(NullPointerException.class, () -> new ContaCDI(0, "", 0.0, null));
	}

	@Test
	void validarRequerimentoDeNaoNuloCDI() {
		assertDoesNotThrow(() -> new ContaCDI(0, "", 0.0, 0.0));
	}

	@Test
	void validarRequerimentoDeNuloCorrrente1() {
		assertThrows(NullPointerException.class,
				() -> new ContaCorrente(null, "", 0.0));
	}

	@Test
	void validarRequerimentoDeNuloCorrrente2() {
		assertThrows(NullPointerException.class,
				() -> new ContaCorrente(0, null, 0.0));
	}

	@Test
	void validarRequerimentoDeNuloCorrrente3() {
		assertThrows(NullPointerException.class,
				() -> new ContaCorrente(0, "", null));
	}

	@Test
	void validarRequerimentoDeNaoNuloCorrrente() {
		assertDoesNotThrow(() -> new ContaCorrente(0, "", 0.0));
	}

	@Test
	void validarRequerimentoDeNuloInvestimentoAutomatico1() {
		assertThrows(NullPointerException.class,
				() -> new ContaInvestimentoAutomatico(null, "", 0.0, new ProdutoRendaFixa(0.0, 0)));
	}

	@Test
	void validarRequerimentoDeNuloInvestimentoAutomatico2() {
		assertThrows(NullPointerException.class,
				() -> new ContaInvestimentoAutomatico(0, null, 0.0, new ProdutoRendaFixa(0.0, 0)));
	}

	@Test
	void validarRequerimentoDeNuloInvestimentoAutomatico3() {
		assertThrows(NullPointerException.class,
				() -> new ContaInvestimentoAutomatico(0, "", null, new ProdutoRendaFixa(0.0, 0)));
	}

	@Test
	void validarRequerimentoDeNaoNuloInvestimentoAutomatico() {
		assertThrows(NullPointerException.class,
				() -> new ContaInvestimentoAutomatico(0, "", 0.0, null));
	}
}
