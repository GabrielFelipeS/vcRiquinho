package br.com.ifsp.vcRiquinho.base.interfaces;

import java.util.List;
import java.util.Optional;

public interface DAO<T,R> {
	List<T> findAll();
	
	Boolean insert(T obj);
	Boolean deleteBy(R id);
	
	T findBy(R id);
	default Optional<T> findOptionalBy(R id) {
		return Optional.of(findBy(id));
	}
	
	T updateBy(R id);
	default Optional<T> updateOptionalBy(R id) {
		return Optional.of(updateBy(id));
	}
}
