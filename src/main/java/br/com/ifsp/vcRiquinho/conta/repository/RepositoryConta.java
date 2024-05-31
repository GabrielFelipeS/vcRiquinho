package br.com.ifsp.vcRiquinho.conta.repository;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.dao.IContaDAO;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryConta;
import br.com.ifsp.vcRiquinho.conta.factory.interfaces.IFactoryContaCreator;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import br.com.ifsp.vcRiquinho.produto.repository.IRepositoryProduto;

public class RepositoryConta implements IRepositoryConta {
	private IContaDAO contaDAO;
	private IRepositoryProduto repositoryProduto;
	private IFactoryContaCreatorProvider factoryContaCreatorProvider;

	public RepositoryConta(IContaDAO contaDAO, IRepositoryProduto repositoryProduto,
			IFactoryContaCreatorProvider factoryContaCreatorProvider) {
		this.contaDAO = contaDAO;
		this.repositoryProduto = repositoryProduto;
		this.factoryContaCreatorProvider = factoryContaCreatorProvider;
	}

	@Override
	public Conta insert(DTOConta dto) {
		try {
			dto = contaDAO.insert(dto);
			Produto produto = repositoryProduto.findBy(dto.id_produto());

			return createContaBy(dto, produto);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Conta update(DTOConta dto) {
		try {
			dto = contaDAO.update(dto);
			Produto produto = repositoryProduto.findBy(dto.id_produto());

			return createContaBy(dto, produto);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Conta findBy(Integer id) {
		try {
			DTOConta dtoConta = contaDAO.findBy(id);
			Produto produto = repositoryProduto.findBy(dtoConta.id_produto());

			return createContaBy(dtoConta, produto);

		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Set<Conta> findBy(String documentoTitular) {
		try {
			Set<Conta> contas = new HashSet<>();

			Set<DTOConta> dtoContas = contaDAO.findByDocument(documentoTitular);
			for (DTOConta dto : dtoContas) {
				Produto produto = repositoryProduto.findBy(dto.id_produto());
				Conta conta = createContaBy(dto, produto);
				contas.add(conta);
			}
			
			return contas;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void deleteBy(Integer id) {
		try {
			contaDAO.deleteBy(id);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<Conta> findAll() {
		try {
			List<Conta> contas = new LinkedList<>();
			List<DTOConta> dtosContas = contaDAO.findAll();

			for (DTOConta dto : dtosContas) {
				Produto produto = repositoryProduto.findBy(dto.id_produto());
				Conta conta = createContaBy(dto, produto);

				contas.add(conta);
			}

			return contas;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private Conta createContaBy(DTOConta dtoConta, Produto produto) {
		IFactoryContaCreator factoryContaCreator = factoryContaCreatorProvider.createBy(produto);
		IFactoryConta factory = factoryContaCreator.createBy(dtoConta.tipo_conta());

		return factory.createBy(dtoConta);
	}
}
