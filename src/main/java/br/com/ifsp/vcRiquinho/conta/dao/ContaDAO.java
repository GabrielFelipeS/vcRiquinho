package br.com.ifsp.vcRiquinho.conta.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import br.com.ifsp.vcRiquinho.base.db.implementation.ConnectionPostgress;
import br.com.ifsp.vcRiquinho.base.interfaces.DAO;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;

public class ContaDAO implements DAO<Conta, String> {
	private Connection conn;
	
	public ContaDAO(Connection conn) {
		this.conn = conn;
	}
	
	ContaDAO() {
		this.conn = new ConnectionPostgress().getConnection();
	}
	
	@Override
	public List<Conta> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insert(Conta obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteBy(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conta findBy(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Conta> findOptionalBy(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Conta updateBy(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Conta> updateOptionalBy(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
