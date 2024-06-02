package br.com.ifsp.vcRiquinho.pessoa.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoaConta;
import br.com.ifsp.vcRiquinho.pessoa.factory.concrate.DTOPessoaContaFactory;
import br.com.ifsp.vcRiquinho.pessoa.factory.concrate.PessoaRepositoryFactory;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IDTOPessoaContaFactory;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IPessoaRepositoryFactory;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.pessoa.repository.IRepositoryPessoa;
import br.com.ifsp.vcRiquinho.usuario.dao.UserDAO;
import br.com.ifsp.vcRiquinho.usuario.dto.DTOUser;

public class PessoaService {
	private IDBConnector connector;

	public PessoaService() {
		connector = new ConnectionPostgress();
	}
	
	public Pessoa findBy(String id) {
		try (Connection conn = connector.getConnection()) {
			IPessoaRepositoryFactory factoryRepository = new PessoaRepositoryFactory();
			IRepositoryPessoa repository = factoryRepository.createBy(conn);
			
			return repository.findBy(id);
		} catch (RuntimeException | SQLException e) {
			throw new RuntimeException(e.getMessage());
		} 
	}

	public Pessoa cadastrar(HttpServletRequest request)  {
		DTOUser userDTO = new DTOUser(request.getParameter("email"), request.getParameter("email"));

		IDTOPessoaContaFactory dtoFactory = new DTOPessoaContaFactory();
		DTOPessoaConta dto = dtoFactory.createBy(request);

		try (Connection conn = connector.getConnection()) {
			connector.disableAutoCommit();

			IPessoaRepositoryFactory factoryRepository = new PessoaRepositoryFactory();
			IRepositoryPessoa repository = factoryRepository.createBy(conn);

			Pessoa pessoa = repository.insert(dto);

			UserDAO userDAO = new UserDAO(conn);
			userDAO.insert(userDTO);

			connector.commit();
			
			return pessoa;
		} catch (RuntimeException | SQLException e) {
			connector.rollback();
			
			throw new RuntimeException(e.getMessage());
		} 
	}

	public void deletar(String id) {
		IPessoaRepositoryFactory factory = new PessoaRepositoryFactory();

		try (Connection conn = connector.getConnection()){
			connector.disableAutoCommit();

			IRepositoryPessoa repository = factory.createBy(conn);
			System.out.println("ANTES DELETE");
			repository.deleteBy(id);
			System.out.println("DEPOIS DELETE");
			connector.commit();
		} catch (Exception e) {

			connector.rollback();
			throw new RuntimeException(e.getMessage());
		}
	}

	public void update(DTOPessoaConta dto) {
		IPessoaRepositoryFactory factory = new PessoaRepositoryFactory();

		try(Connection conn = connector.getConnection()) {
			connector.disableAutoCommit();

			IRepositoryPessoa repository = factory.createBy(conn);
			repository.update(dto);

			connector.commit();
		} catch (Exception e) {
			connector.rollback();
			
			throw new RuntimeException(e.getMessage());
		} 
	}
}
