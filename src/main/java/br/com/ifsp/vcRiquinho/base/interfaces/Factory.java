package br.com.ifsp.vcRiquinho.base.interfaces;

public interface Factory<T, R> {
	R createBy(T obj);
}
