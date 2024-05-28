package br.com.ifsp.vcRiquinho.conta.factory.interfaces;

import br.com.ifsp.vcRiquinho.base.interfaces.Converter;
import br.com.ifsp.vcRiquinho.base.interfaces.Factory;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;

public interface IFactoryConta extends Factory<DTOConta, Conta>, Converter<DTOConta, Conta>{

}
