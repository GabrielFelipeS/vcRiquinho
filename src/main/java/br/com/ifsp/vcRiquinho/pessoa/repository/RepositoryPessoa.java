package br.com.ifsp.vcRiquinho.pessoa.repository;

import java.util.LinkedList;
import java.util.List;

import br.com.ifsp.vcRiquinho.pessoa.dao.IPessoaDAO;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoa;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoaCreator;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProduto;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryProdutoCreator;

public class RepositoryPessoa implements IRepositoryPessoa {
	private IPessoaDAO pessoaDAO;
	private IFactoryPessoaCreator factoryCreator;

	public RepositoryPessoa(IPessoaDAO pessoaDAO, IFactoryPessoaCreator factoryCreator) {
		this.pessoaDAO = pessoaDAO;
		this.factoryCreator = factoryCreator;
	}

	@Override
	public Pessoa insert(DTOPessoa dto) {
		try {
			dto = pessoaDAO.insert(dto);

			return null;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Pessoa update(DTOPessoa dto) {
		try {
			dto = pessoaDAO.update(dto);

			return createBy(dto);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Pessoa findBy(String id) {
		try {
			DTOPessoa dto = pessoaDAO.findBy(id);
			
			return createBy(dto);
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void deleteBy(String id) {
		try {
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
				Pessoa pessoa = createBy(dto);
				pessoas.add(pessoa);
			}

			return pessoas;
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private Pessoa createBy(DTOPessoa dto) {
		IFactoryPessoa factory = factoryCreator.createBy(dto.tipo_pessoa());
		return factory.createBy(dto);
	}

}
