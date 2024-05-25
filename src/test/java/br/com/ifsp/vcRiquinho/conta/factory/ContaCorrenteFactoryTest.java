package br.com.ifsp.vcRiquinho.conta.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.ContaCorrenteFactory;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCorrente;

public class ContaCorrenteFactoryTest {

	@Test
	void createContaCorrente() {
		IFactoryConta factory = new ContaCorrenteFactory();

		Conta conta = factory.createBy(new DTOConta(0, "", 0.0, 0, 0.0, ""));
		assertEquals(ContaCorrente.class, conta.getClass());
	}

	@Test
	void createContaCorrenteAlgumValorNulo() {
		IFactoryConta factory = new ContaCorrenteFactory();

		assertThrows(RuntimeException.class, () -> factory.createBy(new DTOConta(0, null, 0.0, 0, 0.0, "")));
	}

	@Test
	void convertContaCorrente() {
		IFactoryConta factory = new ContaCorrenteFactory();

		Conta conta = new ContaCorrente(0, "", 0.0);

		assertEquals(conta.getNumConta(), factory.convert(conta).numConta());
	}

}
