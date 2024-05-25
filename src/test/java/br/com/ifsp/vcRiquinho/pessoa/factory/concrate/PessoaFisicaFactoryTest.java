package br.com.ifsp.vcRiquinho.pessoa.factory.concrate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoa;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.pessoa.models.concrate.PessoaFisica;

public class PessoaFisicaFactoryTest {

	@Test
	void createByPessoaFisicaComUmaConta() {
		Set<Conta> contas = Set.of(new ContaCorrente(0, "", 0.0));
		IFactoryPessoa factory = new PessoaFisicaFactory(contas);

		Pessoa pessoa = factory.createBy(new DTOPessoa(0, "", "", "", ""));
		assertEquals(PessoaFisica.class, pessoa.getClass());
	}

	@Test
	void createByPessoaFisicaErroValoresNulos() {
		IFactoryPessoa factory = new PessoaFisicaFactory(new HashSet<>());

		assertThrows(RuntimeException.class, () -> factory.createBy(new DTOPessoa(null, null, null, null, null)));
	}

	@Test
	void createByPessoaFisicaErroZeroContas() {
		HashSet<Conta> contas = new HashSet<>();
		IFactoryPessoa factory = new PessoaFisicaFactory(contas);

		assertThrows(RuntimeException.class, () -> factory.createBy(new DTOPessoa(0, "", "", "", "")));
	}

	@Test
	void convertPessoaFisica() {
		Set<Conta> conta = Set.of(new ContaCorrente(0, "", 0.0));
		IFactoryPessoa factory = new PessoaFisicaFactory(conta);

		Pessoa pessoa = new PessoaFisica(0, "", "", conta, "");

		assertEquals(pessoa.getId(), factory.convert(pessoa).id());
	}
}
