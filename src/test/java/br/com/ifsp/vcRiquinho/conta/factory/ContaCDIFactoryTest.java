package br.com.ifsp.vcRiquinho.conta.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.ContaCdiFactory;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;

public class ContaCDIFactoryTest {

	@Test
	void givenCreateBy_whenDTOContaHasNoNullParameters_thenReturnContaCDI() {
		IFactoryConta factory = new ContaCdiFactory();

		Conta conta = factory.createBy(new DTOConta(0, "", 0.0, 0, 0.0, ""));
		assertEquals(ContaCDI.class, conta.getClass());
	}

	@Test
	void givenCreateBy_whenDTOContaHasNullParameters_thenReturnContaCDI() {
		IFactoryConta factory = new ContaCdiFactory();

		assertThrows(RuntimeException.class, () -> factory.createBy(new DTOConta(0, null, 0.0, 0, 0.0, "")));
	}

	@Test
	void givenContaCDI_whenConvert_thenReturnDTOConta() {
		IFactoryConta factory = new ContaCdiFactory();

		Conta conta = new ContaCDI(0, "", 0.0, 0.0);

		assertEquals(conta.getNumConta(), factory.convert(conta).numConta());
	}

}
