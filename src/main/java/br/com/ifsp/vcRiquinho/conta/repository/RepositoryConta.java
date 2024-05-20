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
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public class RepositoryConta implements IRepositoryConta{
	private IContaDAO contaDAO;
	private IProdutoDAO produtoDAO;
	
	public RepositoryConta(IContaDAO contaDAO, IProdutoDAO produtoDAO) {
		this(contaDAO, produtoDAO, new FactoryProduto());
	}

	public RepositoryConta(IContaDAO contaDAO, IProdutoDAO produtoDAO, IFactoryProduto factoryProduto) {
		this.contaDAO = contaDAO;
		this.produtoDAO = produtoDAO;
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
			
			Produto produto = null;
			
			IFactoryContaCreator factoryCreator = new FactoryContaCreator(produto);
			IFactoryConta factory = factoryCreator.createBy(dtoConta.tipo_conta());
			
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
