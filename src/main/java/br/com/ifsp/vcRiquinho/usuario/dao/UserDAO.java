package br.com.ifsp.vcRiquinho.usuario.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
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

	@Override
	public Boolean loginValid(String email, String encryptedPassword) {
		try (PreparedStatement pst = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?")) {
			pst.setString(1, email);
			pst.setString(2, encryptedPassword);
			
			try (ResultSet rs = pst.executeQuery()) {
				return rs.next();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	

}
