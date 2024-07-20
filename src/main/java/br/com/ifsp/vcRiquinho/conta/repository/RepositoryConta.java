package br.com.ifsp.vcRiquinho.conta.repository;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.FactoryConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import br.com.ifsp.vcRiquinho.produto.models.concrete.NullObjectProduto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RepositoryConta {
	private EntityManagerFactory emf;

	public RepositoryConta(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public Conta insert(Conta conta) {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(conta);
			em.getTransaction().commit();
			em.close();

			return conta;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}


	public Conta insert(DTOConta dto) {
		try {
			EntityManager em = emf.createEntityManager();

			Produto produto = dto.id_produto() != null ?
					em.find(Produto.class, dto.id_produto()) :
					new NullObjectProduto() ;
			System.out.println("TESTE");
			Conta conta = createContaBy(dto, produto);
			System.out.println("TESTE");
			em.close();

			return this.insert(conta);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void insert(Set<Conta> contas) {
		for(Conta conta : contas) {
			this.insert(conta);
		}
	}

	public Conta update(DTOConta dto) {
		try {
			EntityManager em = emf.createEntityManager();
			System.out.println("TESTE");
			Produto produto = dto.id_produto() != null ?
					em.find(Produto.class, dto.id_produto())
					: null;
			System.out.println("TESTE");
			Conta conta = createContaBy(dto, produto);
			System.out.println("TESTE");
			em.getTransaction().begin();
			em.merge(conta);
			em.getTransaction().commit();

			return conta;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Conta findBy(Integer id) {
		try {
			EntityManager em = emf.createEntityManager();
			Conta conta = em.find(Conta.class, id);
			em.close();

			return conta;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

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

	public void deleteBy(String document) {
		try {
			Set<Conta> contas = this.findBy(document);

			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			Conta conta1 = em.find(Conta.class, 9);
			em.remove(conta1);

			Conta conta2 = em.find(Conta.class, 10);
			em.remove(conta2);

			Conta conta3 = em.find(Conta.class, 11);
			em.remove(conta3);

			em.getTransaction().commit();

		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void deleteBy(Integer id) {
		try {
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			Query query = em.createNamedQuery("Conta.deletarContaPorID");
			query.setParameter("id", id);
			query.executeUpdate();

			em.getTransaction().commit();

		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

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
		return FactoryConta.createBy(dtoConta, produto);
	}


}
