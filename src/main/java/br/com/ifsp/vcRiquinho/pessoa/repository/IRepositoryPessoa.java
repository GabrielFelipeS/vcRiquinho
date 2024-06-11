package br.com.ifsp.vcRiquinho.pessoa.repository;

import br.com.ifsp.vcRiquinho.base.interfaces.Repository;
import br.com.ifsp.vcRiquinho.pessoa.dto.DTOPessoaConta;
import br.com.ifsp.vcRiquinho.pessoa.models.abstracts.Pessoa;

public interface IRepositoryPessoa extends Repository<Pessoa, String, DTOPessoaConta>{
	Pessoa findByEmail(String email);
}
