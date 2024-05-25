package br.com.ifsp.vcRiquinho;

import java.sql.Connection;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.conta.dao.ContaDAO;
import br.com.ifsp.vcRiquinho.conta.dao.IContaDAO;
import br.com.ifsp.vcRiquinho.conta.factory.concrate.FactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.conta.repository.IRepositoryConta;
import br.com.ifsp.vcRiquinho.conta.repository.RepositoryConta;
import br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.factory.interfaces.IFactoryContaCreatorProvider;
import br.com.ifsp.vcRiquinho.produto.repository.IRepositoryProduto;
import br.com.ifsp.vcRiquinho.produto.repository.RepositoryProduto;

public class Teset {
	public static void main(String[] args) {
		IDBConnector iDbConnector = new ConnectionPostgress();
		Connection connection = iDbConnector.getConnection();
		IContaDAO contaDAO = new ContaDAO(connection);
		
		IRepositoryProduto repositoryProduto = new RepositoryProduto(new ProdutoDAO(connection),new FactoryProdutoCreator());
		IFactoryContaCreatorProvider factoryContaCreatorProvider = new FactoryContaCreatorProvider();
		
		IRepositoryConta repository = new RepositoryConta(contaDAO, repositoryProduto, factoryContaCreatorProvider);
		for(Conta c : repository.findAll()) {
			System.out.println(c.getNumConta());
		}
	}
}
