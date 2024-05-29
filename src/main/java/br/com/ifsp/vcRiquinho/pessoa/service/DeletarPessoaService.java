package br.com.ifsp.vcRiquinho.pessoa.service;

import java.sql.Connection;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.pessoa.factory.concrate.PessoaRepositoryFactory;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IPessoaRepositoryFactory;
import br.com.ifsp.vcRiquinho.pessoa.repository.IRepositoryPessoa;

public class DeletarPessoaService {
	public void deletar(String id) {
		IDBConnector connector = new ConnectionPostgress();
		Connection conn = connector.getConnection();
		
		IPessoaRepositoryFactory factory = new PessoaRepositoryFactory();
		
		try {
			connector.disableAutoCommit();
			
			IRepositoryPessoa repository = factory.createBy(conn);
			repository.deleteBy(id);
			
			connector.commit();
		} catch (Exception e) {
			
			connector.rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			connector.closeConnection();
		}
	}
}
