package br.com.ifsp.vcRiquinho.produto.repository;

import br.com.ifsp.vcRiquinho.base.interfaces.Repository;
import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public interface IRepositoryProduto  extends Repository<Produto, Integer, DTOProduto> {
	
}
