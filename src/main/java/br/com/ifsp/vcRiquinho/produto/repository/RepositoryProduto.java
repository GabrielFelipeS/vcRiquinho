package br.com.ifsp.vcRiquinho.produto.repository;

import java.util.LinkedList;
import java.util.List;

import br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProduto;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public class RepositoryProduto implements IRepositoryProduto {
	private ProdutoDAO produtoDAO;
	private IFactoryProdutoCreator factoryProdutoCreator;

	public RepositoryProduto(ProdutoDAO produtoDAO, IFactoryProdutoCreator factoryProdutoCreator) {
		this.produtoDAO = produtoDAO;
		this.factoryProdutoCreator = factoryProdutoCreator;
	}

	@Override
	public Produto insert(DTOProduto dto) {
		try {
			dto = produtoDAO.insert(dto);

			return createBy(dto);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Produto update(DTOProduto dto) {
		try {
			dto = produtoDAO.update(dto);
			
			return createBy(dto);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Produto findBy(Integer id) {
		try {
			DTOProduto dtoProduto = produtoDAO.findBy(id);
			Produto produto = createBy(dtoProduto);

			return produto;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void deleteBy(Integer id) {
		try {
			produtoDAO.deleteBy(id);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<Produto> getAll() {
		try {
			List<Produto> produtos = new LinkedList<>();
			List<DTOProduto> dtoProdutos = produtoDAO.findAll();

			for (DTOProduto dto : dtoProdutos) {
				Produto produto = createBy(dto);
				produtos.add(produto);
			}

			return produtos;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private Produto createBy(DTOProduto dto) {
		IFactoryProduto factoryProduto = factoryProdutoCreator.createBy(dto.tipo_produto());
		return factoryProduto.createBy(dto);
	}

}
