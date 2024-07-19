package br.com.ifsp.vcRiquinho.pessoa.service;


import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoaConta;

import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;
import br.com.ifsp.vcRiquinho.pessoa.repository.IRepositoryPessoa;
import br.com.ifsp.vcRiquinho.pessoa.repository.RepositoryPessoa;
import br.com.ifsp.vcRiquinho.usuario.dto.DTOUser;
import br.com.ifsp.vcRiquinho.usuario.service.HashPassword;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;


public class PessoaService {
	private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);

	private EntityManagerFactory emf;

	public PessoaService(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public Pessoa findBy(String document) {
		try  {
			RepositoryPessoa repository = new RepositoryPessoa(emf);
			
			return repository.findBy(document);
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e.fillInStackTrace());
			throw new RuntimeException(e.getMessage());
		} 
	}

	public Pessoa cadastrar(HttpServletRequest request) throws NoSuchAlgorithmException  {
		String hashedPassword = HashPassword.generate(request.getParameter("password"));
		DTOUser userDTO = new DTOUser(request.getParameter("email"), hashedPassword);
/*
		DTOPessoaConta dto = dtoFactory.createBy(request);

		try  {
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
 */
		return null;
	}
	




	public void deletar(String id) {
		/*IPessoaRepositoryFactory factory = new PessoaRepositoryFactory();

		try (Connection conn = connector.getConnection()){
			connector.disableAutoCommit();

			UserDAO userDAO = new UserDAO(conn);
			
			IRepositoryPessoa repository = factory.createBy(conn);
			String email = repository.findBy(id).getEmail();

			repository.deleteBy(id);
			
			userDAO.deleteBy(email);
			

			connector.commit();
		} catch (Exception e) {

			connector.rollback();
			throw new RuntimeException(e.getMessage());
		}

		 */

	}

	public void update(DTOPessoaConta dto) {
		/*
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
		 */

	}
}
