package br.com.ifsp.vcRiquinho.pessoa.models.concrate;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.pessoa.exceptions.ZeroContasException;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaFisica;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaJuridica;
import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaFixa;

class PessoaTest {

	@Test
	void validarRequerimentoDeNaoNuloPessoa1() {
		assertThrows("Nome não pode ser nulo", NullPointerException.class,
				() -> new PessoaFisica(null, "", new ContaCDI(0, "", 0.0, 0.0), ""));
	}

	@Test
	void validarRequerimentoDeNaoNuloPessoa2() {
		assertThrows("Email não pode ser nulo", NullPointerException.class,
				() -> new PessoaFisica("", null, new ContaCDI(0, "", 0.0, 0.0), ""));
	}

	@Test
	void validarRequerimentoDeNaoNuloPessoaFisica() {
		assertThrows("cpf não pode ser nulo", NullPointerException.class,
				() -> new PessoaFisica("", "", new ContaCDI(0, "", 0.0, 0.0), null));
	}

	@Test
	void validarRequerimentoDeNaoNuloPessoaJuridica() {
		assertThrows("cnpj não pode ser nulo", NullPointerException.class,
				() -> new PessoaJuridica("", "", new ContaCDI(0, "", 0.0, 0.0), null));
	}

	@Test
	void createPessoaFisicaTest1() {
		assertDoesNotThrow(() -> new PessoaFisica("", "", new ContaCDI(0, "", 0.0, 0.0), ""));
	}

	@Test
	void createPessoaFisicaTest2() {
		assertDoesNotThrow(
				() -> new PessoaFisica("", "", new HashSet<Conta>(Arrays.asList(new ContaCDI(0, "", 0.0, 0.0))), ""));
	}

	@Test
	void createPessoaJuridicaTest1() {
		assertDoesNotThrow(() -> new PessoaJuridica("", "", new ContaCDI(0, "", 0.0, 0.0), ""));
	}

	@Test
	void createPessoaJuridicaTest2() {
		assertDoesNotThrow(
				() -> new PessoaJuridica("", "", new HashSet<Conta>(Arrays.asList(new ContaCDI(0, "", 0.0, 0.0))), ""));
	}

	@Test
	void createPessoaFisicaZeroContasExceptionTest() {
		assertThrows(ZeroContasException.class, () -> new PessoaFisica("", "", new HashSet<Conta>(), ""));
	}

	@Test
	void createPessoaJuridicaZeroContasExceptionTest() {
		assertThrows(ZeroContasException.class, () -> new PessoaJuridica("", "", new HashSet<Conta>(), ""));
	}

	@Test
	void addContaFalse1() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCDI(0, "", 0.0, 0.0), "");

		assertFalse(pessoa.addConta(new ContaCDI(0, "", 0.0, 0.0)));
	}

	@Test
	void addContaFalse2() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(0, "", 0.0), "");

		assertFalse(pessoa.addConta(new ContaCorrente(0, "", 0.0)));
	}

	@Test
	void addContaFalse3() {
		Pessoa pessoa = new PessoaJuridica("", "",
				new ContaInvestimentoAutomatico(00, "", .0, new ProdutoRendaFixa(0.0, 0)), "");

		assertFalse(pessoa.addConta(new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0))));
	}

	@Test
	void addContaTrue1() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCDI(0, "", 0.0, 0.0), "");

		assertTrue(pessoa.addConta(new ContaCorrente(0, "", 0.0)));
	}

	@Test
	void addContaTrue2() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCDI(0, "", 0.0, 0.0), "");

		assertTrue(pessoa.addConta(new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0))));
	}

	@Test
	void addContaTrue3() {
		Pessoa pessoa = new PessoaJuridica("", "",
				new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0)), "");

		assertTrue(pessoa.addConta(new ContaCDI(0, "", 0.0, 0.0)));
	}

	@Test
	void addContaTrue4() {
		Pessoa pessoa = new PessoaJuridica("", "",
				new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0)), "");

		assertTrue(pessoa.addConta(new ContaCorrente(0, "", 0.0)));
	}

	@Test
	void addContaTrue5() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(0, "", 0.0), "");

		assertTrue(pessoa.addConta(new ContaCDI(0, "", 0.0, 0.0)));
	}

	@Test
	void addContaTrue6() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(0, "", 0.0), "");

		assertTrue(pessoa.addConta(new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0))));
	}

	@Test
	void addContaTrue7() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(0, "", 0.0), "");

		assertTrue(pessoa.addConta(new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0))));
		assertTrue(pessoa.addConta(new ContaCDI(0, "", 0.0, 0.0)));
	}

	@Test
	void addContaTrueAndFalse() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(0, "", 0.0), "");

		assertTrue(pessoa.addConta(new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0))));
		assertFalse(pessoa.addConta(new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0))));
	}

	@Test
	void addConta() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(0, "", 0.0), "");

		assertTrue(pessoa.addConta(new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0))));
		assertFalse(pessoa.addConta(new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0))));
	}

	@Test
	void tiposDeContasNaoCadastradas1() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(0, "", 0.0), "");

		List<Conta> verificarContasFaltantes = new ArrayList<>(
				Arrays.asList(new ContaCDI(0, "", 0.0, 0.0), new ContaCorrente(0, "", 0.0),
						new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0))));

		var result = pessoa.verificarContasNaoCadastradas(verificarContasFaltantes);

		assertNotEquals(verificarContasFaltantes.size(), result.size());
	}

	@Test
	void tiposDeContasNaoCadastradas2() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(0, "", 0.0), "");

		List<Conta> verificarContasFaltantes = new ArrayList<>(Arrays.asList(new ContaCDI(0, "", 0.0, 0.0),
				new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0))));

		var result = pessoa.verificarContasNaoCadastradas(verificarContasFaltantes);

		assertArrayEquals(verificarContasFaltantes.toArray(), result.toArray());
	}

	@Test
	void tiposDeContasNaoCadastradas3() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(0, "", 0.0), "");

		List<Conta> verificarContasFaltantes = new ArrayList<>(
				Arrays.asList(new ContaCDI(0, "", 0.0, 0.0), new ContaCorrente(0, "", 0.0),
						new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0))));

		var listResultExpected = Arrays.asList(new ContaCDI(0, "", 0.0, 0.0),
				new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0)));

		var result = pessoa.verificarContasNaoCadastradas(verificarContasFaltantes);

		assertArrayEquals(listResultExpected.toArray(), result.toArray());
		assertEquals(listResultExpected.size(), result.size());
	}

	@Test
	void tiposDeContasNaoCadastradas4() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(0, "", 0.0), "");
		pessoa.addConta(new ContaCDI(0, "", 0.0, 0.0));

		List<Conta> verificarContasFaltantes = new ArrayList<>(
				Arrays.asList(new ContaCDI(0, "", 0.0, 0.0), new ContaCorrente(0, "", 0.0),
						new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0))));

		var listResultExpected = Arrays
				.asList(new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0)));

		var result = pessoa.verificarContasNaoCadastradas(verificarContasFaltantes);

		assertArrayEquals(listResultExpected.toArray(), result.toArray());
		assertEquals(listResultExpected.size(), result.size());
	}

	@Test
	void tiposDeContasNaoCadastradas5() {
		Pessoa pessoa = new PessoaJuridica("", "", new ContaCorrente(0, "", 0.0), "");
		pessoa.addConta(new ContaCDI(0, "", 0.0, 0.0));
		pessoa.addConta(new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0)));

		List<Conta> verificarContasFaltantes = new ArrayList<>(
				Arrays.asList(new ContaCDI(0, "", 0.0, 0.0), new ContaCorrente(0, "", 0.0),
						new ContaInvestimentoAutomatico(0, "", 0.0, new ProdutoRendaFixa(0.0, 0))));

		var listResultExpected = Arrays.asList();

		var result = pessoa.verificarContasNaoCadastradas(verificarContasFaltantes);

		assertArrayEquals(listResultExpected.toArray(), result.toArray());
		assertEquals(listResultExpected.size(), result.size());
	}

}
