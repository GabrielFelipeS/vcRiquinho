package br.com.ifsp.vcRiquinho.usuario.dao;

import br.com.ifsp.vcRiquinho.base.interfaces.DAO;
import br.com.ifsp.vcRiquinho.usuario.dto.DTOUser;

public interface IUserDAO extends DAO<DTOUser, String> {
	Boolean loginValid(String email, String encryptedPassword);
}
