package br.com.ifsp.vcRiquinho.conta.repository;

import java.util.List;

import br.com.ifsp.vcRiquinho.conta.dao.IContaDAO;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreator;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryConta;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryContaCreator;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.produto.dao.IProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProduto;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public class RepositoryConta implements IRepositoryConta{
	private IContaDAO contaDAO;
	private IProdutoDAO produtoDAO;
	private IFactoryProdutoCreator factoryProdutoCreator;
	
	public RepositoryConta(IContaDAO contaDAO, IProdutoDAO produtoDAO) {
		this(contaDAO, produtoDAO, new FactoryProdutoCreator());
	}

	public RepositoryConta(IContaDAO contaDAO, IProdutoDAO produtoDAO, IFactoryProdutoCreator factoryProdutoCreator) {
		this.contaDAO = contaDAO;
		this.produtoDAO = produtoDAO;
		this.factoryProdutoCreator = factoryProdutoCreator;
	}
	
	
	@Override
	public Conta add(DTOConta obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conta update(DTOConta obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conta get(Integer id) {
		try {
			DTOConta dtoConta = contaDAO.findBy(id);
			DTOProduto dtoProduto = produtoDAO.findBy(dtoConta.id_produto());
		
			IFactoryProduto factoryProduto = factoryProdutoCreator.createBy(dtoProduto.tipo_produto());
			Produto produto = factoryProduto.createBy(dtoProduto);
			
			IFactoryContaCreator factoryContaCreator = new FactoryContaCreator(produto);
			IFactoryConta factory = factoryContaCreator.createBy(dtoConta.tipo_conta());
			
			return factory.createBy(dtoConta);
			
		} catch(RuntimeException e) {
			
		}
		
		
		return null;
	}

	@Override
	public Conta delete(Integer obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Conta> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
