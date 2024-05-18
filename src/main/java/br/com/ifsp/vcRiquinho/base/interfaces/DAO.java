package br.com.ifsp.vcRiquinho.base.interfaces;

import java.util.List;
import java.util.Optional;

public interface DAO<T,P> {
	List<T> findAll();
	List<T> findWhere(String where);
	
	T insert(T dto);
	Boolean deleteBy(P id);
	
	T findBy(P id);
	default Optional<T> findOptionalBy(P id) {
		return Optional.ofNullable(findBy(id));
	}
	
	T updateBy(T dto);
	default Optional<T> updateOptionalBy(T id) {
		return Optional.ofNullable(updateBy(id));
	}
}
