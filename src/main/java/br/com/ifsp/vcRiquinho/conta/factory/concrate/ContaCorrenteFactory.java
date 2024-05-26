package br.com.ifsp.vcRiquinho.conta.factory.concrate;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCorrente;

public class ContaCorrenteFactory implements IFactoryConta {

	@Override
	public Conta createBy(DTOConta dto) {
		return new ContaCorrente(dto.numConta(), dto.documentoTitular(), dto.montanteFinanceiro());
	}

	@Override
	public DTOConta convert(Conta contaToConvert) {
		ContaCorrente conta = (ContaCorrente) contaToConvert;
		return new DTOConta(conta);
	}
	
	@Override
	public String toString() {
		return "ContaCorrenteFactory";
	}
}
