package br.com.ifsp.vcRiquinho.produto.repository;

import java.util.LinkedList;
import java.util.List;

import br.com.ifsp.vcRiquinho.base.interfaces.Repository;
import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProduto;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

public class RepositoryProduto {
	private EntityManagerFactory emf;

	public RepositoryProduto(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public Produto insert(DTOProduto dto) {
		try {
			Produto produto = createBy(dto);

			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			em.persist(produto);
			em.getTransaction().commit();

			return produto;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void update(DTOProduto dto) {
		try {
			Produto produto = createBy(dto);

			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			em.merge(produto);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Produto findBy(Integer id) {
		try {
			EntityManager em = emf.createEntityManager();
			return em.find(Produto.class, id);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void deleteBy(Integer id) {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em.createQuery("DELETE FROM Produto p WHERE p.id = :id");
			query.setParameter("id", id);
			query.executeUpdate();

			em.getTransaction().commit();
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<Produto> findAll() {
		try {
			List<Produto> produtos = new LinkedList<>();
		/*	List<DTOProduto> dtoProdutos = produtoDAO.findAll();

			for (DTOProduto dto : dtoProdutos) {
				Produto produto = createBy(dto);
				produtos.add(produto);
			}

			return produtos;
		*/

			return null;

		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private Produto createBy(DTOProduto dto) {
		return FactoryProduto.createBy(dto);
	}

}
