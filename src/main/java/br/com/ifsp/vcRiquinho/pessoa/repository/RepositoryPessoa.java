package br.com.ifsp.vcRiquinho.pessoa.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.repository.IRepositoryConta;
import br.com.ifsp.vcRiquinho.pessoa.dao.IPessoaDAO;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoaConta;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoa;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoaCreator;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoaCreatorProvider;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;

public class RepositoryPessoa implements IRepositoryPessoa {
	private IPessoaDAO pessoaDAO;
	private IFactoryPessoaCreatorProvider factoryPessoaCreatorProvider;
	private IRepositoryConta repositoryConta;

	public RepositoryPessoa(IPessoaDAO pessoaDAO, IFactoryPessoaCreatorProvider factoryPessoaCreatorProvider,
			IRepositoryConta repositoryConta) {
		
		this.pessoaDAO = pessoaDAO;
		this.factoryPessoaCreatorProvider = factoryPessoaCreatorProvider;
		this.repositoryConta = repositoryConta;
	}

	@Override
	public Pessoa insert(DTOPessoaConta dto) {
		try {
			DTOPessoa dtoPessoa = dto.dtoPessoa();
			dtoPessoa = pessoaDAO.insert(dtoPessoa);
			Set<DTOConta> dtoContas = dto.dtoContas();
			dtoContas.stream().forEach(repositoryConta::insert);			
			
			Set<Conta> contas = repositoryConta.findBy(dtoPessoa.documento_titular());
			
			return createBy(dtoPessoa, contas);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Pessoa update(DTOPessoaConta dto) {
		try {
			DTOPessoa dtoPessoa = dto.dtoPessoa();
			dtoPessoa = pessoaDAO.update(dtoPessoa);
			Set<Conta> contas = repositoryConta.findBy(dtoPessoa.documento_titular());
			
			return createBy(dtoPessoa, contas);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Pessoa findBy(String id) {
		try {
			DTOPessoa dto = pessoaDAO.findBy(id);
			Set<Conta> contas = repositoryConta.findBy(dto.documento_titular());
			
			return createBy(dto, contas);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public Pessoa findByEmail(String email) {
		try {
			DTOPessoa dto = pessoaDAO.findByEmail(email);
			Set<Conta> contas = repositoryConta.findBy(dto.documento_titular());
			
			return createBy(dto, contas);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	

	@Override
	public void deleteBy(String id) {
		try {
			pessoaDAO.deleteBy(id);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<Pessoa> findAll() {
		try {
			List<Pessoa> pessoas = new LinkedList<>();
			List<DTOPessoa> DTOPessoas = pessoaDAO.findAll();

			for (DTOPessoa dto : DTOPessoas) {
				Set<Conta> contas = repositoryConta.findBy(dto.documento_titular());
				Pessoa pessoa = createBy(dto, contas);
				
				pessoas.add(pessoa);
			}

			return pessoas;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private Pessoa createBy(DTOPessoa dto, Set<Conta> contas) {
		IFactoryPessoaCreator factoryCreator = factoryPessoaCreatorProvider.createBy(contas);
		IFactoryPessoa factory = factoryCreator.createBy(dto.tipo_pessoa());
		return factory.createBy(dto);
	}



}
