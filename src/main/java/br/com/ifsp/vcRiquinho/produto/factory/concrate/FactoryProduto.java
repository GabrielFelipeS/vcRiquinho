package br.com.ifsp.vcRiquinho.produto.factory.concrate;

import br.com.ifsp.vcRiquinho.base.exception.TypeNotFoundException;
import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaFixa;
import br.com.ifsp.vcRiquinho.produto.models.concrete.ProdutoRendaVariavel;

public class FactoryProduto {
	public static Produto createBy(DTOProduto dto) {
        return switch (dto.tipo_produto()) {
            case "renda_fixa" -> new ProdutoRendaFixa(dto.id(), dto.nome(), dto.descricao(), dto.rendimentoMensal(), dto.carencia());
            case "renda_variavel" -> new ProdutoRendaVariavel(dto.id(), dto.nome(),dto.descricao(), dto.rendimentoMensal());
            default -> throw new TypeNotFoundException("O tipo produto do DTO não pode ser encontrado");
        };
    }
}
