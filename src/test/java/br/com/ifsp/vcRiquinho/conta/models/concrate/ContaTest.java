package br.com.ifsp.vcRiquinho.conta.models.concrate;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaFixa;

public class ContaTest {

	@Test
	void validarRequerimentoDeNuloCDI1() {
		assertThrows("O número de identificação da conta não pode ser nulo", NullPointerException.class,
				() -> new ContaCDI(null, "", 0.0, 0.0));
	}

	@Test
	void validarRequerimentoDeNuloCDI2() {
		assertThrows("O documento do titular não pode ser nulo", NullPointerException.class,
				() -> new ContaCDI(0, null, 0.0, 0.0));
	}

	@Test
	void validarRequerimentoDeNuloCDI3() {
		assertThrows("O montante financeiro da conta não pode ser nulo", NullPointerException.class,
				() -> new ContaCDI(0, "", null, 0.0));
	}

	@Test
	void validarRequerimentoDeNuloCDI4() {
		assertThrows("O cdi não pode ser vazio", NullPointerException.class, () -> new ContaCDI(0, "", 0.0, null));
	}

	@Test
	void validarRequerimentoDeNaoNuloCDI() {
		assertDoesNotThrow(() -> new ContaCDI(0, "", 0.0, 0.0));
	}

	@Test
	void validarRequerimentoDeNuloCorrrente1() {
		assertThrows("O número de identificação da conta não pode ser nulo", NullPointerException.class,
				() -> new ContaCorrente(null, "", 0.0));
	}

	@Test
	void validarRequerimentoDeNuloCorrrente2() {
		assertThrows("O documento do titular não pode ser nulo", NullPointerException.class,
				() -> new ContaCorrente(0, null, 0.0));
	}

	@Test
	void validarRequerimentoDeNuloCorrrente3() {
		assertThrows("O montante financeiro da conta não pode ser nulo", NullPointerException.class,
				() -> new ContaCorrente(0, "", null));
	}

	@Test
	void validarRequerimentoDeNaoNuloCorrrente() {
		assertDoesNotThrow(() -> new ContaCorrente(0, "", 0.0));
	}

	@Test
	void validarRequerimentoDeNuloInvestimentoAutomatico1() {
		assertThrows("O número de identificação da conta não pode ser nulo", NullPointerException.class,
				() -> new ContaInvestimentoAutomatico(null, "", 0.0, new ProdutoRendaFixa(0.0, 0)));
	}

	@Test
	void validarRequerimentoDeNuloInvestimentoAutomatico2() {
		assertThrows("O documento do titular não pode ser nulo", NullPointerException.class,
				() -> new ContaInvestimentoAutomatico(0, null, 0.0, new ProdutoRendaFixa(0.0, 0)));
	}

	@Test
	void validarRequerimentoDeNuloInvestimentoAutomatico3() {
		assertThrows("O montante financeiro da conta não pode ser nulo", NullPointerException.class,
				() -> new ContaInvestimentoAutomatico(0, "", null, new ProdutoRendaFixa(0.0, 0)));
	}

	@Test
	void validarRequerimentoDeNaoNuloInvestimentoAutomatico() {
		assertThrows("Produto não pode ser nulo", NullPointerException.class,
				() -> new ContaInvestimentoAutomatico(0, "", 0.0, null));
	}
}
