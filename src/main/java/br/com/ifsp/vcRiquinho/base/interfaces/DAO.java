package br.com.ifsp.vcRiquinho.base.interfaces;

import java.util.List;

public interface DAO<ReturnType, IdType> {
	List<ReturnType> findAll();
	
	ReturnType insert(ReturnType dto);
	Boolean deleteBy(IdType id);
	
	ReturnType findBy(IdType id);
	
	ReturnType update(ReturnType dto);

}
