package br.com.ifsp.vcRiquinho.base.interfaces;

public interface Converter<T, R> {
	T convert(R obj);
}
