package br.com.ifsp.vcRiquinho.usuario.dao;

import java.sql.Connection;
import java.util.List;

import br.com.ifsp.vcRiquinho.usuario.dto.DTOUser;

public class UserDAO implements IUserDAO{
	private Connection conn;
	
	public UserDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<DTOUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DTOUser insert(DTOUser dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteBy(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DTOUser findBy(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DTOUser update(DTOUser dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
