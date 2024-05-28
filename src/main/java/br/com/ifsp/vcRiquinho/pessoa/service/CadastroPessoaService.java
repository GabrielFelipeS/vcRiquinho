package br.com.ifsp.vcRiquinho.pessoa.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.db.interfaces.IDBConnector;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoaConta;
import br.com.ifsp.vcRiquinho.pessoa.factory.concrate.DTOPessoaContaFactory;
import br.com.ifsp.vcRiquinho.pessoa.factory.concrate.PessoaRepositoryFactory;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IDTOPessoaContaFactory;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IPessoaRepositoryFactory;
import br.com.ifsp.vcRiquinho.pessoa.repository.IRepositoryPessoa;
import br.com.ifsp.vcRiquinho.usuario.dao.UserDAO;
import br.com.ifsp.vcRiquinho.usuario.dto.DTOUser;

public class CadastroPessoaService {
	 private static final Logger logger = LoggerFactory.getLogger(CadastroPessoaService.class);
	
	public void cadastrar(HttpServletRequest request) throws Exception {
		DTOUser userDTO = new DTOUser(request.getParameter("email"), request.getParameter("email"));
		
		IDTOPessoaContaFactory dtoFactory = new DTOPessoaContaFactory();
		DTOPessoaConta dto = dtoFactory.createBy(request);

		IDBConnector connector = new ConnectionPostgress();

		try (Connection conn = connector.getConnection()){
			connector.disableAutoCommit();
			
			IPessoaRepositoryFactory factoryRepository = new PessoaRepositoryFactory();
			IRepositoryPessoa repository = factoryRepository.createBy(conn);
			
			repository.insert(dto);
			
			UserDAO userDAO = new UserDAO(conn);
			userDAO.insert(userDTO);
			
			connector.commit();
		} catch (RuntimeException | SQLException e) {
			connector.rollback();
			System.out.println(e.getMessage());
			logger.error("Erro ao inserir pessoa", e);
			throw e;
		} finally {
			connector.closeConnection();
		}
	}
}
