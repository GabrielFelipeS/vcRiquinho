package br.com.ifsp.vcRiquinho.conta.models.concrate;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaFixa;

public class ContaTest {

	@Test
	void validarRequerimentoDeNaoNuloCDI() {
		assertThrows(NullPointerException.class, () -> new ContaCDI(null, "", 0.0, 0.0));
		assertThrows(NullPointerException.class, () -> new ContaCDI(0, null, 0.0, 0.0));
		assertThrows(NullPointerException.class, () -> new ContaCDI(0, "", null, 0.0));
		assertThrows(NullPointerException.class, () -> new ContaCDI(0, "", 0.0, null));
	}

	@Test
	void validarRequerimentoDeNaoNuloCorrrente() {
		assertThrows(NullPointerException.class, () -> new ContaCorrente(null, "", 0.0));
		assertThrows(NullPointerException.class, () -> new ContaCorrente(0, null, 0.0));
		assertThrows(NullPointerException.class, () -> new ContaCorrente(0, "", null));
	}

	@Test
	void validarRequerimentoDeNaoNuloInvestimentoAutomatico() {
		assertThrows(NullPointerException.class,
				() -> new ContaInvestimentoAutomatico(null, "", 0.0, new ProdutoRendaFixa(0, 0.0, 0)));
		assertThrows(NullPointerException.class,
				() -> new ContaInvestimentoAutomatico(0, null, 0.0, new ProdutoRendaFixa(0, 0.0, 0)));
		assertThrows(NullPointerException.class, () -> new ContaInvestimentoAutomatico(0, "", 0.0, null));
	}
}
