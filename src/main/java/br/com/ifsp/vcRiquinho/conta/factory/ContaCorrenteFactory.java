package br.com.ifsp.vcRiquinho.conta.factory;

import br.com.ifsp.vcRiquinho.base.interfaces.Factory;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCorrente;

public class ContaCorrenteFactory implements Factory<DTOConta, Conta> {

	@Override
	public Conta createBy(DTOConta dto) {
		return new ContaCorrente(dto.id(), dto.documentoTitular(), dto.montanteFinanceiro());
	}

	@Override
	public DTOConta convert(Conta contaToConvert) {
		ContaCorrente conta = (ContaCorrente) contaToConvert;
		return new DTOConta(conta);
	}
}
