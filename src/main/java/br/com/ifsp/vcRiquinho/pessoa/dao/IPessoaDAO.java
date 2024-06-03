package br.com.ifsp.vcRiquinho.pessoa.dao;

import br.com.ifsp.vcRiquinho.base.interfaces.DAO;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoa;

public interface IPessoaDAO extends DAO<DTOPessoa, String> {
	DTOPessoa findByEmail(String email);
}
