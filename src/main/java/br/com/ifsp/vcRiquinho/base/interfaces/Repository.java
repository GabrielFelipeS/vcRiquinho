package br.com.ifsp.vcRiquinho.base.interfaces;

import java.util.List;

public interface Repository<R,P,T> {
	R add(T obj);
	R update(T obj);
	R get(P obj);
	R delete(P obj);
	List<R> getAll();
}
