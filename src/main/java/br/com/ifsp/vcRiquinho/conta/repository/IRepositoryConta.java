package br.com.ifsp.vcRiquinho.conta.repository;

import java.util.Set;

import br.com.ifsp.vcRiquinho.base.interfaces.Repository;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;

public interface IRepositoryConta extends Repository<Conta, Integer, DTOConta> {
	Set<Conta> findBy(String documentoTitular);
}
