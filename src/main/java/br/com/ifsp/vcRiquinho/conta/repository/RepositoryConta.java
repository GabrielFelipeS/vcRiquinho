package br.com.ifsp.vcRiquinho.conta.repository;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreator;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import jakarta.persistence.EntityManagerFactory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RepositoryConta implements IRepositoryConta {
	private EntityManagerFactory emf;

	public RepositoryConta(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Conta insert(DTOConta dto) {
		try {
			// dto = contaDAO.insert(dto);
			//Produto produto = repositoryProduto.findBy(dto.id_produto());

		//	return createContaBy(dto, produto);
			return null;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Conta update(DTOConta dto) {
		try {
			//dto = contaDAO.update(dto);
		//	Produto produto = repositoryProduto.findBy(dto.id_produto());

			return createContaBy(dto, null);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Conta findBy(Integer id) {
		try {
		//	DTOConta dtoConta = contaDAO.findBy(id);
		//	Produto produto = repositoryProduto.findBy(dtoConta.id_produto());

			return createContaBy(null, null);

		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Set<Conta> findBy(String documentoTitular) {
		try {
			Set<Conta> contas = new HashSet<>();

			//Set<DTOConta> dtoContas = contaDAO.findByDocument(documentoTitular);
		//	for (DTOConta dto : dtoContas) {
			//	Produto produto = repositoryProduto.findBy(dto.id_produto());
			//	Conta conta = createContaBy(dto, produto);
		//		contas.add(conta);
		//	}
			
			return contas;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void deleteBy(Integer id) {
		try {
		//	contaDAO.deleteBy(id);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<Conta> findAll() {
		try {
			List<Conta> contas = new LinkedList<>();
		//	List<DTOConta> dtosContas = contaDAO.findAll();

		//	for (DTOConta dto : dtosContas) {
			//	Produto produto = repositoryProduto.findBy(dto.id_produto());
		//		Conta conta = createContaBy(dto, produto);

		//		contas.add(conta);
		//	}

			return contas;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<String> findMissingTypeAccounts(String documentoTitular) {
		try {
			//List<String> missingFindTypeAccounts = contaDAO.findMissingTypeAccounts(documentoTitular);
		//	return missingFindTypeAccounts;
			return null;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private Conta createContaBy(DTOConta dtoConta, Produto produto) {
		return FactoryContaCreator.createBy(dtoConta, produto);
	}
}
