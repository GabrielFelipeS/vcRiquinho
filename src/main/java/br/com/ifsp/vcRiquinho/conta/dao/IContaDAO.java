package br.com.ifsp.vcRiquinho.conta.dao;

import java.util.List;
import java.util.Set;

import br.com.ifsp.vcRiquinho.base.interfaces.DAO;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;

public interface IContaDAO extends  DAO<DTOConta, Integer> {
	Set<DTOConta> findByDocument(String documentoTitular);

	List<String> findMissingTypeAccounts(String documentoTitular);
}
