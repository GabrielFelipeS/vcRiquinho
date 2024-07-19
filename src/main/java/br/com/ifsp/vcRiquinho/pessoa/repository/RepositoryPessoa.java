package br.com.ifsp.vcRiquinho.pessoa.repository;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.factory.FactoryConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.repository.RepositoryConta;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoaConta;
import br.com.ifsp.vcRiquinho.pessoa.factory.FactoryPessoa;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RepositoryPessoa {
	private EntityManagerFactory emf;
	private RepositoryConta repositoryConta;


	public RepositoryPessoa(EntityManagerFactory emf) {
		this(emf, new RepositoryConta(emf));
	}

	public RepositoryPessoa(EntityManagerFactory emf, RepositoryConta repositoryConta) {
		this.emf = emf;
		this.repositoryConta = repositoryConta;
	}

	public Pessoa insert(DTOPessoaConta dto) {
		try (EntityManager em = emf.createEntityManager()){

			RepositoryConta repository = new RepositoryConta(emf);

			Set<DTOConta> dtoContas = dto.dtoContas();
			Set<Conta> contas = dtoContas.stream().map(dtoConta -> createConta(dtoConta, em)).collect(Collectors.toSet());

			Pessoa pessoa = this.createBy(dto.dtoPessoa(), contas);

			em.getTransaction().begin();
			em.persist(pessoa);
			em.getTransaction().commit();

			return pessoa;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Pessoa update(Pessoa pessoa) {
		try (EntityManager em = emf.createEntityManager()){
			em.getTransaction().begin();
			pessoa = em.merge(pessoa);
			em.getTransaction().commit();
			return pessoa;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Pessoa findBy(Integer id) {
		try (EntityManager em = emf.createEntityManager()){
			return em.find(Pessoa.class, id);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public Pessoa findBy(String document) {
		try (EntityManager em = emf.createEntityManager()){

			TypedQuery<Pessoa> query = em.createNamedQuery("findByDocument", Pessoa.class);
			query.setParameter("document" ,document);

			return query.getSingleResult();
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Pessoa findByEmail(String email) {
		try {
		//	DTOPessoa dto = pessoaDAO.findByEmail(email);
		//	Set<Conta> contas = repositoryConta.findBy(dto.documento_titular());
			
		//	return createBy(dto, contas);
			return null;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	

	public void deleteBy(String document) {
		try (EntityManager em = emf.createEntityManager()){
			repositoryConta.deleteBy(document);

			em.getTransaction().begin();
			Pessoa pessoa = em.merge(this.findBy(document));
			em.remove(pessoa);
			em.getTransaction().commit();

			} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<Pessoa> findAll() {
		try {
			List<Pessoa> pessoas = new LinkedList<>();
			//List<DTOPessoa> DTOPessoas = pessoaDAO.findAll();

			//for (DTOPessoa dto : DTOPessoas) {
			//	Set<Conta> contas = repositoryConta.findBy(dto.documento_titular());
			//	Pessoa pessoa = createBy(dto, contas);
				
			//	pessoas.add(pessoa);
			//}

			//return pessoas;
			return null;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private Pessoa createBy(DTOPessoa dto, Set<Conta> contas) {
		return FactoryPessoa.createBy(dto, contas);
	}

	private Conta createConta(DTOConta dtoConta, EntityManager em) {
		Produto produto = dtoConta.id_produto() != null?
				em.find(Produto.class, dtoConta.id_produto()) : null;
		System.out.println(dtoConta.id_produto() + " " + dtoConta.tipo_conta());
		return FactoryConta.createBy(dtoConta, produto);
	}
}
