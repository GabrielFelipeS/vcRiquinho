package br.com.ifsp.vcRiquinho.conta.factory.concrate;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;

public class ContaCdiFactory implements IFactoryConta {

	@Override
	public Conta createBy(DTOConta dto) {
		return new ContaCDI(dto.numConta(), dto.documentoTitular(), dto.montanteFinanceiro(), dto.cdi());
	}

	@Override
	public DTOConta convert(Conta contaToConvert) {
		ContaCDI conta = (ContaCDI) contaToConvert;
		return new DTOConta(conta);
	}
	
	@Override
	public String toString() {
		return "ContaCdiFactory";
	}
}
