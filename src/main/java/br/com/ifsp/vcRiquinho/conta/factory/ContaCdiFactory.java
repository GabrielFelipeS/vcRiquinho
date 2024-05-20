package br.com.ifsp.vcRiquinho.conta.factory;

import br.com.ifsp.vcRiquinho.base.interfaces.Factory;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;

public class ContaCdiFactory implements  Factory<DTOConta, Conta> {

	@Override
	public Conta createBy(DTOConta dto) {
		return new ContaCDI(dto.id(), dto.documentoTitular(), dto.montanteFinanceiro(), dto.cdi());
	}

	@Override
	public DTOConta convert(Conta contaToConvert) {
		ContaCDI conta = (ContaCDI) contaToConvert;
		return new DTOConta(conta);
	}
}
