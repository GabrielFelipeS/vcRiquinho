package br.com.ifsp.vcRiquinho.base.interfaces;

import java.util.List;

public interface Repository<ReturnType, IdType, ValueType> {
	ReturnType insert(ValueType v);
	ReturnType update(ValueType v);
	ReturnType findBy(IdType id);
	void deleteBy(IdType id);
	List<ReturnType> getAll();
}
