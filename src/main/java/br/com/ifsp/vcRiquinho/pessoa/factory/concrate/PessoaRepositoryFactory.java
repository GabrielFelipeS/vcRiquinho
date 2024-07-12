package br.com.ifsp.vcRiquinho.pessoa.factory.concrate;

import java.sql.Connection;

import br.com.ifsp.vcRiquinho.conta.dao.ContaDAO;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.conta.repository.IRepositoryConta;
import br.com.ifsp.vcRiquinho.conta.repository.RepositoryConta;
import br.com.ifsp.vcRiquinho.pessoa.dao.PessoaDAO;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IPessoaRepositoryFactory;
import br.com.ifsp.vcRiquinho.pessoa.repository.IRepositoryPessoa;
import br.com.ifsp.vcRiquinho.pessoa.repository.RepositoryPessoa;
import br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.repository.RepositoryProduto;

public class PessoaRepositoryFactory implements IPessoaRepositoryFactory {

	@Override
	public IRepositoryPessoa createBy(Connection conn) {
		RepositoryProduto repositoryProduto = new RepositoryProduto(new ProdutoDAO(conn), new FactoryProdutoCreator());
	//	RepositoryConta repositoryConta = new RepositoryConta(new ContaDAO(conn), repositoryProduto,
		//		new FactoryContaCreatorProvider());
		IRepositoryConta repositoryConta =null;
		return new RepositoryPessoa(new PessoaDAO(conn), new FactoryPessoaCreatorProvider(), repositoryConta);
	}

}
