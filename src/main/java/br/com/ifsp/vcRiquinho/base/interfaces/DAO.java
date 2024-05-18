package br.com.ifsp.vcRiquinho.base.interfaces;

import java.util.List;
import java.util.Optional;

public interface DAO<T,P> {
	List<T> findAll();
	List<T> findWhere(String where);
	
	T insert(T dto);
	Boolean deleteBy(P id);
	
	T findBy(P id);
	
	T updateBy(T dto);

}
