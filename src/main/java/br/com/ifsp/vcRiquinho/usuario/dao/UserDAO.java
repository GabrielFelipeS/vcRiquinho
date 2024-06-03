package br.com.ifsp.vcRiquinho.usuario.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

		try (PreparedStatement pst = conn.prepareStatement(
				"INSERT INTO users (email, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, dto.email());
			pst.setString(2, dto.hashPassword());
			pst.executeUpdate();

			try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return findBy(dto.email());
				} else {
					throw new SQLException("Falha na criação do usuário, nenhum ID obtido.");
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Boolean deleteBy(String email) {
		try (PreparedStatement pst = conn.prepareStatement("DELETE FROM users WHERE email = ?", Statement.RETURN_GENERATED_KEYS)){
			pst.setString(1, email);
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows != 1) {
				throw new RuntimeException("Ocorreu um erro ao tentar deletar usuário!");
			}
			
			
			return true;
		} catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public DTOUser findBy(String email) {
		try (PreparedStatement pst = conn.prepareStatement("SELECT * FROM users WHERE email = ? ")) {
			pst.setString(1, email);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return createDTOUser(rs);
				}
				throw new SQLException("Falha na busca do usuario, nenhuma encontrado.");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
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
	
	private DTOUser createDTOUser(ResultSet rs) throws SQLException {
		return new DTOUser(rs.getString("email"), rs.getString("password"));
	}

}
