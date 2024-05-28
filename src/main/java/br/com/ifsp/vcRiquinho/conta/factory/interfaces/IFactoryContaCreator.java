package br.com.ifsp.vcRiquinho.conta.factory.interfaces;

import br.com.ifsp.vcRiquinho.base.interfaces.Converter;
import br.com.ifsp.vcRiquinho.base.interfaces.Factory;

public interface IFactoryContaCreator extends Factory<String, IFactoryConta>, Converter<String, IFactoryConta> {

}
