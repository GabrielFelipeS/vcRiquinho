package br.com.ifsp.vcRiquinho.conta.factory;

import br.com.ifsp.vcRiquinho.base.exception.TypeNotFoundException;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCDI;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaCorrente;
import br.com.ifsp.vcRiquinho.conta.models.concrate.ContaInvestimentoAutomatico;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public class FactoryConta {

	public static Conta createBy(DTOConta dto, Produto produto) {
		return switch (dto.tipo_conta()) {
			case "cdi" -> new ContaCDI(dto.numConta(), dto.documentoTitular(), dto.montanteFinanceiro(), dto.cdi());
			case "corrente" -> new ContaCorrente(dto.numConta(), dto.documentoTitular(), dto.montanteFinanceiro());
			case "investimento_automatico" -> new ContaInvestimentoAutomatico(dto.numConta(), dto.documentoTitular(), dto.montanteFinanceiro(), produto);
			default -> throw new TypeNotFoundException("O tipo conta do DTO não pode ser encontrado");
		};
	}
}
