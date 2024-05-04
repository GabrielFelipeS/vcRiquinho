package br.com.ifsp.vcRiquinho.pessoa.model.concrate;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.conta.models.entities.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.entities.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.conta.models.entities.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.conta.models.entities.concrate.ContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.pessoa.exceptions.ZeroContasException;
import br.com.ifsp.vcRiquinho.pessoa.model.abstracts.Pessoa;

class PessoaTest {

	@Test
	void createPessoaFisicaTest1() {
		assertDoesNotThrow(() -> new PessoaFisica("", "", new ContaCDI(null, null), ""));
	}

	@Test
	void createPessoaFisicaTest2() {
		assertDoesNotThrow(
				() -> new PessoaFisica("", "", new HashSet<Conta>(Arrays.asList(new ContaCDI(null, null))), ""));
	}

	@Test
	void createPessoaFisicaTest3() {
		assertDoesNotThrow(() -> new PessoaJuridica("", "", new ContaCDI(null, null), ""));
	}

	@Test
	void createPessoaFisicaTest4() {
		assertDoesNotThrow(() -> new PessoaJuridica("", "",
				new HashSet<Conta>(Arrays.asList(new ContaCDI(null, null))), ""));
	}

	@Test
	void createPessoaFisicaZeroContasExceptionTest1() {
		assertThrows(ZeroContasException.class, () -> new PessoaFisica("", "", new HashSet<Conta>(), ""));
	}

	@Test
	void createPessoaFisicaZeroContasExceptionTest2() {
		assertThrows(ZeroContasException.class, () -> new PessoaJuridica("", "", new HashSet<Conta>(), ""));
	}

	@Test
	void addContaFalse1() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCDI(null, null), "");

		assertFalse(pessoa.addConta(new ContaCDI(null, null)));
	}

	@Test
	void addContaFalse2() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(null), "");

		assertFalse(pessoa.addConta(new ContaCorrente(null)));
	}

	@Test
	void addContaFalse3() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaInvestimentoAutomatico(null, null), "");

		assertFalse(pessoa.addConta(new ContaInvestimentoAutomatico(null, null)));
	}

	@Test
	void addContaTrue1() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCDI(null, null), "");

		assertTrue(pessoa.addConta(new ContaCorrente(null)));
	}

	@Test
	void addContaTrue2() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCDI(null, null), "");

		assertTrue(pessoa.addConta(new ContaInvestimentoAutomatico(null, null)));
	}

	@Test
	void addContaTrue3() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaInvestimentoAutomatico(null, null), "");

		assertTrue(pessoa.addConta(new ContaCDI(null, null)));
	}

	@Test
	void addContaTrue4() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaInvestimentoAutomatico(null, null), "");

		assertTrue(pessoa.addConta(new ContaCorrente(null)));
	}

	@Test
	void addContaTrue5() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(null), "");

		assertTrue(pessoa.addConta(new ContaCDI(null, null)));
	}

	@Test
	void addContaTrue6() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(null), "");

		assertTrue(pessoa.addConta(new ContaInvestimentoAutomatico(null, null)));
	}

	@Test
	void addContaTrue7() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(null), "");

		assertTrue(pessoa.addConta(new ContaInvestimentoAutomatico(null, null)));
		assertTrue(pessoa.addConta(new ContaCDI(null, null)));
	}

	@Test
	void addContaTrueAndFalse() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(null), "");

		assertTrue(pessoa.addConta(new ContaInvestimentoAutomatico(null, null)));
		assertFalse(pessoa.addConta(new ContaInvestimentoAutomatico(null, null)));
	}

	@Test
	void addConta() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(null), "");

		assertTrue(pessoa.addConta(new ContaInvestimentoAutomatico(null, null)));
		assertFalse(pessoa.addConta(new ContaInvestimentoAutomatico(null, null)));
	}

	@Test
	void tiposDeContasNaoCadastradas1() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(null), "");

		List<Conta> verificarContasFaltantes = new ArrayList<>(Arrays.asList(new ContaCDI(null, null),
				new ContaCorrente(null), new ContaInvestimentoAutomatico(null, null)));

		var result = pessoa.tiposDeContasNaoCadastradas(verificarContasFaltantes);

		assertNotEquals(verificarContasFaltantes.size(), result.size());
	}

	@Test
	void tiposDeContasNaoCadastradas2() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(null), "");

		List<Conta> verificarContasFaltantes = new ArrayList<>(
				Arrays.asList(new ContaCDI(null, null), new ContaInvestimentoAutomatico(null, null)));

		var result = pessoa.tiposDeContasNaoCadastradas(verificarContasFaltantes);

		assertArrayEquals(verificarContasFaltantes.toArray(), result.toArray());
	}

	@Test
	void tiposDeContasNaoCadastradas3() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(null), "");

		List<Conta> verificarContasFaltantes = new ArrayList<>(Arrays.asList(new ContaCDI(null, null),
				new ContaCorrente(null), new ContaInvestimentoAutomatico(null, null)));

		var listResultExpected = Arrays.asList(new ContaCDI(null, null), new ContaInvestimentoAutomatico(null, null));

		var result = pessoa.tiposDeContasNaoCadastradas(verificarContasFaltantes);

		assertArrayEquals(listResultExpected.toArray(), result.toArray());
		assertEquals(listResultExpected.size(), result.size());
	}

	@Test
	void tiposDeContasNaoCadastradas4() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(null), "");
		pessoa.addConta(new ContaCDI(null, null));

		List<Conta> verificarContasFaltantes = new ArrayList<>(Arrays.asList(new ContaCDI(null, null),
				new ContaCorrente(null), new ContaInvestimentoAutomatico(null, null)));

		var listResultExpected = Arrays.asList(new ContaInvestimentoAutomatico(null, null));

		var result = pessoa.tiposDeContasNaoCadastradas(verificarContasFaltantes);

		assertArrayEquals(listResultExpected.toArray(), result.toArray());
		assertEquals(listResultExpected.size(), result.size());
	}

	@Test
	void tiposDeContasNaoCadastradas5() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(null), "");
		pessoa.addConta(new ContaCDI(null, null));
		pessoa.addConta(new ContaInvestimentoAutomatico(null, null));

		List<Conta> verificarContasFaltantes = new ArrayList<>(Arrays.asList(new ContaCDI(null, null),
				new ContaCorrente(null), new ContaInvestimentoAutomatico(null, null)));

		var listResultExpected = Arrays.asList();

		var result = pessoa.tiposDeContasNaoCadastradas(verificarContasFaltantes);

		assertArrayEquals(listResultExpected.toArray(), result.toArray());
		assertEquals(listResultExpected.size(), result.size());
	}

}
