package br.com.ifsp.vcRiquinho.conta.factory;

import java.util.HashMap;
import java.util.Map;

import br.com.ifsp.vcRiquinho.base.interfaces.Factory;
import br.com.ifsp.vcRiquinho.conta.dto.DTOConta;
import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.produto.models.abstracts.Produto;

public class FactoryCreator implements Factory<String, Factory<DTOConta, Conta>> {
	private Produto produto;
	private Map<String, Factory<DTOConta, Conta>> map;

	public FactoryCreator(Produto produto) {
		this.produto = produto;
		this.map = createMap();
	}

	private Map<String, Factory<DTOConta, Conta>> createMap() {
		Map<String, Factory<DTOConta, Conta>> map = new HashMap<>();
		map.put("cdi", new ContaCdiFactory());
		map.put("corrente", new ContaCorrenteFactory());
		map.put("invesimento_automatico", new ContaInvestimentoAutomaticoFactory(produto));
		return map;
	}

	@Override
	public Factory<DTOConta, Conta> createBy(String str) {		
		return map.getOrDefault(str, new ContaCorrenteFactory());
	}

	@Override
	public String convert(Factory<DTOConta, Conta> obj) {
		return obj.toString();
	}

}
