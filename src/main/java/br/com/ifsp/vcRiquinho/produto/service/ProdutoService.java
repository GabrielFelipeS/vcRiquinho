package br.com.ifsp.vcRiquinho.produto.service;

import java.sql.Connection;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.produto.dao.ProdutoDAO;
import br.com.ifsp.vcRiquinho.produto.dto.DTOProduto;
import br.com.ifsp.vcRiquinho.produto.factory.concrate.FactoryProdutoCreator;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;
import br.com.ifsp.vcRiquinho.produto.repository.IRepositoryProduto;
import br.com.ifsp.vcRiquinho.produto.repository.RepositoryProduto;

public class ProdutoService {
	private IDBConnector connector;
	
	public ProdutoService() {
		connector = new ConnectionPostgress();
	}
	
	public Produto findBy(Integer id) {
		try(Connection conn = connector.getConnection()) {
			IRepositoryProduto repository = new RepositoryProduto(new ProdutoDAO(conn), new FactoryProdutoCreator());
			
		
			
			return repository.findBy(id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} 
	}

	public Produto cadastrar(DTOProduto dto)  {
		try(Connection conn = connector.getConnection()) {
			IRepositoryProduto repository = new RepositoryProduto(new ProdutoDAO(conn), new FactoryProdutoCreator());
			//connector.disableAutoCommit();
			
			//Integer id, Integer carencia, String tipo_produto, 
			//String nome, String descricao, Double rendimentoMensal
			
			Produto produto = repository.insert(dto);
			
			//connector.commit();
			
			return produto;
		} catch (Exception e) {
			//connector.rollback();
			throw new RuntimeException(e.getMessage());
		} 
	}

	public void deletar(Integer id) {
		try(Connection conn = connector.getConnection()) {
			IRepositoryProduto repository = new RepositoryProduto(new ProdutoDAO(conn), new FactoryProdutoCreator());
			repository.deleteBy(id);
			
		} catch (Exception e) {
			connector.rollback();
			throw new RuntimeException(e.getMessage());
		} 
	}

	public void update(DTOProduto dto) {
		try(Connection conn = connector.getConnection()) {
			IRepositoryProduto repository = new RepositoryProduto(new ProdutoDAO(conn), new FactoryProdutoCreator());
			repository.update(dto);
			
		} catch (Exception e) {
			connector.rollback();
			throw new RuntimeException(e.getMessage());
		} 
	}
}
