package br.com.ifsp.vcRiquinho.pessoa.factory.concrate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.ifsp.vcRiquinho.conta.models.abstracts.Conta;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoa;
import br.com.ifsp.vcRiquinho.pessoa.factory.interfaces.IFactoryPessoaCreator;

public class FactoryPessoaCreator implements IFactoryPessoaCreator {
	private Set<Conta> contas;
	private Map<String, IFactoryPessoa> map;
	
	public FactoryPessoaCreator(Set<Conta> contas) {
		this.contas = contas;
		map = createMap();
	}
	
	private Map<String, IFactoryPessoa> createMap() {
		Map<String, IFactoryPessoa> map = new HashMap<>();
		map.put("cdi", new PessoaFisicaFactory(contas));
		map.put("corrente", new PessoaJuridicaFactory(contas));
		return map;
	}

	@Override
	public IFactoryPessoa createBy(String str) {
		return map.get(str);
	}

	@Override
	public String convert(IFactoryPessoa factory) {
		return factory.toString();
	}

}
